package com.conteo.api.models.dtos.conteo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GenericRs {
    Boolean status;
    String description;
}
