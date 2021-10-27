package com.jccornejor.exam.aplazo.creditpayments.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CreditPaymentDTO {

    private Integer paymentNumber;
    private Double amount;
    private Date paymentDate;
}
