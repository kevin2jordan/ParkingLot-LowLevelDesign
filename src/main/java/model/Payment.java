package model;

import lombok.Getter;
import lombok.NonNull;
import model.enums.PaymentStatus;

import java.util.Date;
import java.util.UUID;


public class Payment {

    private final String paymentId;
    private final Date paymentDate;
    private final Double totalCost;
    private final String ticketId;
    @Getter
    private PaymentStatus paymentStatus;

    public Payment(@NonNull final String ticketId, @NonNull final double amount) {
        this.paymentId = UUID.randomUUID().toString();
        this.totalCost = amount;
        this.paymentDate = new Date();
        this.ticketId = ticketId;
        this.paymentStatus = PaymentStatus.PENDING;
    }

    public void makePayment() {
        this.paymentStatus = PaymentStatus.SUCCESS;
    }
}
