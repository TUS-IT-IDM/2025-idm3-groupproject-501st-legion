package idm3.project.gallery.repository;

import idm3.project.gallery.model.Showcaseproject;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ShowcaseprojectRepository extends CrudRepository<Showcaseproject, Long> {

    @Query("SELECT sp FROM Showcaseproject sp WHERE sp.showcaseId.showcaseId = :showcaseId")
    List<Showcaseproject> findByShowcaseId(long showcaseId);
}
