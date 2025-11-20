package idm3.project.gallery.service;
import idm3.project.gallery.model.Project;
import idm3.project.gallery.model.User;
import idm3.project.gallery.repository.ProjectRepository;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
    private static final String UPLOAD_DIR = "src/main/resources/static/assets/images/projects/thumbnail/thumb_";
    @Autowired
    private ProjectRepository projectRepo;

    @Autowired
    private  ThumbnailService thumbnailService;

    public List<Project> findAll() {
        return (List<Project>) projectRepo.findAll();
    }

    public List<Project> findByUser(User user) {
        return projectRepo.findByUser(user);
    }


    public void saveProject(Project project) {
        projectRepo.save(project);
    }

    public Optional<Project> findOne(long projectId) {
        return projectRepo.findById(projectId);
    }

    public void deleteByID(long projectId) {
        projectRepo.deleteById(projectId);
    }

    public Project updateProject(Project updatedProject) {
        Optional<Project> existingProject = projectRepo.findById(updatedProject.getProjectId());

        if (existingProject.isPresent()) {
            Project project = existingProject.get();
            project.setProjectName(updatedProject.getProjectName());
            project.setProjectDescription(updatedProject.getProjectDescription());
            project.setProjectDescriptionSummary(updatedProject.getProjectDescriptionSummary());
            project.setProjectHeroImage(updatedProject.getProjectHeroImage());
            project.setCreationDate(updatedProject.getCreationDate());
            projectRepo.save(project);
            return project;
        } else {
            throw new EntityNotFoundException("Product not found with id " + updatedProject.getProjectId());
        }
    }

    public void saveProjectWithImage(Project project, MultipartFile file) throws IOException {
        if (!file.isEmpty()) {

            Path thumbnailDir = Paths.get(UPLOAD_DIR + "thumbnail/");
            Files.createDirectories(thumbnailDir);


            String filename = "thumb_" + file.getOriginalFilename();
            Path filePath = thumbnailDir.resolve(filename);


            Files.write(filePath, file.getBytes());
            File uploadedFile = filePath.toFile();


            thumbnailService.generateThumbnail(uploadedFile, uploadedFile);


            project.setProjectHeroImage(filename);
        }


        projectRepo.save(project);
    }



    public List<Project> searchProject(String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return (List<Project>) projectRepo.findAll();
        }
        return projectRepo.findByProjectNameContainingIgnoreCaseOrProjectDescriptionContainingIgnoreCase(keyword, keyword);
    }


}

