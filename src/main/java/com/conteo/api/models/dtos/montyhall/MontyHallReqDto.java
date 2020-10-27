package com.conteo.api.models.dtos.montyhall;

import com.sun.istack.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class MontyHallReqDto {
    @NotNull List<String> options;
    @NotNull Long transactionId;
}
