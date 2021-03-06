/*
 * @(#)OrderAmount.java     0.1 2019.12.21
 *
 * Copyright (c) 2019 lxxjn0
 */

package domain.Payment;

public class OrderAmount {
    private static final double ZERO_ORDER_AMOUNT = 0;
    private static final int CHICKEN_DISCOUNT_UNIT = 10;
    private static final double CHICKEN_ADDITIONAL_DISCOUNT = 10000;
    private static final double CASH_ADDITIONAL_DISCOUNT = 0.95;

    public static OrderAmount ZERO = new OrderAmount(ZERO_ORDER_AMOUNT);

    private final double orderAmount;

    public OrderAmount(double orderAmount) {
        isValid(orderAmount);

        this.orderAmount = orderAmount;
    }

    private void isValid(double orderAmount) {
        if (orderAmount < ZERO_ORDER_AMOUNT) {
            throw new IllegalArgumentException("주문 금액이 0원보다 작을수 없습니다.");
        }
    }

    public OrderAmount add(double addOrderAmount) {
        return new OrderAmount(this.orderAmount + addOrderAmount);
    }

    public double getOrderAmount(int chickenAmount, PaymentMethod paymentMethod) {
        final double discountAmount = (chickenAmount / CHICKEN_DISCOUNT_UNIT) * CHICKEN_ADDITIONAL_DISCOUNT;
        if (paymentMethod.isCash()) {
            return ((this.orderAmount - discountAmount) * CASH_ADDITIONAL_DISCOUNT);
        }
        return this.orderAmount - discountAmount;
    }
}
