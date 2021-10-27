package com.jccornejor.exam.aplazo.creditpayments.repository;

import com.jccornejor.exam.aplazo.creditpayments.model.CreditModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface CreditPaymentRepository extends CrudRepository<CreditModel, BigInteger> {
}
