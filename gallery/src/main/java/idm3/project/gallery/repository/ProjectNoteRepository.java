package idm3.project.gallery.repository;

import idm3.project.gallery.model.ProjectNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProjectNoteRepository extends JpaRepository<ProjectNote, Long> {

    // Find all saved project notes for one employer, based on USERID column
    List<ProjectNote> findByUserId(Long userId);

    // Find ONE specific note for a project+employer pair
    List<ProjectNote> findByUserIdAndProject_ProjectId(Long userId, Long projectId);

    // Get ALL notes for employer sorted by newest first (used in /employer/notes)
    List<ProjectNote> findByUserIdOrderByCreatedAtDesc(Long userId);
}
