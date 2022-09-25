package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemebrServiceImpl;
import hello.core.member.MemoryMemberRepositroy;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceimpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService() {
        return new MemebrServiceImpl(memberRepository());
    }

    @Bean
    public static MemoryMemberRepositroy memberRepository() {
        return new MemoryMemberRepositroy();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceimpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy(){
        //return new RateDiscountPolicy();
        return new FixDiscountPolicy();
    }
}