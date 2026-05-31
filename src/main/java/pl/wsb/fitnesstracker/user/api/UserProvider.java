package pl.wsb.fitnesstracker.user.api;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserProvider {

    /**
     * Retrieves a user based on their ID.
     *
     * @param userId id of the user to be searched
     * @return Optional containing the located user, or Optional.empty if not found
     */
    Optional<User> getUser(Long userId);

    /**
     * Retrieves a user based on their email.
     *
     * @param email The email of the user to be searched
     * @return Optional containing the located user, or Optional.empty if not found
     */
    Optional<User> getUserByEmail(String email);

    /**
     * Retrieves all users.
     *
     * @return all users
     */
    List<User> findAllUsers();

    /**
     * Finds users by e-mail fragment, ignoring case.
     *
     * @param email email or part of e-mail
     * @return matching users
     */
    List<User> findUsersByEmail(String email);

    /**
     * Finds users born before selected date.
     *
     * @param date date used as upper birthdate limit
     * @return users older than given date
     */
    List<User> findUsersOlderThan(LocalDate date);
}