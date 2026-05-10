package pl.wsb.fitnesstracker.user.internal;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wsb.fitnesstracker.user.api.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    /** lista wszystkich (tylko ID + imię) */
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.findAllUsers().stream()
                .map(userMapper::toDto)
                .toList();
        return ResponseEntity.ok(users);
    }

    /** szczegóły użytkownika */
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userService.getUser(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /** szukaj po emailu */
    @GetMapping("/email")
    public ResponseEntity<List<UserEmailDto>> getUsersByEmail(@RequestParam String email) {
        List<UserEmailDto> users = userService.findUsersByEmail(email).stream()
                .map(userMapper::toEmailDto)
                .toList();
        return ResponseEntity.ok(users);
    }

    /**użytkownicy starsi niż ileś lat */
    @GetMapping("/older-than/{time}")
    public ResponseEntity<List<User>> getUsersOlderThan(@PathVariable int time) {
        LocalDate cutoffDate = LocalDate.now().minusYears(time);
        return ResponseEntity.ok(userService.findUsersOlderThan(cutoffDate));
    }

    /**utwórz nowego użytkownika */
    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody UserCreateDto createDto) {
        User saved = userService.createUser(userMapper.toEntity(createDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    /**zaktualizuj użytkownika */
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id,
                                           @RequestBody UserUpdateDto updateDto) {
        try {
            return ResponseEntity.ok(userService.updateUser(id, updateDto));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /** usuń użytkownika */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}