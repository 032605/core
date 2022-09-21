package hello.core.discount;

import hello.core.order.Order;

public interface DiscountPolicy {

    int discount(Order order);
}
