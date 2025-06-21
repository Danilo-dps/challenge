package user_common.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "tb_users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "email", name = "uk_user_email"),
                @UniqueConstraint(columnNames = "cpf", name = "uk_user_cpf")
        }
)
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(columnDefinition = "UUID", updatable = false, nullable = false)
    private UUID idUser;

    @Column(nullable = false, length = 100)
    private String fullName;

    @Column(nullable = false, unique = true, length = 100)
    private String cpf;

    @Column(nullable = false, unique = true, length = 50)
    private String userEmail;

    @Column(nullable = false)
    @Builder.Default
    private BigDecimal balance = BigDecimal.ZERO;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<TransferHistory> transferHistory;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<DepositHistory> depositHistory;

    public User(UUID idUser, String fullName, String cpf, String userEmail, BigDecimal balance) {
        this.idUser = idUser;
        this.fullName = fullName;
        this.cpf = cpf;
        this.userEmail = userEmail;
        this.balance = BigDecimal.ZERO;
    }
}