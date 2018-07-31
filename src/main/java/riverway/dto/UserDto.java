package riverway.dto;

import riverway.domain.User;

import java.util.Objects;

public class UserDto {

    private Long id;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;

    public UserDto() {
    }

    private UserDto(String username, String password, String email, String phoneNumber){
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public User toUser(){
        return new User(username, password, email, phoneNumber);
    }

    public static UserDto build(){
        return new UserDto();
    }

    public String getUsername() {
        return username;
    }

    public UserDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserDto setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UserDto setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return Objects.equals(username, userDto.username) &&
                Objects.equals(password, userDto.password) &&
                Objects.equals(email, userDto.email) &&
                Objects.equals(phoneNumber, userDto.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, email, phoneNumber);
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
