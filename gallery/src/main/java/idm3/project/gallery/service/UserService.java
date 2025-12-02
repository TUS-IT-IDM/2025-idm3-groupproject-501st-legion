package idm3.project.gallery.service;
import idm3.project.gallery.model.User;
import idm3.project.gallery.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User authenticate(String username, String password) {
        return userRepository.findByUserNameAndPassword(username, password);
    }

    public boolean registerUser(User user) {
        if (userRepository.existsByUserName(user.getUserName()) || userRepository.existsByEmailAddress(user.getEmailAddress())) {
            return false;
        }
        userRepository.save(user);
        return true;
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }

}