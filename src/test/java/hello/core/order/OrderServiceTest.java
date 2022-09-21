package hello.core.order;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemebrServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Or;

public class OrderServiceTest {

    MemberService memberService = new MemebrServiceImpl();
    OrderService orderService = new OrderServiceimpl();

    @Test
    void createOrder(){
        Long memberId = 1L; //primitive Type은 NULL을 넣을 수 없음
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "pizza", 5000);

        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}
