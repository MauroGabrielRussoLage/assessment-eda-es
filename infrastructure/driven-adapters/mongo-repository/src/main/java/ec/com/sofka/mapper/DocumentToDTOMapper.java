package ec.com.sofka.mapper;

import ec.com.sofka.account.Account;
import ec.com.sofka.aggregate.Customer;
import ec.com.sofka.data.*;
import ec.com.sofka.gateway.dto.AccountDTO;
import ec.com.sofka.gateway.dto.CustomerDTO;
import ec.com.sofka.gateway.dto.TransactionDTO;
import ec.com.sofka.transaction.Transaction;
import org.springframework.beans.BeanUtils;
import reactor.core.publisher.Mono;

import java.util.function.Function;

public class DocumentToDTOMapper {
    public static final Function<Mono<AccountDocument>, Mono<AccountDTO>> toAccount = account ->
            account.map(accountDocument -> {
                AccountDTO accountModel = new AccountDTO();
                BeanUtils.copyProperties(accountDocument, accountModel);
                return accountModel;
            });

    public static final Function<Mono<CustomerDocument>, Mono<CustomerDTO>> toCustomer = customer ->
            customer.map(customerDocument -> {
                CustomerDTO customerModel = new CustomerDTO();
                BeanUtils.copyProperties(customerDocument, customerModel);
                return customerModel;
            });

    public static final Function<Mono<TransactionDocument>, Mono<TransactionDTO>> toTransaction = transaction ->
            transaction.map(transactionDocument -> {
                TransactionDTO transactionModel = new TransactionDTO();
                BeanUtils.copyProperties(transactionDocument, transactionModel);
                return transactionModel;
            });
}
