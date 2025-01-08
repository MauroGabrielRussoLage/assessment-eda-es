package ec.com.sofka.handler;

import ec.com.sofka.UC.create.CreateTransactionUseCase;
import ec.com.sofka.UC.delete.DeleteTransactionUseCase;
import ec.com.sofka.UC.get.transaction.*;
import ec.com.sofka.UC.update.UpdateTransactionUseCase;
import ec.com.sofka.customException.AlreadyExistsException;
import ec.com.sofka.customException.NotFoundException;
import ec.com.sofka.data.request.AccountRequestDTO;
import ec.com.sofka.data.request.TransactionRequestDTO;
import ec.com.sofka.data.response.TransactionResponseDTO;
import ec.com.sofka.mapper.DTORequestMapper;
import ec.com.sofka.mapper.DTOResponseMapper;
import ec.com.sofka.request.CustomerRequest;
import ec.com.sofka.request.TransactionRequest;
import ec.com.sofka.response.TransactionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class TransactionHandler {
    private final CreateTransactionUseCase createTransactionUseCase;
    private final GetTransactionByIdUseCase getTransactionByIdUseCase;
    private final GetAllTransactionsUseCase getAllTransactionsUseCase;
    private final GetTransactionByBranchUseCase getTransactionByBranchUseCase;
    private final GetTransactionsByDestinationAccountUseCase getTransactionsByDestinationAccountUseCase;
    private final GetTransactionsBySourceAccountUseCase getTransactionsBySourceAccountUseCase;
    private final GetTransactionsByDateUseCase getTransactionsByDateUseCase;
    private final GetTransactionsByTypeUseCase getTransactionsByTypeUseCase;
    private final UpdateTransactionUseCase updateTransactionUseCase;
    private final DeleteTransactionUseCase deleteTransactionUseCase;

    public TransactionHandler(
            CreateTransactionUseCase createTransactionUseCase,
            GetTransactionByIdUseCase getTransactionByIdUseCase,
            GetAllTransactionsUseCase getAllTransactionsUseCase,
            GetTransactionByBranchUseCase getTransactionByBranchUseCase,
            GetTransactionsByDestinationAccountUseCase getTransactionsByDestinationAccountUseCase,
            GetTransactionsBySourceAccountUseCase getTransactionsBySourceAccountUseCase,
            GetTransactionsByDateUseCase getTransactionsByDateUseCase,
            GetTransactionsByTypeUseCase getTransactionsByTypeUseCase,
            UpdateTransactionUseCase updateTransactionUseCase,
            DeleteTransactionUseCase deleteTransactionUseCase
    ) {
        this.createTransactionUseCase = createTransactionUseCase;
        this.getTransactionByIdUseCase = getTransactionByIdUseCase;
        this.getAllTransactionsUseCase = getAllTransactionsUseCase;
        this.getTransactionByBranchUseCase = getTransactionByBranchUseCase;
        this.getTransactionsByDestinationAccountUseCase = getTransactionsByDestinationAccountUseCase;
        this.getTransactionsBySourceAccountUseCase = getTransactionsBySourceAccountUseCase;
        this.getTransactionsByDateUseCase = getTransactionsByDateUseCase;
        this.getTransactionsByTypeUseCase = getTransactionsByTypeUseCase;
        this.updateTransactionUseCase = updateTransactionUseCase;
        this.deleteTransactionUseCase = deleteTransactionUseCase;
    }

    public Mono<TransactionResponse> createTransaction(TransactionRequestDTO transactionRequestDTO) {
        TransactionRequest request =new TransactionRequest.Builder()
                .withAggregateId(transactionRequestDTO.getAggregateId())
                .withAmount(transactionRequestDTO.getAmount())
                .withStatus(transactionRequestDTO.getStatus())
                .withDate(transactionRequestDTO.getDate())
                .withDescription(transactionRequestDTO.getDescription())
                .withType(transactionRequestDTO.getType())
                .withDestinationAccountId(transactionRequestDTO.getDestinationAccountId())
                .withSourceAccountId(transactionRequestDTO.getSourceAccountId())
                .build();
        return createTransactionUseCase.apply(Mono.just(request));
    }

    public Mono<TransactionResponseDTO> getTransactionById(TransactionRequestDTO transactionRequestDTO) {
        return DTOResponseMapper
                .toTransactionResponseDTO
                .apply(getTransactionByIdUseCase
                        .apply(transactionRequestDTO.getId()))
                .onErrorResume(e -> {
                    if (e instanceof NotFoundException) {
                        return Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaction not found"));
                    }
                    return Mono.error(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error"));
                });
    }

    public Flux<TransactionResponseDTO> getAllTransactions() {
        return getAllTransactionsUseCase.apply()
                .flatMap(transaction -> DTOResponseMapper.toTransactionResponseDTO.apply(Mono.just(transaction)))
                .onErrorResume(e -> {
                    return Mono.error(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error fetching all transactions"));
                });
    }

    public Flux<TransactionResponseDTO> getTransactionsByDestinationAccount(AccountRequestDTO accountRequestDTO) {
        return getTransactionsByDestinationAccountUseCase
                .apply(accountRequestDTO.getId())
                .flatMap(transaction -> DTOResponseMapper
                        .toTransactionResponseDTO
                        .apply(Mono.just(transaction)))
                .onErrorResume(e -> {
                    if (e instanceof NotFoundException) {
                        return Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Transactions not found for the destination account"));
                    }
                    return Mono.error(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error"));
                });
    }

    public Flux<TransactionResponseDTO> getTransactionsBySourceAccount(AccountRequestDTO accountRequestDTO) {
        return getTransactionsBySourceAccountUseCase
                .apply(accountRequestDTO.getId())
                .flatMap(transaction -> DTOResponseMapper
                        .toTransactionResponseDTO
                        .apply(Mono.just(transaction)))
                .onErrorResume(e -> {
                    if (e instanceof NotFoundException) {
                        return Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Transactions not found for the source account"));
                    }
                    return Mono.error(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error"));
                });
    }

    public Flux<TransactionResponseDTO> getTransactionsByDate(TransactionRequestDTO transactionRequestDTO) {
        return getTransactionsByDateUseCase
                .apply(transactionRequestDTO.getDate())
                .flatMap(transaction -> DTOResponseMapper
                        .toTransactionResponseDTO
                        .apply(Mono.just(transaction)))
                .onErrorResume(e -> {
                    if (e instanceof NotFoundException) {
                        return Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Transactions not found for the date"));
                    }
                    return Mono.error(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error"));
                });
    }

    public Flux<TransactionResponseDTO> getTransactionsByType(TransactionRequestDTO transactionRequestDTO) {
        return getTransactionsByTypeUseCase
                .apply(transactionRequestDTO.getType())
                .flatMap(transaction -> DTOResponseMapper
                        .toTransactionResponseDTO
                        .apply(Mono.just(transaction)))
                .onErrorResume(e -> {
                    if (e instanceof NotFoundException) {
                        return Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Transactions not found for the type"));
                    }
                    return Mono.error(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error"));
                });
    }

    public Mono<TransactionResponse> updateTransaction(TransactionRequestDTO transactionRequestDTO) {
        TransactionRequest request =new TransactionRequest.Builder()
                .withAmount(transactionRequestDTO.getAmount())
                .withStatus(transactionRequestDTO.getStatus())
                .withDate(transactionRequestDTO.getDate())
                .withDescription(transactionRequestDTO.getDescription())
                .withType(transactionRequestDTO.getType())
                .withDestinationAccountId(transactionRequestDTO.getDestinationAccountId())
                .withSourceAccountId(transactionRequestDTO.getSourceAccountId())
                .build();
        return updateTransactionUseCase.apply(Mono.just(request));
    }

    public Mono<Void> deleteTransaction(TransactionRequestDTO transactionRequestDTO) {
        return deleteTransactionUseCase.apply(transactionRequestDTO.getId())
                .onErrorResume(e -> {
                    if (e instanceof NotFoundException) {
                        return Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaction not found"));
                    }
                    return Mono.error(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error"));
                });
    }

}
