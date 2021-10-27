package com.jccornejor.exam.aplazo.creditpayments.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Validated
@ConstructorBinding()
public class CreditDTO {

    @NotNull
    @Min(1)
    @Max(999999)
    private Double amount;
    @NotNull
    @Min(4)
    @Max(52)
    private Integer terms;
    @NotNull
    @Min(1)
    @Max(100)
    private Double rate;

}
