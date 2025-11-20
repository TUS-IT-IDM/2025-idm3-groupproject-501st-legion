package idm3.project.gallery.service;

import idm3.project.gallery.model.Review;
import idm3.project.gallery.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepo;

    public ReviewService(ReviewRepository reviewRepo) {
        this.reviewRepo = reviewRepo;
    }

    public List<Review> getReviewsForEmployer(Long employerId) {
        return reviewRepo.findByEmployerEmployerId(employerId);
    }

    public Review saveReview(Review review) {
        return reviewRepo.save(review);
    }
}

