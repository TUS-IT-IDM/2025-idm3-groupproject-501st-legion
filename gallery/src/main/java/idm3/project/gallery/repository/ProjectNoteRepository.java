package idm3.project.gallery.repository;

import idm3.project.gallery.model.ProjectNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProjectNoteRepository extends JpaRepository<ProjectNote, Long> {
    List<ProjectNote> findByUserId(Long userId);
    ProjectNote findByUserIdAndEmployers_EmployerId(Long userId, Long employerId);

}
