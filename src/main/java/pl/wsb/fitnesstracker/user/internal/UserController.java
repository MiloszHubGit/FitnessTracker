package pl.wsb.fitnesstracker.user.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.wsb.fitnesstracker.user.api.UserDto;
import pl.wsb.fitnesstracker.user.api.UserEmailDto;
import pl.wsb.fitnesstracker.user.api.UserNotFoundException;
import pl.wsb.fitnesstracker.user.api.UserProvider;
import pl.wsb.fitnesstracker.user.api.UserService;
import pl.wsb.fitnesstracker.user.api.UserSimpleDto;

import java.time.LocalDate;
import java.util.List;

/**
 * REST controller for user management operations.
 * Exposes CRUD endpoints under {@code /v1/users}.
 */
@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserService userService;
    private final UserProvider userProvider;
    private final UserMapper userMapper;

    /**
     * Returns all users with full details.
     */
    @GetMapping
    public List<UserDto> getAllUsers() {
        return userProvider.findAllUsers().stream()
                .map(userMapper::toDto)
                .toList();
    }

    /**
     * Returns all users with basic info (ID, first name, last name).
     */
    @GetMapping("/simple")
    public List<UserSimpleDto> getAllSimpleUsers() {
        return userProvider.findAllUsers().stream()
                .map(userMapper::toSimpleDto)
                .toList();
    }

    /**
     * Returns the user with the given {@code id}.
     */
    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return userProvider.getUser(id)
                .map(userMapper::toDto)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    /**
     * Returns users whose email contains the given fragment (case-insensitive).
     */
    @GetMapping("/email")
    public List<UserEmailDto> getUsersByEmail(@RequestParam String email) {
        return userProvider.findUsersByEmailFragment(email).stream()
                .map(userMapper::toEmailDto)
                .toList();
    }

    /**
     * Returns users born before the given date (older than {@code time}).
     */
    @GetMapping("/older/{time}")
    public List<UserDto> getUsersOlderThan(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate time) {
        return userProvider.findUsersOlderThan(time).stream()
                .map(userMapper::toDto)
                .toList();
    }

    /**
     * Creates a new user. Returns HTTP 201 Created on success.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto addUser(@RequestBody UserDto userDto) {
        return userMapper.toDto(userService.createUser(userMapper.toEntity(userDto)));
    }

    /**
     * Deletes the user with the given {@code userId}. Returns HTTP 204 No Content.
     */
    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }

    /**
     * Updates the user with the given {@code userId}.
     */
    @PutMapping("/{userId}")
    public UserDto updateUser(@PathVariable Long userId, @RequestBody UserDto userDto) {
        return userMapper.toDto(userService.updateUser(userId, userMapper.toEntity(userDto)));
    }
}
