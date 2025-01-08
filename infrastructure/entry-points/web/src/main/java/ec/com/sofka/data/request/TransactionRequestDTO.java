package ec.com.sofka.data.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionRequestDTO {
    private String id;
    private LocalDateTime date;
    private String type;
    private BigDecimal amount;
    private String description;
    private String destinationAccountId;
    private String sourceAccountId;
    private String status;
    private String aggregateId;
}
