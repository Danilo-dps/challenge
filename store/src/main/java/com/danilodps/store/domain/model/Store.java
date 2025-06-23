package com.danilodps.store.domain.model;

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
        name = "tb_store",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "email", name = "uk_store_email"),
                @UniqueConstraint(columnNames = "cnpj", name = "uk_store_cnpj")
        }
)
public class Store implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(columnDefinition = "UUID", updatable = false, nullable = false)
    private UUID storeId;

    @Column(nullable = false, length = 100)
    private String storeName;

    @Column(nullable = false, unique = true, length = 100)
    private String cnpj;

    @Column(nullable = false, unique = true, length = 50)
    private String storeEmail;

    @Column(nullable = false)
    @Builder.Default
    private BigDecimal balance = BigDecimal.ZERO;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<TransferHistory> transferHistory;

    public Store(UUID storeId, String storeName, String cnpj, String storeEmail, BigDecimal balance) {
        this.storeId = storeId;
        this.storeName = storeName;
        this.cnpj = cnpj;
        this.storeEmail = storeEmail;
        this.balance = BigDecimal.ZERO;
    }
}
