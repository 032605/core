package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepositroy;

public class OrderServiceimpl implements OrderService{
    private MemberRepository memberRepository = new MemoryMemberRepositroy();
    private DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public void order(Order order) {

        Member member = memberRepository.findById(order.getId());

        if(member.getGrade() == Grade.VIP) {
            order.setPrice(order.getPrice() - discountPolicy.discount(order));
        }
    }
}
