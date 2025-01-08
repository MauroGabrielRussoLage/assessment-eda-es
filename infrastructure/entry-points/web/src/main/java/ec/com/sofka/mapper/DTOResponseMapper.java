package ec.com.sofka.mapper;

import ec.com.sofka.account.Account;
import ec.com.sofka.aggregate.Customer;
import ec.com.sofka.data.response.*;
import ec.com.sofka.gateway.dto.AccountDTO;
import ec.com.sofka.gateway.dto.CustomerDTO;
import ec.com.sofka.gateway.dto.TransactionDTO;
import ec.com.sofka.transaction.Transaction;
import org.springframework.beans.BeanUtils;
import reactor.core.publisher.Mono;

import java.util.function.Function;

public class DTOResponseMapper {

    public static final Function<Mono<AccountDTO>, Mono<AccountResponseDTO>> toAccountResponseDTO = account ->
            account.map(accountEntity -> {
                AccountResponseDTO accountDTO = new AccountResponseDTO();
                BeanUtils.copyProperties(accountEntity, accountDTO);
                return accountDTO;
            });

    public static final Function<Mono<CustomerDTO>, Mono<CustomerResponseDTO>> toCustomerResponseDTO = customer ->
            customer.map(customerEntity -> {
                CustomerResponseDTO customerDTO = new CustomerResponseDTO();
                BeanUtils.copyProperties(customerEntity, customerDTO);
                return customerDTO;
            });

    public static final Function<Mono<TransactionDTO>, Mono<TransactionResponseDTO>> toTransactionResponseDTO = transaction ->
            transaction.map(transactionEntity -> {
                TransactionResponseDTO transactionDTO = new TransactionResponseDTO();
                BeanUtils.copyProperties(transactionEntity, transaction);
                return transactionDTO;
            });

    public static final Function<Mono<AccountResponseDTO>, Mono<AccountDTO>> toAccount = accountDTO ->
            accountDTO.map(DTO -> {
                AccountDTO account = new AccountDTO();
                BeanUtils.copyProperties(DTO, account);
                return account;
            });

    public static final Function<Mono<CustomerResponseDTO>, Mono<CustomerDTO>> toCustomer = customerDTO ->
            customerDTO.map(DTO -> {
                CustomerDTO customer = new CustomerDTO();
                BeanUtils.copyProperties(DTO, customer);
                return customer;
            });

    public static final Function<Mono<TransactionResponseDTO>, Mono<TransactionDTO>> toTransaction = transactionDTO ->
            transactionDTO.map(DTO -> {
                TransactionDTO transaction = new TransactionDTO();
                BeanUtils.copyProperties(DTO, transaction);
                return transaction;
            });
}