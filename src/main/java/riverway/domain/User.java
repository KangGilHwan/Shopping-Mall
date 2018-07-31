package riverway.domain;

import riverway.dto.UserDto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
public class User {

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
}
