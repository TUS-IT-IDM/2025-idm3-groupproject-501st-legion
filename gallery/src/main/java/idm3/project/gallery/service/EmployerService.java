package idm3.project.gallery.service;

import idm3.project.gallery.model.Employer;
import idm3.project.gallery.repository.EmployerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.sql.Date;
import java.util.List;

@Service
public class EmployerService {

    private final EmployerRepository employerRepo;

    @Autowired
    public EmployerService(EmployerRepository employerRepo) {
        this.employerRepo = employerRepo;
    }



    private Employer getOrThrow(Long id) {
        return employerRepo.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Employer not found ID " + id));
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

        return employerRepo
                .findByEmployerNameContainingIgnoreCaseOrEmployerDescriptionContainingIgnoreCase(
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

    @Autowired
    private ThumbnailService thumbnailService;

    public void generateThumbnailForEmployer(Employer employer) {
        if (employer.getEmployerHeroImage() == null) return;

        try {
            String sourcePath =
                    "src/main/resources/static/assets/images/projects/" + employer.getEmployerHeroImage();

            String thumbPath =
                    "src/main/resources/static/assets/images/projects/thumbnail/thumb_" + employer.getEmployerHeroImage();

            thumbnailService.generateThumbnail(
                    new File(sourcePath),
                    new File(thumbPath)
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}


