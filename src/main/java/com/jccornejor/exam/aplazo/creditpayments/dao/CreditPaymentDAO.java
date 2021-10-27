package com.jccornejor.exam.aplazo.creditpayments.dao;

import com.jccornejor.exam.aplazo.creditpayments.model.CreditModel;
import com.jccornejor.exam.aplazo.exceptions.DataAccessException;

public interface CreditPaymentDAO {

    /**
     * ESP:
     * Método que guarda la información de un crédito
     * <p>
     * ENG:
     * Method that stores credit information
     *
     * @param credit ESP:
     *               Objeto que contiene la información del Crédito
     *               ENG:
     *               Object that contains the Credit information
     * @return ESP:
     * Objeto de crédito con el Id Generado
     * ENG:
     * Credit object with the Generated Id
     * @throws DataAccessException ESP:
     *                             Se lanza la excepción si ocurre un error al momento de realizar la operación de guardado
     *                             ENG:
     *                             The exception is thrown if an error occurs while performing the save operation
     */
    CreditModel saveCredit(final CreditModel credit) throws DataAccessException;

}
