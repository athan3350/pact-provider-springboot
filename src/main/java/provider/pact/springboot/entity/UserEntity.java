package provider.pact.springboot.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "USER_TABLE")
@Getter
@Setter
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "OWNER_NAME", nullable = false)
    private String ownerName;
    @Column(name = "OWNER_EMAIL", nullable = false)
    private String ownerEmail;
    private String phone;
    private String password;

}
