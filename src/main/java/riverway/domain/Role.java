package riverway.domain;

import java.util.Arrays;
import java.util.List;

public enum Role {

    SELLER(Arrays.asList("ROLE_ADMIN", "ROLE_USER")),
    CONSUMER(Arrays.asList("ROLE_USER"));

    private List<String> roleName;

    Role(List<String> roleName) {
        this.roleName = roleName;
    }

    public static boolean hasAdmin(Role role){
        return role.roleName.stream().anyMatch(r-> r.equals("ROLE_ADMIN"));
    }
}
