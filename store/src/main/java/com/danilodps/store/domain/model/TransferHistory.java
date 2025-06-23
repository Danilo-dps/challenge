package com.danilodps.store.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_transfer_history")
public class TransferHistory implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(columnDefinition = "UUID", updatable = false, nullable = false)
    private UUID transferId;
    private LocalDateTime whenDidItHappen;
    private String fromEmail;
    private String operationType;
    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    public TransferHistory(LocalDateTime whenDidItHappen, String fromEmail, String operationType, BigDecimal amount, Store store) {
        this.whenDidItHappen = whenDidItHappen;
        this.fromEmail = fromEmail;
        this.operationType = operationType;
        this.amount = amount;
        this.store = store;
    }
}
