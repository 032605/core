package hello.core.order;

import hello.core.AppConfig;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    void createOrder(){
        Long memberId = 1L; //primitive Type은 NULL을 넣을 수 없음
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "pizza", 10000);

        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

    @Test
    void fieldInjectionTest(){
        Member member = new Member(1L, "meme1", Grade.VIP);
        memberService.join(member);

        OrderServiceimpl orderService = new OrderServiceimpl();
        // 값을 넣어 줄 수 없으므로 java.lang.NullPointerException
        orderService.setMemberRepository(new MemoryMemberRepositroy());
        orderService.setDiscountPolicy(new FixDiscountPolicy());

        orderService.createOrder(1L, "meme1", 100);
    }
}
