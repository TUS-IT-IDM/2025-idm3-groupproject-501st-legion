package idm3.project.gallery.repository;


import idm3.project.gallery.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUserNameAndPassword(String username, String password);

    User findByUserName(String username);

    boolean existsByUserName(String username);

    boolean existsByEmailAddress(String emailAddress);


}