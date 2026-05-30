package pl.wsb.fitnesstracker.user.api;

import jakarta.annotation.Nullable;

/**
 * DTO containing user ID and email address, used in email-based search results.
 */
public record UserEmailDto(@Nullable Long id, String email) {
}
