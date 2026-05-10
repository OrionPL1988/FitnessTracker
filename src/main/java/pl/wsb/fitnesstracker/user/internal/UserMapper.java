package pl.wsb.fitnesstracker.user.internal;

import org.springframework.stereotype.Component;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserCreateDto;
import pl.wsb.fitnesstracker.user.api.UserDto;
import pl.wsb.fitnesstracker.user.api.UserEmailDto;

@Component
class UserMapper {

    UserDto toDto(User user) {
        return new UserDto(user.getId(), user.getFirstName(), user.getLastName());
    }

    UserEmailDto toEmailDto(User user) {
        return new UserEmailDto(user.getId(), user.getEmail());
    }

    User toEntity(UserCreateDto createDto) {
        return new User(
                createDto.firstName(),
                createDto.lastName(),
                createDto.birthdate(),
                createDto.email()
        );
    }
}