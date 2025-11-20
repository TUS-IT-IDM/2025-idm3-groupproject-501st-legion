package idm3.project.gallery.repository;

import idm3.project.gallery.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByEmployerEmployerId(Long employerId);
}

