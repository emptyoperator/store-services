package org.leniv.distributed.systems.order.dto;

import lombok.Data;

@Data
public class PaymentCardDto {
    private String cardNumber;
    private String expirationDate;
    private String cvv;
}
