package riverway.domain;

import riverway.dto.UserDto;

import javax.jws.soap.SOAPBinding;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
public class User {
    public static final GuestUser GUEST_USER = new GuestUser();

    @Id
    @GeneratedValue
    private Long id;

    @Size(min = 2, max = 10)
    @Column(nullable = false)
    private String username;

    @Size(min = 6, max = 20)
    @Column(nullable = false)
    private String password;

    private String email;

    @Column(length = 20)
    private String phoneNumber;

    public User() {
    }

    public User(String username, String password, String email, String phoneNumber) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public UserDto toUserDto(){
        return UserDto.build()
                .setUsername(username)
                .setPassword(password)
                .setPhoneNumber(phoneNumber)
                .setEmail(email);
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

    public boolean isGuestUser(){
        return false;
    }

    private static class GuestUser extends User{

        @Override
        public boolean isGuestUser(){
            return true;
        }
    }
}
