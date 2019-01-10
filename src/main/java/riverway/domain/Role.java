package riverway.domain;

import java.util.Arrays;
import java.util.List;

public enum Role {

    ROLE_ADMIN(Arrays.asList("SELLER", "CONSUMER")),
    ROLE_USER(Arrays.asList("CONSUMER"));

    private List<String> roleName;

    Role(List<String> roleName) {
        this.roleName = roleName;
    }

    public static boolean hasSeller(Role role) {
        return role.roleName.stream().anyMatch(r -> r.equals("SELLER"));
    }
}
