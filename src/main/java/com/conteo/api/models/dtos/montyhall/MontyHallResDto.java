package com.conteo.api.models.dtos.montyhall;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MontyHallResDto {
    List<String> options;
    Long transactionId;
    Boolean gameSaved;
    String result;
}
