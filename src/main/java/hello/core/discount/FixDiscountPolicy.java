package hello.core.discount;

import hello.core.order.Order;

public class FixDiscountPolicy implements DiscountPolicy {
    @Override
    public int discount(Order order) {
        return order.getPrice() - 1000;
    }
}
