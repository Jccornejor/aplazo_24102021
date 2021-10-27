package com.jccornejor.exam.aplazo.creditpayments.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

@Validated
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "credits")
public class CreditModel implements Serializable {

    @Id
    @Column(name = "credit_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger creditId;

    @NotNull
    @Min(1)
    @Max(999999)
    @Column(nullable = false, precision = 2)
    private double amount;

    @NotNull
    @Min(4)
    @Max(52)
    @Column(name = "payment_number", nullable = false)
    private Integer terms;

    @NotNull
    @Min(1)
    @Max(100)
    @Column(nullable = false, precision = 2)
    private double rate;

    @OneToMany(mappedBy = "credit", cascade = {CascadeType.PERSIST})
    private List<PaymentModel> payments;

    @Column(name = "create_at", nullable = false)
    @ColumnDefault("CURRENT_TIMESTAMP()")
    @CreationTimestamp
    private Timestamp createdAt;
}
