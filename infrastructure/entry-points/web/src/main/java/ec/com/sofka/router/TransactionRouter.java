package ec.com.sofka.router;

import ec.com.sofka.data.request.AccountRequestDTO;
import ec.com.sofka.data.request.TransactionRequestDTO;
import ec.com.sofka.data.response.TransactionResponseDTO;
import ec.com.sofka.handler.TransactionHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Tag(name = "Transaction Management", description = "Endpoints for managing transactions")
@Configuration
public class TransactionRouter {

    private final TransactionHandler transactionHandler;

    public TransactionRouter(TransactionHandler transactionHandler) {
        this.transactionHandler = transactionHandler;
    }

    @Bean
    @RouterOperations({
            @RouterOperation(
                    path = "/transactions/create",
                    method = RequestMethod.POST,
                    operation = @Operation(
                            tags = {"transactions"},
                            operationId = "createTransaction",
                            summary = "Create a new transaction",
                            description = "Add a new transaction to the system",
                            requestBody = @RequestBody(
                                    description = "Transaction details",
                                    required = true,
                                    content = @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = TransactionRequestDTO.class)
                                    )
                            ),
                            responses = {
                                    @ApiResponse(
                                            responseCode = "201",
                                            description = "Transaction created successfully",
                                            content = @Content(
                                                    mediaType = "application/json",
                                                    schema = @Schema(implementation = TransactionResponseDTO.class)
                                            )),
                                    @ApiResponse(responseCode = "400", description = "Invalid input data")
                            }
                    )
            ),
            @RouterOperation(
                    path = "/transactions/id",
                    method = RequestMethod.POST,
                    operation = @Operation(
                            tags = {"transactions"},
                            operationId = "getTransactionById",
                            summary = "Retrieve a transaction by ID",
                            description = "Get details of a transaction by its unique ID",
                            requestBody = @RequestBody(
                                    description = "Transaction details",
                                    required = true,
                                    content = @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = TransactionRequestDTO.class)
                                    )
                            ),
                            responses = {
                                    @ApiResponse(
                                            responseCode = "200",
                                            description = "Transaction retrieved successfully",
                                            content = @Content(
                                                    mediaType = "application/json",
                                                    schema = @Schema(implementation = TransactionResponseDTO.class)
                                            )),
                                    @ApiResponse(responseCode = "404", description = "Transaction not found")
                            }
                    )
            ),
            @RouterOperation(
                    path = "/transactions",
                    method = RequestMethod.GET,
                    operation = @Operation(
                            tags = {"transactions"},
                            operationId = "getAllCustomers",
                            summary = "Retrieve all transactions",
                            description = "Get a list of all transactions in the system",
                            responses = {
                                    @ApiResponse(
                                            responseCode = "200",
                                            description = "Transactions retrieved successfully",
                                            content = @Content(
                                                    mediaType = "application/json",
                                                    schema = @Schema(implementation = TransactionResponseDTO[].class)
                                            ))
                            }
                    )
            ),
            @RouterOperation(
                    path = "/transactions/account/destination",
                    method = RequestMethod.POST,
                    operation = @Operation(
                            tags = {"transactions"},
                            operationId = "getTransactionsByDestinationAccountId",
                            summary = "Retrieve transactions by destination account ID",
                            description = "Get transactions linked to a specific destination account",
                            requestBody = @RequestBody(
                                    description = "Account details",
                                    required = true,
                                    content = @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = AccountRequestDTO.class)
                                    )
                            ),
                            responses = {
                                    @ApiResponse(
                                            responseCode = "200",
                                            description = "Transactions retrieved successfully",
                                            content = @Content(
                                                    mediaType = "application/json",
                                                    schema = @Schema(implementation = TransactionResponseDTO[].class)
                                            )),
                                    @ApiResponse(responseCode = "404", description = "Account not found")
                            }
                    )
            ),
            @RouterOperation(
                    path = "/transactions/account/source",
                    method = RequestMethod.POST,
                    operation = @Operation(
                            tags = {"transactions"},
                            operationId = "getTransactionsBySourceAccountId",
                            summary = "Retrieve transactions by source account ID",
                            description = "Get transactions linked to a specific source account",
                            requestBody = @RequestBody(
                                    description = "Account details",
                                    required = true,
                                    content = @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = AccountRequestDTO.class)
                                    )
                            ),
                            responses = {
                                    @ApiResponse(
                                            responseCode = "200",
                                            description = "Transactions retrieved successfully",
                                            content = @Content(
                                                    mediaType = "application/json",
                                                    schema = @Schema(implementation = TransactionResponseDTO[].class)
                                            )),
                                    @ApiResponse(responseCode = "404", description = "Account not found")
                            }
                    )
            ),
            @RouterOperation(
                    path = "/transactions/date",
                    method = RequestMethod.POST,
                    operation = @Operation(
                            tags = {"transactions"},
                            operationId = "getTransactionsByDate",
                            summary = "Retrieve transactions by date",
                            description = "Get transactions made on a specific date",
                            requestBody = @RequestBody(
                                    description = "Transaction details",
                                    required = true,
                                    content = @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = TransactionRequestDTO.class)
                                    )
                            ),
                            responses = {
                                    @ApiResponse(
                                            responseCode = "200",
                                            description = "Transactions retrieved successfully",
                                            content = @Content(
                                                    mediaType = "application/json",
                                                    schema = @Schema(implementation = TransactionResponseDTO[].class)
                                            ))
                            }
                    )
            ),
            @RouterOperation(
                    path = "/transactions/type",
                    method = RequestMethod.POST,
                    operation = @Operation(
                            tags = {"transactions"},
                            operationId = "getTransactionsByType",
                            summary = "Retrieve transactions by type",
                            description = "Get transactions of a specific type",
                            requestBody = @RequestBody(
                                    description = "Transaction details",
                                    required = true,
                                    content = @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = TransactionRequestDTO.class)
                                    )
                            ),
                            responses = {
                                    @ApiResponse(
                                            responseCode = "200",
                                            description = "Transactions retrieved successfully",
                                            content = @Content(
                                                    mediaType = "application/json",
                                                    schema = @Schema(implementation = TransactionResponseDTO[].class)
                                            )),
                                    @ApiResponse(responseCode = "404", description = "Transaction type not found")
                            }
                    )
            ),
            @RouterOperation(
                    path = "/transactions",
                    method = RequestMethod.POST,
                    operation = @Operation(
                            tags = {"transactions"},
                            operationId = "updateTransaction",
                            summary = "Update a transaction",
                            description = "Update details of an existing transaction",
                            requestBody = @RequestBody(
                                    description = "Updated transaction details",
                                    required = true,
                                    content = @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = TransactionRequestDTO.class)
                                    )
                            ),
                            responses = {
                                    @ApiResponse(responseCode = "200", description = "Transaction updated successfully"),
                                    @ApiResponse(responseCode = "400", description = "Invalid input data"),
                                    @ApiResponse(responseCode = "404", description = "Transaction not found")
                            }
                    )
            ),
            @RouterOperation(
                    path = "/transactions",
                    method = RequestMethod.POST,
                    operation = @Operation(
                            tags = {"transactions"},
                            operationId = "deleteTransaction",
                            summary = "Delete a transaction",
                            description = "Remove a transaction from the system by its ID",
                            requestBody = @RequestBody(
                                    description = "Deleted transaction details",
                                    required = true,
                                    content = @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = TransactionRequestDTO.class)
                                    )
                            ),
                            responses = {
                                    @ApiResponse(responseCode = "204", description = "Transaction deleted successfully"),
                                    @ApiResponse(responseCode = "404", description = "Transaction not found")
                            }
                    )
            )
    })
    public RouterFunction<ServerResponse> transactionRoutes() {
        return route(POST("/transactions/create"), this::createTransaction)
                .andRoute(POST("/transactions/id"), this::getTransactionById)
                .andRoute(GET("/transactions"), this::getAllTransactions)
                .andRoute(POST("/transactions/account/destination"), this::getTransactionsByDestinationAccountId)
                .andRoute(POST("/transactions/account/source"), this::getTransactionsBySourceAccountId)
                .andRoute(POST("/transactions/date"), this::getTransactionsByDate)
                .andRoute(POST("/transactions/type"), this::getTransactionsByType)
                .andRoute(PUT("/transactions"), this::updateTransaction)
                .andRoute(DELETE("/transactions"), this::deleteTransaction);
    }

    private Mono<ServerResponse> createTransaction(ServerRequest request) {
        return request.bodyToMono(TransactionRequestDTO.class)
                .flatMap(transactionHandler::createTransaction)
                .flatMap(transactionResponseDTO -> ServerResponse.status(HttpStatus.CREATED)
                        .contentType(MediaType.APPLICATION_JSON).bodyValue(transactionResponseDTO));
    }

    private Mono<ServerResponse> getTransactionById(ServerRequest request) {
        return request.bodyToMono(TransactionRequestDTO.class)
                .flatMap(transactionHandler::getTransactionById)
                .flatMap(transactionResponseDTO -> ServerResponse.status(HttpStatus.FOUND)
                        .contentType(MediaType.APPLICATION_JSON).bodyValue(transactionResponseDTO))
                .switchIfEmpty(ServerResponse.status(HttpStatus.NOT_FOUND).build());
    }

    private Mono<ServerResponse> getAllTransactions(ServerRequest request) {
        return transactionHandler.getAllTransactions()
                .collectList()
                .flatMap(transactionResponseDTOs -> ServerResponse.status(transactionResponseDTOs.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON).bodyValue(transactionResponseDTOs));
    }

    private Mono<ServerResponse> getTransactionsByDestinationAccountId(ServerRequest request) {
        return request.bodyToMono(AccountRequestDTO.class)
                .flatMapMany(transactionHandler::getTransactionsByDestinationAccount)
                .collectList()
                .flatMap(transactionResponseDTOs -> ServerResponse.status(transactionResponseDTOs.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.FOUND)
                        .contentType(MediaType.APPLICATION_JSON).bodyValue(transactionResponseDTOs));
    }

    private Mono<ServerResponse> getTransactionsBySourceAccountId(ServerRequest request) {
        return request.bodyToMono(AccountRequestDTO.class)
                .flatMapMany(transactionHandler::getTransactionsBySourceAccount)
                .collectList()
                .flatMap(transactionResponseDTOs -> ServerResponse.status(transactionResponseDTOs.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.FOUND)
                        .contentType(MediaType.APPLICATION_JSON).bodyValue(transactionResponseDTOs));
    }

    private Mono<ServerResponse> getTransactionsByDate(ServerRequest request) {
        return request.bodyToMono(TransactionRequestDTO.class)
                .flatMapMany(transactionHandler::getTransactionsByDate)
                .collectList()
                .flatMap(transactionResponseDTOs -> ServerResponse.status(transactionResponseDTOs.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.FOUND)
                        .contentType(MediaType.APPLICATION_JSON).bodyValue(transactionResponseDTOs));
    }

    private Mono<ServerResponse> getTransactionsByType(ServerRequest request) {
        return request.bodyToMono(TransactionRequestDTO.class)
                .flatMapMany(transactionHandler::getTransactionsByType)
                .collectList()
                .flatMap(transactionResponseDTOs -> ServerResponse.status(transactionResponseDTOs.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.FOUND)
                        .contentType(MediaType.APPLICATION_JSON).bodyValue(transactionResponseDTOs));
    }

    private Mono<ServerResponse> updateTransaction(ServerRequest request) {
        return request.bodyToMono(TransactionRequestDTO.class)
                .flatMap(transactionHandler::updateTransaction)
                .flatMap(transactionResponseDTO -> ServerResponse.status(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON).bodyValue(transactionResponseDTO))
                .switchIfEmpty(ServerResponse.status(HttpStatus.NOT_FOUND).build());
    }

    private Mono<ServerResponse> deleteTransaction(ServerRequest request) {
        return request.bodyToMono(TransactionRequestDTO.class)
                .flatMap(transactionHandler::deleteTransaction)
                .then(ServerResponse.noContent().build())
                .onErrorResume(e -> ServerResponse.status(HttpStatus.NOT_FOUND).build());
    }
}
