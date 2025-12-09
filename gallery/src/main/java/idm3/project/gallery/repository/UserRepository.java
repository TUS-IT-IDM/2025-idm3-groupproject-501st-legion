package idm3.project.gallery.repository;


import idm3.project.gallery.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUserNameAndPassword(String userName, String password);

    boolean existsByUserName(String userName);

    boolean existsByEmailAddress(String emailAddress);
}


