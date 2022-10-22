package hello.core.order;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepositroy;
import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceimplTest {
    
    @Test
    void createOrder() {
        // 순수 자바 코드 테스트
        MemoryMemberRepositroy memberRepositroy = new MemoryMemberRepositroy();
        memberRepositroy.save(new Member(1L, "name", Grade.VIP));

        // 생성자 주입하여 파라미터 빨리 파악 가능
        OrderServiceimpl orderServiceimpl = new OrderServiceimpl(new MemoryMemberRepositroy(), new FixDiscountPolicy());

        //NullPointerException
        //creatOrder가 테스트만 필요하더라도 가짜 메모리레포지터리 (더미라도)를 만들어줘야 하는데 누락
        Order order = orderServiceimpl.createOrder(1L, "itemA", 1000);

        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

}