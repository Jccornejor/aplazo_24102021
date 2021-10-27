package com.jccornejor.exam.aplazo.creditpayments.dao.impl;

import com.jccornejor.exam.aplazo.creditpayments.dao.CreditPaymentDAO;
import com.jccornejor.exam.aplazo.creditpayments.model.CreditModel;
import com.jccornejor.exam.aplazo.creditpayments.repository.CreditPaymentRepository;
import com.jccornejor.exam.aplazo.exceptions.DataAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CreditPaymentDAOImpl implements CreditPaymentDAO {

    @Autowired
    private CreditPaymentRepository creditPaymentRepository;

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
    @Override
    @Transactional
    public CreditModel saveCredit(CreditModel credit) throws DataAccessException {
        try {

            return creditPaymentRepository.save(credit);
        } catch (Exception ex) {
            throw new DataAccessException("La información no ha posido ser procesada", 500);
        }
    }
}
