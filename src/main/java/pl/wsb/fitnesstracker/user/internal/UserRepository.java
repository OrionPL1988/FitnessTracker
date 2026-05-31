package pl.wsb.fitnesstracker.user.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wsb.fitnesstracker.user.api.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;

interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Query searching users by email address. It matches by exact match.
     *
     * @param email email of the user to search
     * @return Optional containing found user or Optional.empty if none matched
     */
    default Optional<User> findByEmail(String email) {
        return findAll().stream()
                .filter(user -> Objects.equals(user.getEmail(), email))
                .findFirst();
    }

    /**
     * Searches users by e-mail fragment, ignoring case.
     *
     * @param email email fragment
     * @return matching users
     */
    default List<User> findByEmailContainingIgnoreCase(String email) {
        if (email == null || email.isBlank()) {
            return List.of();
        }

        String searchedEmail = email.toLowerCase(Locale.ROOT);

        return findAll().stream()
                .filter(user -> user.getEmail() != null)
                .filter(user -> user.getEmail().toLowerCase(Locale.ROOT).contains(searchedEmail))
                .toList();
    }

    /**
     * Searches users born before selected date.
     *
     * @param date selected date
     * @return users born before selected date
     */
    default List<User> findOlderThan(LocalDate date) {
        return findAll().stream()
                .filter(user -> user.getBirthdate().isBefore(date))
                .toList();
    }
}