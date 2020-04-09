package com.example.eversis.controller.transfer;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class OrderProductDto {
    @NonNull
    private Integer productId;
    @NonNull
    private Integer quantity;
}
