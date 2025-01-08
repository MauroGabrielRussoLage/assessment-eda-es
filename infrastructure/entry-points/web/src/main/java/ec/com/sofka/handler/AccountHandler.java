package ec.com.sofka.handler;

import ec.com.sofka.UC.create.CreateAccountUseCase;
import ec.com.sofka.UC.delete.DeleteAccountUseCase;
import ec.com.sofka.UC.get.account.GetAccountByIdUseCase;
import ec.com.sofka.UC.get.account.GetAccountsByCustomerIdUseCase;
import ec.com.sofka.UC.get.account.GetAllAccountsUseCase;
import ec.com.sofka.UC.update.UpdateAccountUseCase;
import ec.com.sofka.customException.AlreadyExistsException;
import ec.com.sofka.customException.NotFoundException;
import ec.com.sofka.data.request.AccountRequestDTO;
import ec.com.sofka.data.request.CustomerRequestDTO;
import ec.com.sofka.data.response.AccountResponseDTO;
import ec.com.sofka.mapper.DTORequestMapper;
import ec.com.sofka.mapper.DTOResponseMapper;
import ec.com.sofka.request.AccountRequest;
import ec.com.sofka.request.CustomerRequest;
import ec.com.sofka.response.AccountResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class AccountHandler {
    private final GetAccountByIdUseCase getAccountByIdUseCase;
    private final CreateAccountUseCase createAccountUseCase;
    private final GetAccountsByCustomerIdUseCase getAccountsByCustomerId;
    private final GetAllAccountsUseCase getAllAccountsUseCase;
    private final UpdateAccountUseCase updateAccountUseCase;
    private final DeleteAccountUseCase deleteAccountUseCase;

    public AccountHandler(
            GetAccountByIdUseCase getAccountByIdUseCase,
            CreateAccountUseCase createAccountUseCase,
            GetAccountsByCustomerIdUseCase getAccountsByCustomerId,
            GetAllAccountsUseCase getAllAccountsUseCase,
            UpdateAccountUseCase updateAccountUseCase,
            DeleteAccountUseCase deleteAccountUseCase
    ) {
        this.getAccountByIdUseCase = getAccountByIdUseCase;
        this.createAccountUseCase = createAccountUseCase;
        this.getAccountsByCustomerId = getAccountsByCustomerId;
        this.getAllAccountsUseCase = getAllAccountsUseCase;
        this.updateAccountUseCase = updateAccountUseCase;
        this.deleteAccountUseCase = deleteAccountUseCase;
    }

    public Mono<AccountResponseDTO> getAccountById(AccountRequestDTO accountRequestDTO) {
        return DTOResponseMapper
                .toAccountResponseDTO
                .apply(getAccountByIdUseCase.apply(accountRequestDTO.getId()))
                .onErrorResume(e -> {
                    if (e instanceof NotFoundException) {
                        return Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));
                    }
                    return Mono.error(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error"));
                });
    }

    public Mono<AccountResponse> createAccount(AccountRequestDTO accountRequestDTO) {
        AccountRequest request = new AccountRequest.Builder()
                .withAggregateId(accountRequestDTO.getAggregateId())
                .withStatus(accountRequestDTO.getStatus())
                .withAccountNumber(accountRequestDTO.getAccountNumber())
                .withBalance(accountRequestDTO.getBalance())
                .withAccountType(accountRequestDTO.getAccountType())
                .withCustomerId(accountRequestDTO.getCustomerId())
                .build();
        return createAccountUseCase.apply(Mono.just(request));
    }

    public Flux<AccountResponseDTO> getAccountsByCustomerId(CustomerRequestDTO customerRequestDTO) {
        return getAccountsByCustomerId
                .apply(customerRequestDTO.getId())
                .flatMap(account -> DTOResponseMapper
                        .toAccountResponseDTO
                        .apply(Mono.just(account)))
                .onErrorResume(e -> {
                    if (e instanceof NotFoundException) {
                        return Flux.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
                    }
                    return Flux.error(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error"));
                });
    }

    public Flux<AccountResponseDTO> getAllAccounts() {
        return getAllAccountsUseCase
                .apply()
                .flatMap(account -> DTOResponseMapper
                        .toAccountResponseDTO
                        .apply(Mono.just(account)))
                .onErrorResume(e ->
                        Flux.error(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error")));
    }

    public Mono<AccountResponse> updateAccount(AccountRequestDTO accountRequestDTO) {
        AccountRequest request = new AccountRequest.Builder()
                .withAggregateId(accountRequestDTO.getAggregateId())
                .withStatus(accountRequestDTO.getStatus())
                .withAccountNumber(accountRequestDTO.getAccountNumber())
                .withBalance(accountRequestDTO.getBalance())
                .withAccountType(accountRequestDTO.getAccountType())
                .withCustomerId(accountRequestDTO.getCustomerId())
                .build();
        return updateAccountUseCase.apply(Mono.just(request));
    }

    public Mono<Void> deleteAccount(AccountRequestDTO accountRequestDTO) {
        return deleteAccountUseCase.apply(accountRequestDTO.getId())
                .onErrorResume(e -> {
                    if (e instanceof NotFoundException) {
                        return Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));
                    }
                    return Mono.error(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error"));
                });
    }
}
