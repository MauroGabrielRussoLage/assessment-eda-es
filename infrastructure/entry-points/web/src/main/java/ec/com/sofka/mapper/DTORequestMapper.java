package ec.com.sofka.mapper;

import ec.com.sofka.account.Account;
import ec.com.sofka.aggregate.Customer;
import ec.com.sofka.data.request.*;
import ec.com.sofka.gateway.dto.AccountDTO;
import ec.com.sofka.gateway.dto.CustomerDTO;
import ec.com.sofka.gateway.dto.TransactionDTO;
import ec.com.sofka.transaction.Transaction;
import org.springframework.beans.BeanUtils;
import reactor.core.publisher.Mono;

import java.util.function.Function;

public class DTORequestMapper {

    public static final Function<Mono<AccountDTO>, Mono<AccountRequestDTO>> toAccountRequestDTO = account ->
            account.map(accountEntity -> {
                AccountRequestDTO accountDTO = new AccountRequestDTO();
                BeanUtils.copyProperties(accountEntity, accountDTO);
                return accountDTO;
            });

    public static final Function<Mono<CustomerDTO>, Mono<CustomerRequestDTO>> toCustomerRequestDTO = customer ->
            customer.map(customerEntity -> {
                CustomerRequestDTO customerDTO = new CustomerRequestDTO();
                BeanUtils.copyProperties(customerEntity, customerDTO);
                return customerDTO;
            });

    public static final Function<Mono<TransactionDTO>, Mono<TransactionRequestDTO>> toTransactionRequestDTO = transaction ->
            transaction.map(transactionEntity -> {
                TransactionRequestDTO transactionDTO = new TransactionRequestDTO();
                BeanUtils.copyProperties(transactionEntity, transactionDTO);
                return transactionDTO;
            });

    public static final Function<Mono<AccountRequestDTO>, Mono<AccountDTO>> toAccount = accountDTO ->
            accountDTO.map(DTO -> {
                AccountDTO account = new AccountDTO();
                BeanUtils.copyProperties(DTO, account);
                return account;
            });

    public static final Function<Mono<CustomerRequestDTO>, Mono<CustomerDTO>> toCustomer = customerDTO ->
            customerDTO.map(DTO -> {
                CustomerDTO customer = new CustomerDTO();
                BeanUtils.copyProperties(DTO, customer);
                return customer;
            });

    public static final Function<Mono<TransactionRequestDTO>, Mono<TransactionDTO>> toTransaction = transactionDTO ->
            transactionDTO.map(DTO -> {
                TransactionDTO transaction = new TransactionDTO();
                BeanUtils.copyProperties(DTO, transaction);
                return transaction;
            });
}