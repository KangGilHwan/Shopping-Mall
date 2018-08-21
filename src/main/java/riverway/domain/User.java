package riverway.domain;

import riverway.dto.UserDto;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
public class User {
    public static final GuestUser GUEST_USER = new GuestUser();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 2, max = 10)
    private String username;

    @Size(min = 6)
    private String password;

    private String email;

    @Column(length = 20)
    private String phoneNumber;

    private Long socialId;

    @Column
    @Enumerated(EnumType.STRING)
    private SocialCode socialCode;

    @Column(columnDefinition = "varchar(30) default 'ROLE_USER'")
    @Enumerated(EnumType.STRING)
    private Role role;

    public User() {
    }

    public User(String username, String password, String email, String phoneNumber, Long socialId, SocialCode socialCode, Role role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.socialId = socialId;
        this.socialCode = socialCode;
        this.role = role;
    }

    public UserDto toUserDto() {
        return UserDto.build()
                .setUsername(username)
                .setPassword(password)
                .setPhoneNumber(phoneNumber)
                .setEmail(email)
                .setRole(role);
    }

    public boolean matchPassword(String password) {
        return this.password.equals(password);
    }

    public boolean isSeller() {
        return Role.hasSeller(role);
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

    public boolean isGuestUser() {
        return false;
    }

    private static class GuestUser extends User {

        @Override
        public boolean isGuestUser() {
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
                ", role=" + role +
                ", socialCode=" + socialCode +
                '}';
    }
}
