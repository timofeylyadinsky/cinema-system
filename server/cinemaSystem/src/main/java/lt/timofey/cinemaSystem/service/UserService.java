package lt.timofey.cinemaSystem.service;

import jakarta.transaction.Transactional;
import lt.timofey.cinemaSystem.entity.User;
import lt.timofey.cinemaSystem.exception.UserExistException;
import lt.timofey.cinemaSystem.payload.SignupRequest;
import lt.timofey.cinemaSystem.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(SignupRequest userIn) {
        User user = new User();
        user.setEmail(userIn.getEmail());
        user.setUsername(userIn.getUsername());
        user.setPassword(passwordEncoder.encode(userIn.getPassword()));

        try {
            LOG.info("Saving User {}", userIn.getEmail());
            return userRepository.save(user);
        } catch (Exception e) {
            LOG.error("Error during registration. {}", e.getMessage());
            throw new UserExistException("The user " + user.getUsername() + " already exist. Please check credentials");
        }
    }


    @Transactional
    public void update(Long id, User updatedUser) {
        updatedUser.setId(id);
        userRepository.save(updatedUser);
    }

}
