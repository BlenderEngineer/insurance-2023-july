package pot.insurance.manager;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table (
    name = "users"
)
public class User {
    @Id
    @GeneratedValue
    @Column (
        name = "id"
    )
    UUID id;

    @Column (
        name = "username"
    )
    String username;

    @Column (
        name = "password"
    )
    String password;

    @Enumerated (
        EnumType.STRING
    )
    @Column (
        name = "privilege"
    )
    UserPrivilege privilege;


}
