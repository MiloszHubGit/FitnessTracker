package pl.wsb.fitnesstracker.user.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Query searching users by email address. It matches by exact match.
     *
     * @param email email of the user to search
     * @return {@link Optional} containing found user or {@link Optional#empty()} if none matched
     */
    default Optional<User> findByEmail(String email) {
        return findAll().stream()
                .filter(user -> Objects.equals(user.getEmail(), email))
                .findFirst();
    }

    /**
     * Find all users with email address from a specific domain.
     * Example: domain = "gmail.com" returns all users with @gmail.com email.
     *
     * @param domain email domain suffix (without @)
     * @return list of users with email from the given domain
     */
    @Query(
        value = "SELECT * FROM users WHERE email LIKE CONCAT('%@', :domain)",
        nativeQuery = true
    )
    List<User> findUsersByEmailDomain(@Param("domain") String domain);

}
