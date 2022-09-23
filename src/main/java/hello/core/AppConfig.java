package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemebrServiceImpl;
import hello.core.member.MemoryMemberRepositroy;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceimpl;

public class AppConfig {

    public MemberService memberService() {
        return new MemebrServiceImpl(memberRepository());
    }

    private static MemoryMemberRepositroy memberRepository() {
        return new MemoryMemberRepositroy();
    }

    public OrderService orderService() {
        return new OrderServiceimpl(memberRepository(), discountPolicy());
    }

    public DiscountPolicy discountPolicy(){
        //return new RateDiscountPolicy();
        return new FixDiscountPolicy();
    }
}
