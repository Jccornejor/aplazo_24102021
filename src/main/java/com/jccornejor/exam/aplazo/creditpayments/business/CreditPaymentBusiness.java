package com.jccornejor.exam.aplazo.creditpayments.business;

import com.jccornejor.exam.aplazo.creditpayments.dto.CreditPaymentDTO;
import com.jccornejor.exam.aplazo.creditpayments.model.CreditModel;
import com.jccornejor.exam.aplazo.exceptions.BusinessException;

import java.util.List;

public interface CreditPaymentBusiness {
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
    List<CreditPaymentDTO> generateCreditPayments(final CreditModel credit) throws BusinessException;
}
