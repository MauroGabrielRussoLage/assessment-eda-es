package ec.com.sofka.mapper;

import ec.com.sofka.data.*;
import ec.com.sofka.gateway.dto.AccountDTO;
import ec.com.sofka.gateway.dto.CustomerDTO;
import ec.com.sofka.gateway.dto.TransactionDTO;
import org.springframework.beans.BeanUtils;
import reactor.core.publisher.Mono;

import java.util.function.Function;

public class DTOToDocumentMapper {
    public static final Function<Mono<AccountDTO>, Mono<AccountDocument>> toAccount = account ->
            account.map(accountDTO -> {
                AccountDocument accountDocument = new AccountDocument();
                BeanUtils.copyProperties(accountDTO, accountDocument);
                return accountDocument;
            });

    public static final Function<Mono<CustomerDTO>, Mono<CustomerDocument>> toCustomer = customer ->
            customer.map(customerDTO -> {
                CustomerDocument customerDocument = new CustomerDocument();
                BeanUtils.copyProperties(customerDTO, customerDocument);
                return customerDocument;
            });

    public static final Function<Mono<TransactionDTO>, Mono<TransactionDocument>> toTransaction = transaction ->
            transaction.map(transactionModel -> {
                TransactionDocument transactionDocument = new TransactionDocument();
                BeanUtils.copyProperties(transactionModel, transactionDocument);
                return transactionDocument;
            });
}
