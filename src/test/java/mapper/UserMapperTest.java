package mapper;

import dto.UserDto;
import entity.Role;
import entity.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class UserMapperTest {
    private static final UserDto USER_DTO = buildUserDto();
    private static final User USER = buildUser();

    private static final Long ID = 1L;
    private static final String USERNAME = "Mercucio29";
    private static final String PASSWORD = "SomeWhere0Nr@inbow";
    private static final String FIRST_NAME = "David";
    private static final String LAST_NAME = "Merquez";
    private static final String EMAIL = "david@qmail.com";
    private static final String PHONE_NUMBER = "+380884123245";
    private static final Role ROLE = Role.CLIENT;

    private UserMapper mapper;


    @Before
    public void init() {
        mapper = new UserMapper();
    }

    @Test
    public void mapDtoIntoEntityShouldReturnNullForNullDto() {
        UserDto userDto = null;
        User actual = mapper.mapDtoIntoEntity(userDto);
        assertNull(actual);
    }

    @Test
    public void mapDtoIntoEntityShouldReturnCorrectUserForNonEmptyDto() {
        User actual = mapper.mapDtoIntoEntity(USER_DTO);
        assertEquals(USER, actual);
    }

    @Test
    public void mapEntityIntoDtoShouldReturnNullForNullDto() {
        User user = null;
        UserDto actual = mapper.mapEntityIntoDto(user);
        assertNull(actual);
    }

    @Test
    public void mapEntityIntoDtoShouldReturnCorrectUserForNonEmptyDto() {
        UserDto actual = mapper.mapEntityIntoDto(USER);
        assertEquals(USER_DTO, actual);
    }

    private static UserDto buildUserDto() {
        return new UserDto.UserDtoBuilder()
                .setId(ID)
                .setUserName(USERNAME)
                .setFirstName(FIRST_NAME)
                .setLastName(LAST_NAME)
                .setEmail(EMAIL)
                .setPassword(PASSWORD)
                .setPhoneNumber(PHONE_NUMBER)
                .setRole(ROLE)
                .build();
    }

    private static User buildUser() {
        return User.builder()
                .setId(ID)
                .setUserName(USERNAME)
                .setFirstName(FIRST_NAME)
                .setLastName(LAST_NAME)
                .setEmail(EMAIL)
                .setPassword(PASSWORD)
                .setPhoneNumber(PHONE_NUMBER)
                .setRole(ROLE)
                .build();
    }

}
