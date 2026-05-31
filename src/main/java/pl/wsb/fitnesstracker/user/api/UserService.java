package pl.wsb.fitnesstracker.user.api;

/**
 * Interface (API) for modifying operations on {@link User} entities through the API.
 * Implementing classes are responsible for executing changes within a database transaction.
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
     * Updates an existing user.
     *
     * @param userId ID of the user to update
     * @param user new user data
     * @return updated user
     */
    User updateUser(Long userId, User user);

    /**
     * Deletes user by ID.
     *
     * @param userId ID of the user to delete
     */
    void deleteUser(Long userId);
}