package com.jccornejor.exam.aplazo.creditpayments.controller;

import com.jccornejor.exam.aplazo.creditpayments.business.CreditPaymentBusiness;
import com.jccornejor.exam.aplazo.creditpayments.dto.CreditDTO;
import com.jccornejor.exam.aplazo.creditpayments.model.CreditModel;
import com.jccornejor.exam.aplazo.exceptions.ApiException;
import com.jccornejor.exam.aplazo.exceptions.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 *
 */
@Validated
@CrossOrigin
@RestController
public class CreditPaymentsController {

    @Autowired
    private CreditPaymentBusiness creditPaymentBusiness;

    /**
     * ESP:
     * Método que calcula los pagos con base en la cantidad, la tasa de interés y el plazo en semanas dado
     * <p>
     * ENG:
     * Method that calculates payments based on the amount, the interest rate and the term in weeks given
     *
     * @param request ESP:
     *                Objeto que contiene la cantidad, la tasa de interés y el plazo para el calculo de pagos
     *                ENG:
     *                Object containing the amount, the interest rate and the term for calculating payments
     * @return ESP:
     * Lista de pagos calculados
     * ENG:
     * List of calculated payments
     * @throws ApiException
     */
    @PostMapping(value = "/public/credit-payments")
    public ResponseEntity calculateCreditPayments(
            @Valid @RequestBody final CreditDTO request
    ) throws ApiException {
        try {
            return ResponseEntity.ok(
                    this.creditPaymentBusiness.generateCreditPayments(
                            new CreditModel(null,
                                    request.getAmount(),
                                    request.getTerms(),
                                    request.getRate(),
                                    null, null)));
        } catch (BusinessException e) {
            throw new ApiException(e.getMessage(), HttpStatus.PRECONDITION_FAILED.value(), e.getCause());
        }
    }
}
