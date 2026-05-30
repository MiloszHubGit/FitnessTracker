package pl.wsb.fitnesstracker.user.internal;

import org.springframework.stereotype.Component;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserDto;
import pl.wsb.fitnesstracker.user.api.UserEmailDto;
import pl.wsb.fitnesstracker.user.api.UserSimpleDto;

/**
 * Maps between {@link User} entities and their DTO representations.
 */
@Component
class UserMapper {

    /**
     * Maps a {@link User} to a full {@link UserDto}.
     */
    UserDto toDto(User user) {
        return new UserDto(user.getId(), user.getFirstName(), user.getLastName(),
                user.getBirthdate(), user.getEmail());
    }

    /**
     * Maps a {@link User} to a {@link UserSimpleDto} with only basic fields.
     */
    UserSimpleDto toSimpleDto(User user) {
        return new UserSimpleDto(user.getId(), user.getFirstName(), user.getLastName());
    }

    /**
     * Maps a {@link User} to a {@link UserEmailDto} with only ID and email.
     */
    UserEmailDto toEmailDto(User user) {
        return new UserEmailDto(user.getId(), user.getEmail());
    }

    /**
     * Maps a {@link UserDto} to a {@link User} entity (without ID, for creation).
     */
    User toEntity(UserDto userDto) {
        return new User(userDto.firstName(), userDto.lastName(), userDto.birthdate(), userDto.email());
    }
}
