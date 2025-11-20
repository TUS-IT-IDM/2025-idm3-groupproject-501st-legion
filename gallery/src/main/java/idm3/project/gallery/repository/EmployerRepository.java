package idm3.project.gallery.repository;

import idm3.project.gallery.model.Employer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployerRepository extends JpaRepository<Employer, Long> {


    List<Employer> findByEmployerNameContainingIgnoreCaseOrEmployerDescriptionContainingIgnoreCase(
            String employerName,
            String employerDescription
    );
}



