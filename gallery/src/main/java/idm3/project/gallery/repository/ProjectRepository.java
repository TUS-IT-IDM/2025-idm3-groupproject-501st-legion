package idm3.project.gallery.repository;

import idm3.project.gallery.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findByUser_UserId(Long userId);

    List<Project> findByProjectNameContainingIgnoreCase(String name);

    List<Project> findByShowcase_ShowcaseId(Long showcaseId);
}
