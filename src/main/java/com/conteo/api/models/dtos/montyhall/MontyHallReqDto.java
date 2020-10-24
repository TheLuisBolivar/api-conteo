package com.conteo.api.models.dtos.montyhall;

import lombok.Data;

import java.util.List;

@Data
public class MontyHallReqDto {
    List<String> options;
    Long transactionId;
}
