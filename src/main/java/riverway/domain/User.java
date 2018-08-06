package riverway.domain;

import riverway.dto.UserDto;

import javax.jws.soap.SOAPBinding;
import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
public class User {
    public static final GuestUser GUEST_USER = new GuestUser();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 2, max = 10)
    @Column(nullable = false)
    private String username;

    @Size(min = 6)
    @Column(nullable = false)
    private String password;

    private String email;

    @Column(length = 20)
    private String phoneNumber;

    private Long socialId;

    @Column
    @Enumerated(EnumType.STRING)
    private SocialCode socialCode;

    public User() {
    }

    public User(String username, String password, String email, String phoneNumber, Long socialId, SocialCode socialCode) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.socialId = socialId;
        this.socialCode = socialCode;
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

    public boolean matchPassword(String password) {
        return this.password.equals(password);
    }

    private static class GuestUser extends User{

        @Override
        public boolean isGuestUser(){
            return true;
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", socialId=" + socialId +
                ", socialCode=" + socialCode +
                '}';
    }
}
