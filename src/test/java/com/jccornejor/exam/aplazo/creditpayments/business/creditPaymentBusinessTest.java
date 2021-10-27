package com.jccornejor.exam.aplazo.creditpayments.business;

import com.jccornejor.exam.aplazo.creditpayments.dto.CreditPaymentDTO;
import com.jccornejor.exam.aplazo.creditpayments.model.CreditModel;
import com.jccornejor.exam.aplazo.exceptions.BusinessException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest()
class creditPaymentBusinessTest {

    @Autowired
    private CreditPaymentBusiness creditPaymentBusiness;

    @Test
    void shouldAssertThatContextLoads() {
        assertThat(this.creditPaymentBusiness).isNotNull();
    }

    @Test
    void shouldReturnPaymentsListWhenCreditPaymentsAreNotGenerated() throws Exception {
        // GIVEN
        assertThat(this.creditPaymentBusiness).isNotNull();
        // WHEN
        final List<CreditPaymentDTO> payments = this.creditPaymentBusiness.generateCreditPayments(null);
        // THEN

        assertThat(payments).isNull();
    }

    @Test
    void shouldReturnPaymentsListWhenCreditPaymentsAreGenerated() throws Exception {
        // GIVEN
        final CreditModel toGenerate = new CreditModel(null, 40000.00d, 24, 40.00d, null, null);
        assertThat(this.creditPaymentBusiness).isNotNull();
        // WHEN
        final List<CreditPaymentDTO> payments = this.creditPaymentBusiness.generateCreditPayments(toGenerate);
        // THEN

        assertThat(payments).isNotNull();
        assertThat(payments).isNotEmpty();

        assertThat(payments).isNotNull().isNotEmpty();
    }

    @Test
    void shouldReturnPaymentsListWhenCreditPaymentsAreGeneratedAndEqualsToTerms() throws Exception {
        // GIVEN
        final CreditModel toGenerate = new CreditModel(null, 40000.00d, 24, 40.00d, null, null);
        assertThat(this.creditPaymentBusiness).isNotNull();
        // WHEN
        final List<CreditPaymentDTO> payments = this.creditPaymentBusiness.generateCreditPayments(toGenerate);
        // THEN

        assertThat(payments).isNotNull();
        assertThat(payments).isNotEmpty();
        assertThat(payments.size()).isEqualTo(toGenerate.getTerms());
    }

    @Test
    void shouldReturnAnExceptionWhenAmountIsLessThanOne() throws Exception {
        // GIVEN
        final CreditModel toGenerate = new CreditModel(null, -40000.00d, 24, 40.00d, null, null);

        // WHEN
        final BusinessException exception = assertThrows(BusinessException.class, () -> this.creditPaymentBusiness.generateCreditPayments(toGenerate));
        // THEN
        assertThat(exception.getMessage()).containsIgnoringCase("Amount");
    }

    @Test
    void shouldReturnAnExceptionWhenAmountIsMoreThan999999() throws Exception {
        // GIVEN
        final CreditModel toGenerate = new CreditModel(null, 9999999.00d, 24, 40.00d, null, null);

        // WHEN
        final BusinessException exception = assertThrows(BusinessException.class, () -> this.creditPaymentBusiness.generateCreditPayments(toGenerate));
        // THEN
        assertThat(exception.getMessage()).containsIgnoringCase("Amount");
    }

    @Test
    void shouldReturnAnExceptionWhenTermsIsLessThanFour() throws Exception {
        // GIVEN
        final CreditModel toGenerate = new CreditModel(null, 40000.00d, 2, 40.00d, null, null);
        // WHEN
        final BusinessException exception = assertThrows(BusinessException.class, () -> this.creditPaymentBusiness.generateCreditPayments(toGenerate));
        // THEN
        assertThat(exception.getMessage()).containsIgnoringCase("Terms");
    }

    @Test
    void shouldReturnAnExceptionWhenAmountIsMoreThanFiftyTwo() throws Exception {
        // GIVEN
        final CreditModel toGenerate = new CreditModel(null, 40000.00d, 54, 40.00d, null, null);
        // WHEN
        final BusinessException exception = assertThrows(BusinessException.class, () -> this.creditPaymentBusiness.generateCreditPayments(toGenerate));
        // THEN
        assertThat(exception.getMessage()).containsIgnoringCase("Terms");
    }

    @Test
    void shouldReturnAnExceptionWhenRateIsLessThanOne() throws Exception {
        // GIVEN
        final CreditModel toGenerate = new CreditModel(null, 40000.00d, 24, -40.00d, null, null);
        // WHEN
        final BusinessException exception = assertThrows(BusinessException.class, () -> this.creditPaymentBusiness.generateCreditPayments(toGenerate));
        // THEN
        assertThat(exception.getMessage()).containsIgnoringCase("Rate");
    }

    @Test
    void shouldReturnAnExceptionWhenRateIsMoreThanHundred() throws Exception {
        // GIVEN
        final CreditModel toGenerate = new CreditModel(null, 40000.00d, 24, 400.00d, null, null);

        // WHEN
        final BusinessException exception = assertThrows(BusinessException.class, () -> this.creditPaymentBusiness.generateCreditPayments(toGenerate));
        // THEN
        assertThat(exception.getMessage()).containsIgnoringCase("Rate");
    }
}