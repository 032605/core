package hello.core.scan;

import hello.core.AutoAppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.order.OrderServiceimpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class AutoAppConfigTest {
    
    @Test
    void basicScan(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

        MemberService bean = ac.getBean(MemberService.class);
        assertThat(bean).isInstanceOf(MemberService.class);

        OrderServiceimpl bean1 = ac.getBean(OrderServiceimpl.class);
        MemberRepository memberRepository = bean1.getMemberRepository();
        System.out.println("memberRepository = " + memberRepository);
    }
}
