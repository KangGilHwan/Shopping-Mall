package riverway.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Where;
import org.springframework.security.crypto.password.PasswordEncoder;
import riverway.dto.UserDto;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

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

    @Column(unique = true)
    private Long socialId;

    @Column
    @Enumerated(EnumType.STRING)
    private SocialCode socialCode;

    @Column(columnDefinition = "varchar(30) default 'USER'")
    @Enumerated(EnumType.STRING)
    private Role role;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    @Where(clause = "used = false")
    private List<UserCoupon> coupons;

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
                .setId(id)
                .setUsername(username)
                .setPassword(password)
                .setPhoneNumber(phoneNumber)
                .setEmail(email)
                .setRole(role)
                .setCoupons(coupons);
    }

    public boolean matchPassword(String inputPassword, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(inputPassword, password);
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

    public List<UserCoupon> getCoupons() {
        return coupons;
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
