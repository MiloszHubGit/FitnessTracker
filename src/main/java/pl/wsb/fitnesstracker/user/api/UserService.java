package pl.wsb.fitnesstracker.user.api;

/**
 * Interface (API) for modifying operations on {@link User} entities through the API.
 * Implementing classes are responsible for executing changes within a database transaction.
 */
public interface UserService {

    /**
     * Creates a new user.
     *
     * @param user the user to be created
     * @return the created user
     */
    User createUser(User user);

    /**
     * Updates an existing user identified by {@code id} with the data from {@code user}.
     *
     * @param id   the ID of the user to update
     * @param user the updated user data
     * @return the updated user
     */
    User updateUser(Long id, User user);

    /**
     * Deletes the user with the given {@code id}.
     *
     * @param id the ID of the user to delete
     */
    void deleteUser(Long id);
}
