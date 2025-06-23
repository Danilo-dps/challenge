package com.danilodps.store.domain.dto;

import com.danilodps.store.domain.model.TransferHistory;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class StoreDTO {

    private UUID storeId;

    @Column(nullable = false, length = 100)
    @NotBlank(message = "Nome é obrigatório")
    private String storeName;

    @NotBlank(message = "CNPJ é obrigatório")
    private String cnpj;

    @NotBlank(message = "Email é obrigatório")
    private String storeEmail;

    @Column(nullable = false)
    @Builder.Default
    private BigDecimal balance = BigDecimal.ZERO;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<TransferHistory> transferHistory;

    public StoreDTO(UUID storeId, String storeName, String cnpj, String storeEmail, BigDecimal balance) {
        this.storeId = storeId;
        this.storeName = storeName;
        this.cnpj = cnpj;
        this.storeEmail = storeEmail;
        this.balance = BigDecimal.ZERO;
    }
}
