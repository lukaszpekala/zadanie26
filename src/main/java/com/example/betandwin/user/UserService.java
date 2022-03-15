package com.example.betandwin.user;

import com.example.betandwin.userrole.Role;
import com.example.betandwin.userrole.UserRole;
import com.example.betandwin.userrole.UserRoleRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.InvalidKeyException;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;

    public UserService(PasswordEncoder passwordEncoder,
                       UserRepository userRepository,
                       UserRoleRepository userRoleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
    }

    public void registerUser(String username, String rawPassword) {
        AUser userToAdd = new AUser();
        userToAdd.setUsername(username);
        String encryptedPassword = passwordEncoder.encode(rawPassword);
        userToAdd.setPassword(encryptedPassword);
        userToAdd.getRoles().add(userRoleRepository.findByRole(Role.ROLE_USER));
        userRepository.save(userToAdd);
    }

    public AUser findByUsername(String name) {
        return userRepository.findByUsername(name).orElse(null);
    }

    public List<AUser> findAllWithoutCurrentUser() {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findAll()
                .stream()
                .filter(user -> !user.getUsername().equals(currentUser.getName()))
                .collect(Collectors.toList());
    }

    public AUser findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public boolean isUserAnAdmin(AUser user) {
        return user.getRoles().contains(userRoleRepository.findByRole(Role.ROLE_ADMIN));
    }

    public void updateUserRole(Long id, boolean admin) {
        UserRole userRole = userRoleRepository.findByRole(Role.ROLE_ADMIN);
        AUser user = findById(id);
        if (admin) {
            user.getRoles().add(userRole);
        } else {
            user.getRoles().remove(userRole);
        }
        userRepository.save(user);
    }

    public boolean updateProfile(AUser user, String newPassword) {
        AUser dbUser = findById(user.getId());
        dbUser.setFirstName(user.getFirstName());
        dbUser.setLastName(user.getLastName());
        boolean updated = true;
        if (!newPassword.isEmpty()) {
            try {
                String oldPassword = user.getPassword();
                dbUser = updatePassword(dbUser, oldPassword, newPassword);
            } catch (InvalidKeyException e) {
                updated = false;
            }
        }
        userRepository.save(dbUser);
        return updated;
    }

    private AUser updatePassword(AUser dbUser, String oldPassword, String newPassword) throws InvalidKeyException {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        if (currentUser.getName().equals(dbUser.getUsername())) {
            if (passwordEncoder.matches(oldPassword, dbUser.getPassword())) {
                dbUser.setPassword(passwordEncoder.encode(newPassword));
            } else {
                throw new InvalidKeyException();
            }
        }
        return dbUser;
    }
}
