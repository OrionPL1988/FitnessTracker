package pl.wsb.fitnesstracker.user.api;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Interface (API) for modifying operations on {@link User} entities through the API.
 * Implementing classes are responsible for executing changes within a database transaction, whether by continuing an existing transaction or creating a new one if required.
 */
public interface UserService {

    /**
     * Creates a new user.
     *
     * @param user The user to be created
     * @return The created user
     */
    User createUser(User user);
    /**
     * Retrieves a user by their ID.
     */
    Optional<User> getUser(Long userId);

    /**
     * Returns all users in the system.
     */
    List<User> findAllUsers();

    /**
     * Finds users whose email contains the given fragment (case-insensitive).
     */
    List<User> findUsersByEmail(String emailFragment);

    /**
     * Finds users born before the given date (older than that date).
     */
    List<User> findUsersOlderThan(LocalDate date);

    /**
     * Deletes the user with the given ID.
     */
    void deleteUser(Long userId);

    /**
     * Updates an existing user. Only non-null fields in updateDto are applied.
     */
    User updateUser(Long userId, UserUpdateDto updateDto);
}
