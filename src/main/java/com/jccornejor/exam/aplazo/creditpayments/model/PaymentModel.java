package com.jccornejor.exam.aplazo.creditpayments.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;

@Validated
@Getter
@Setter
@Entity
@Table(name = "payments")
public class PaymentModel implements Serializable {
    @Id
    @Column(name = "payment_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger paymentId;

    @NotNull
    @Min(1)
    @Column(name = "payment_number", nullable = false)
    private Integer paymentNumber;

    @NotNull
    @Column(nullable = false, precision = 2)
    private Double amount;

    @NotNull
    @Column(name = "paymnet_date", nullable = false)
    private Date paymentDate;

    @ManyToOne
    @JoinColumn(name = "credit_id", nullable = false, updatable = false)
    private CreditModel credit;

    @Column(name = "create_at", nullable = false)
    @ColumnDefault("CURRENT_TIMESTAMP()")
    @CreationTimestamp
    private Timestamp createdAt;
}
