package idm3.project.gallery.service;

import idm3.project.gallery.model.Employer;
import idm3.project.gallery.model.Review;
import idm3.project.gallery.repository.EmployerRepository;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.sql.Date;
import java.util.List;

@Service
public class EmployerService {

    private final EmployerRepository employerRepo;

    @Autowired
    private ThumbnailService thumbnailService;

    @Autowired
    public EmployerService(EmployerRepository employerRepo) {
        this.employerRepo = employerRepo;
    }

    private Employer getOrThrow(Long id) {
        return employerRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employer not found ID " + id));
    }

    public List<Employer> findAll() {
        return employerRepo.findAll();
    }

    public Employer findOne(Long id) {
        return getOrThrow(id);
    }

    public Employer saveEmployer(Employer employer) {
        if (employer.getCreationDate() == null) {
            employer.setCreationDate(new Date(System.currentTimeMillis()));
        }
        return employerRepo.save(employer);
    }

    public void deleteById(Long id) {
        employerRepo.deleteById(id);
    }

    public List<Employer> searchProject(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return findAll();
        }
        return employerRepo.findByEmployerNameContainingIgnoreCaseOrEmployerDescriptionContainingIgnoreCase(
                keyword, keyword
        );
    }

    public void saveToList(Long id) {
        Employer e = getOrThrow(id);
        e.setSaved(true);
        employerRepo.save(e);
    }

    public void removeFromList(Long id) {
        Employer e = getOrThrow(id);
        e.setSaved(false);
        employerRepo.save(e);
    }

    public void updateNotes(Long id, String notes) {
        Employer e = getOrThrow(id);
        e.setNotes(notes);
        employerRepo.save(e);
    }

    public List<Employer> getSavedProjects() {
        return employerRepo.findAll()
                .stream()
                .filter(Employer::isSaved)
                .toList();
    }

    public void addReview(Long employerId, Integer rating, String comment) {
        Employer e = getOrThrow(employerId);

        Review r = new Review();
        r.setRating(rating);
        r.setComment(comment);
        r.setEmployer(e);

        e.getReviews().add(r);
        employerRepo.save(e);
    }

    public void saveEmployerImage(Long employerId, MultipartFile file) throws Exception {

        Employer employer = getOrThrow(employerId);

        if (file.isEmpty()) {
            throw new Exception("File is empty");
        }

        String uploadDir = "src/main/resources/static/images/projects/";
        String thumbnailDir = "src/main/resources/static/images/projects/thumbnail/";

        new File(uploadDir).mkdirs();
        new File(thumbnailDir).mkdirs();

        String filename = file.getOriginalFilename();
        File saveFile = new File(uploadDir + filename);
        file.transferTo(saveFile);

        employer.setEmployerHeroImage(filename);
        employerRepo.save(employer);

        File thumbFile = new File(thumbnailDir + "thumb_" + filename);
        thumbnailService.generateThumbnail(saveFile, thumbFile);
    }
}





