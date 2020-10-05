package mapper;

import dto.UserDto;
import entity.User;

public class UserMapper implements Mapper<User, UserDto> {
    @Override
    public User mapDtoIntoEntity(UserDto dto) {
        if (dto == null) {
            return null;
        }

        return new User.UserBuilder()
                .setId(dto.getId())
                .setUserName(dto.getUsername())
                .setFirstName(dto.getFirstName())
                .setLastName(dto.getLastName())
                .setEmail(dto.getEmail())
                .setPassword(dto.getPassword())
                .setPhoneNumber(dto.getPhoneNumber())
                .setRole(dto.getRole())
                .build();
    }

    @Override
    public UserDto mapEntityIntoDto(User user) {
        if (user == null) {
            return null;
        }

        return new UserDto.UserDtoBuilder()
                .setId(user.getId())
                .setUserName(user.getUsername())
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setEmail(user.getEmail())
                .setPassword(user.getPassword())
                .setPhoneNumber(user.getPhoneNumber())
                .setRole(user.getRole())
                .build();
    }

}
