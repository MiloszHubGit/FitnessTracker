package pl.wsb.fitnesstracker.user.api;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Interface (API) for read operations on {@link User} entities.
 */
public interface UserProvider {

    /**
     * Retrieves a user by ID.
     *
     * @param userId the ID of the user
     * @return an {@link Optional} containing the found user, or empty if not found
     */
    Optional<User> getUser(Long userId);

    /**
     * Retrieves a user by exact email address.
     *
     * @param email the email address to match
     * @return an {@link Optional} containing the found user, or empty if not found
     */
    Optional<User> getUserByEmail(String email);

    /**
     * Retrieves all users.
     *
     * @return list of all users
     */
    List<User> findAllUsers();

    /**
     * Retrieves users whose email contains the given fragment (case-insensitive).
     *
     * @param emailFragment the email fragment to search for
     * @return list of matching users
     */
    List<User> findUsersByEmailFragment(String emailFragment);

    /**
     * Retrieves users born before the given date (older than that date).
     *
     * @param date the reference date
     * @return list of users whose birthdate is before {@code date}
     */
    List<User> findUsersOlderThan(LocalDate date);
}
