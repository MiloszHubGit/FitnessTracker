package pl.wsb.fitnesstracker.user.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.wsb.fitnesstracker.user.api.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds a user by exact email address.
     *
     * @param email email to match
     * @return {@link Optional} with the found user, or empty
     */
    default Optional<User> findByEmail(String email) {
        return findAll().stream()
                .filter(user -> Objects.equals(user.getEmail(), email))
                .findFirst();
    }

    /**
     * Finds users whose email contains the given fragment, case-insensitively.
     *
     * @param fragment the email fragment to search for
     * @return list of matching users
     */
    default List<User> findByEmailContainingIgnoreCase(String fragment) {
        return findAll().stream()
                .filter(user -> user.getEmail().toLowerCase().contains(fragment.toLowerCase()))
                .toList();
    }

    /**
     * Finds users born before the given date.
     *
     * @param date the reference date
     * @return list of users born before {@code date}
     */
    default List<User> findByBirthdateBefore(LocalDate date) {
        return findAll().stream()
                .filter(user -> user.getBirthdate().isBefore(date))
                .toList();
    }

    /**
     * Finds all users whose email belongs to the given domain.
     *
     * @param domain email domain suffix (without @)
     * @return list of matching users
     */
    @Query(
        value = "SELECT * FROM users WHERE email LIKE CONCAT('%@', :domain)",
        nativeQuery = true
    )
    List<User> findUsersByEmailDomain(@Param("domain") String domain);
}
