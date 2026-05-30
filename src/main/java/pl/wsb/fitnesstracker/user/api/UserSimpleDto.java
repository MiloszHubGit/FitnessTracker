package pl.wsb.fitnesstracker.user.api;

import jakarta.annotation.Nullable;

/**
 * DTO containing basic user information (ID, first name, last name).
 */
public record UserSimpleDto(@Nullable Long id, String firstName, String lastName) {
}
