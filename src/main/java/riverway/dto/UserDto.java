package riverway.dto;

import riverway.domain.Role;
import riverway.domain.SocialCode;
import riverway.domain.User;
import riverway.domain.UserCoupon;

import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

public class UserDto {

    private Long id;
    @Size(min = 2, max = 10)
    private String username;
    @Size(min = 6)
    private String password;
    private String email;
    private String phoneNumber;
    private Long socialId;
    private SocialCode socialCode;
    private Role role;
    private List<UserCoupon> coupons;

    public UserDto() {
    }

    private UserDto(String username, String password, String email, String phoneNumber) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public User toConsumer() {
        return new User(username, password, email, phoneNumber, socialId, socialCode, Role.ROLE_USER);
    }

    public User toUser() {
        return new User(username, password, email, phoneNumber, socialId, socialCode, role);
    }

    public static UserDto build() {
        return new UserDto();
    }

    public Long getId() {
        return id;
    }

    public UserDto setId(Long id) {
        this.id = id;
        return this;
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

    public Long getSocialId() {
        return socialId;
    }

    public UserDto setSocialId(Long socialId) {
        this.socialId = socialId;
        return this;
    }

    public SocialCode getSocialCode() {
        return socialCode;
    }

    public UserDto setSocialCode(SocialCode socialCode) {
        this.socialCode = socialCode;
        return this;
    }

    public Role getRole() {
        return role;
    }

    public UserDto setRole(Role role) {
        this.role = role;
        return this;
    }

    public List<UserCoupon> getCoupons() {
        return coupons;
    }

    public UserDto setCoupons(List<UserCoupon> coupons) {
        this.coupons = coupons;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return Objects.equals(username, userDto.username) &&
                Objects.equals(email, userDto.email) &&
                Objects.equals(phoneNumber, userDto.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, email, phoneNumber);
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", socialId=" + socialId +
                ", socialCode=" + socialCode +
                ", role=" + role +
                '}';
    }
}
