package com.jccornejor.exam.aplazo.creditpayments.business.impl;

import com.jccornejor.exam.aplazo.creditpayments.business.CreditPaymentBusiness;
import com.jccornejor.exam.aplazo.creditpayments.dao.CreditPaymentDAO;
import com.jccornejor.exam.aplazo.creditpayments.dto.CreditPaymentDTO;
import com.jccornejor.exam.aplazo.creditpayments.model.CreditModel;
import com.jccornejor.exam.aplazo.creditpayments.model.PaymentModel;
import com.jccornejor.exam.aplazo.exceptions.BusinessException;
import com.jccornejor.exam.aplazo.exceptions.DataAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;
import java.util.stream.IntStream;

@Service
public class CreditPaymentBusinessImpl implements CreditPaymentBusiness {

    @Autowired
    private CreditPaymentDAO creditPaymentDAO;

    /**
     * ESP:
     * Método que calcula los Pagos de un crédito
     * <p>
     * ENG:
     * Method that calculates the Payments of a credit
     *
     * @param credit ESP:
     *               Objeto que contiene la cantidad, la tasa de interés y el plazo para el calculo de pagos
     *               ENG:
     *               Object containing the amount, the interest rate and the term for calculating payments
     * @return ESP:
     * Lista de pagos calculados
     * ENG:
     * List of calculated payments
     * @throws BusinessException ESP:
     *                           Excepción lanzada cuando ocurre un error por regla de negocio o guardado de capa de datos
     *                           ENG:
     *                           Exception thrown when a data layer save or business rule error occurs
     */
    @Override
    public ArrayList<CreditPaymentDTO> generateCreditPayments(CreditModel credit) throws BusinessException {
        if (credit == null) {
            return null;
        }
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        final Set<ConstraintViolation<CreditModel>> violations = validator.validate(credit);

        if (!violations.isEmpty()) {
            for (ConstraintViolation<CreditModel> violation : violations) {
                throw new BusinessException(violation.getPropertyPath() + " " + violation.getMessage());
            }
        }

        ArrayList<CreditPaymentDTO> payments = this.generatePayments(credit.getAmount(), credit.getRate(), credit.getTerms());
        ArrayList<PaymentModel> paymentEntities = new ArrayList<>();

        CreditModel finalCredit = credit;
        payments.forEach(creditPaymentDTO -> {
            final PaymentModel payment = new PaymentModel();
            payment.setAmount(creditPaymentDTO.getAmount());
            payment.setPaymentNumber(creditPaymentDTO.getPaymentNumber());
            payment.setPaymentDate(creditPaymentDTO.getPaymentDate());
            payment.setCredit(finalCredit);
            paymentEntities.add(payment);
        });

        credit.setPayments(paymentEntities);

        try {
            credit = this.creditPaymentDAO.saveCredit(credit);
        } catch (DataAccessException e) {
            throw new BusinessException(e.getMessage(), e.getCode(), e.getCause());
        }

        return payments;
    }

    /**
     * ESP:
     * Método que genera los pagos de un crédito
     * <p>
     * ENG:
     * Method that generates credit payments
     *
     * @param amount ESP:
     *               Cantidad que se va a financiar
     *               ENG:
     *               Amount to be financed
     * @param rate   ESP:
     *               Tasa de interés para el crédito
     *               ENG:
     *               Interest rate for credit
     * @param terms  ESP:
     *               Plazo para del financiamiento en semanas
     *               ENG:
     *               Term for financing in weeks
     * @return ESP:
     * Lista de pagos calculados
     * ENG:
     * List of calculated payments
     */
    private ArrayList<CreditPaymentDTO> generatePayments(final double amount, final double rate, final int terms) {
        ArrayList<CreditPaymentDTO> payments = new ArrayList<>();
        final Double paymentAmount = (amount + this.calculateRateAmount(amount, rate)) / terms;
        final Date creditDateToCalculate = Calendar.getInstance().getTime();
        IntStream.rangeClosed(1, terms).forEach(i -> {
            CreditPaymentDTO payment = new CreditPaymentDTO();
            payment.setPaymentNumber(i);
            payment.setAmount(paymentAmount);
            payment.setPaymentDate(this.generatePaymentDate(creditDateToCalculate, i));
            payments.add(payment);
        });
        return payments;
    }

    /**
     * ESP:
     * Método que calcula el monto en base al interés
     * <p>
     * ENG:
     * Method that calculates the amount based on interest
     *
     * @param amount ESP:
     *               Cantidad que se va a financiar
     *               ENG:
     *               Amount to be financed
     * @param rate   ESP:
     *               Tasa de interés para el crédito
     *               ENG:
     *               Interest rate for credit
     * @return ESP:
     * El monto calculado
     * ENG:
     * The calculated amount
     */
    private Double calculateRateAmount(final double amount, final double rate) {
        return (amount * (rate / 100));
    }

    /**
     * ESP:
     * Método que calcula una fecha de un pago
     * ENG:
     * Method that calculates a date of a payment
     *
     * @param paymentDate ESP:
     *                    Fecha base para el calculo
     *                    ENG:
     *                    Base date for calculation
     * @param index       ESP:
     *                    Número de pago a calcular
     *                    ENG:
     *                    PaymentModel number to calculate
     * @return
     */
    private Date generatePaymentDate(final Date paymentDate, final int index) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(paymentDate);
        calendar.add(Calendar.DATE, index * 7);
        return calendar.getTime();
    }
}
