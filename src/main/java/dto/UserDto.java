package dto;

import entity.Role;

import java.util.Objects;

public class UserDto {
    private final Long id;
    private final String username;
    private final String password;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String phoneNumber;
    private final Role role;

    private UserDto(UserDtoBuilder builder) {
        id = builder.id;
        username = builder.userName;
        firstName = builder.firstName;
        lastName = builder.lastName;
        email = builder.email;
        password = builder.password;
        phoneNumber = builder.phoneNumber;
        role = builder.role;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Role getRole() {
        return role;
    }

    public static class UserDtoBuilder {
        private Long id;
        private String userName;
        private String password;
        private String firstName;
        private String lastName;
        private String email;
        private String phoneNumber;
        private Role role;

        public UserDtoBuilder() {
        }

        public UserDtoBuilder(UserDto userDto) {
            this.userName = userDto.getUsername();
            this.firstName = userDto.getFirstName();
            this.lastName = userDto.getLastName();
            this.email = userDto.getEmail();
            this.password = userDto.getPassword();
            this.phoneNumber = userDto.getPhoneNumber();
            this.role = userDto.getRole();
        }

        public UserDtoBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public UserDtoBuilder setUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public UserDtoBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public UserDtoBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public UserDtoBuilder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public UserDtoBuilder setRole(Role role) {
            this.role = role;
            return this;
        }

        public UserDtoBuilder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserDtoBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserDto build() {
            return new UserDto(this);
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserDto userDto = (UserDto) o;
        return Objects.equals(id, userDto.id) &&
                Objects.equals(username, userDto.username) &&
                Objects.equals(password, userDto.password) &&
                Objects.equals(firstName, userDto.firstName) &&
                Objects.equals(lastName, userDto.lastName) &&
                Objects.equals(email, userDto.email) &&
                Objects.equals(phoneNumber, userDto.phoneNumber) &&
                role == userDto.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, firstName, lastName, email, phoneNumber, role);
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", role=" + role +
                '}';
    }

}
