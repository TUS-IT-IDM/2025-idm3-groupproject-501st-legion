package idm3.project.gallery.service;

import idm3.project.gallery.model.Project;
import idm3.project.gallery.model.Showcase;
import idm3.project.gallery.model.User;
import idm3.project.gallery.repository.ProjectRepository;
import idm3.project.gallery.repository.ShowcaseRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.*;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ProjectService {

    private final ProjectRepository projectRepo;
    private final ShowcaseRepository showcaseRepo;
    private final ThumbnailService thumbnailService;

    private static final String IMG_DIR = "src/main/resources/static/assets/images/projects/";
    private static final String THUMB_DIR = IMG_DIR + "thumbnail/";

    public ProjectService(ProjectRepository projectRepo,
                          ShowcaseRepository showcaseRepo,
                          ThumbnailService thumbnailService) {
        this.projectRepo = projectRepo;
        this.showcaseRepo = showcaseRepo;
        this.thumbnailService = thumbnailService;
    }

    public List<Project> findAll() {
        return projectRepo.findAll();
    }

    public List<Project> findByUser(User user) {
        return projectRepo.findByUser_UserId(user.getUserId());
    }

    public Project findOne(Long id) {
        return projectRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Project not found"));
    }

    public void delete(Long id) {
        Project project = findOne(id);

        // BREAK FK LINK FIRST → prevents DB blocking delete
        if (project.getShowcase() != null) {
            project.setShowcase(null);
            projectRepo.save(project);
        }

        projectRepo.deleteById(id);
    }

    public List<Project> searchByName(String keyword, User user) {

        if (keyword == null || keyword.isBlank()) {
            return findByUser(user);
        }

        return projectRepo.findByProjectNameContainingIgnoreCase(keyword)
                .stream()
                .filter(p -> p.getUser().getUserId() == user.getUserId())
                .toList();
    }

    public void addProject(Project project,
                           MultipartFile file,
                           Long showcaseId,
                           User user) throws Exception {

        Showcase showcase = showcaseRepo.findById(showcaseId)
                .orElseThrow(() -> new EntityNotFoundException("Showcase not found"));

        project.setUser(user);
        project.setShowcase(showcase);
        project.setCreationDate(new Date(System.currentTimeMillis()));

        if (!file.isEmpty()) {

            Files.createDirectories(Paths.get(IMG_DIR));
            Files.createDirectories(Paths.get(THUMB_DIR));

            String ext = file.getOriginalFilename()
                    .substring(file.getOriginalFilename().lastIndexOf('.'));

            String base = UUID.randomUUID().toString();

            String imageName = base + ext;
            String thumbName = "thumb_" + base + ext;

            Path fullImagePath = Paths.get(IMG_DIR + imageName);
            Files.write(fullImagePath, file.getBytes());

            Path thumbPath = Paths.get(THUMB_DIR + thumbName);
            thumbnailService.generateThumbnail(fullImagePath.toFile(), thumbPath.toFile());

            project.setProjectHeroImage(thumbName);
        }

        projectRepo.save(project);
    }
    public void addProject(Project project, MultipartFile file) {
        projectRepo.save(project);
    }

    public void updateProject(Project incoming,
                              MultipartFile file,
                              Long showcaseId,
                              User user) throws Exception {

        Project existing = findOne(incoming.getProjectId());

        if (existing.getUser().getUserId() != user.getUserId()) {
            throw new IllegalAccessException("Unauthorized access to project");
        }

        Showcase showcase = showcaseRepo.findById(showcaseId)
                .orElseThrow(() -> new EntityNotFoundException("Showcase not found"));

        existing.setProjectName(incoming.getProjectName());
        existing.setProjectDescription(incoming.getProjectDescription());
        existing.setProjectDescriptionSummary(incoming.getProjectDescriptionSummary());
        existing.setCategory(incoming.getCategory());
        existing.setShowcase(showcase);

        if (!file.isEmpty()) {

            Files.createDirectories(Paths.get(IMG_DIR));
            Files.createDirectories(Paths.get(THUMB_DIR));

            String ext = file.getOriginalFilename()
                    .substring(file.getOriginalFilename().lastIndexOf('.'));

            String base = UUID.randomUUID().toString();

            String imageName = base + ext;
            String thumbName = "thumb_" + base + ext;

            Path fullImagePath = Paths.get(IMG_DIR + imageName);
            Files.write(fullImagePath, file.getBytes());

            Path thumbPath = Paths.get(THUMB_DIR + thumbName);
            thumbnailService.generateThumbnail(fullImagePath.toFile(), thumbPath.toFile());

            existing.setProjectHeroImage(thumbName);
        }

        projectRepo.save(existing);
    }

    public List<Project> findProjectsByShowcaseId(long id) {
        return projectRepo.findByShowcase_ShowcaseId(id);
    }
}
