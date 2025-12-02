package idm3.project.gallery.repository;

import idm3.project.gallery.model.Showcase;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ShowcaseRepository extends CrudRepository<Showcase, Long> {

    List<Showcase> findByNameContainingIgnoreCase(String keyword);

    List<Showcase> findByStatusIgnoreCase(String status);

    List<Showcase> findAllByOrderByNameAsc();
    List<Showcase> findAllByOrderByNameDesc();
    List<Showcase> findAllByOrderByShowcaseIdDesc();
    List<Showcase> findAllByOrderByShowcaseIdAsc();
}
