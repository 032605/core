package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemebrServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanName(){
        MemberService memberService = ac.getBean("memberService", MemberService.class); //ac.getBean(빈이름, 타입)
        //System.out.println("memberService = " + memberService);
        //System.out.println("memberService.getClass() = " +memberService.getClass());

        assertThat(memberService).isInstanceOf(MemebrServiceImpl.class); //memberService가 MemebrServiceImpl의 인스턴스인지
    }

    @Test
    @DisplayName("이름없이 타입으로만 조회")
    void findBeanType(){
        MemberService memberService = ac.getBean(MemberService.class); //ac.getBean(타입)
        assertThat(memberService).isInstanceOf(MemebrServiceImpl.class); //memberService가 MemebrServiceImpl의 인스턴스인지
    }

    @Test
    @DisplayName("구체타입으로 조회")
    void findBeanName2(){
        // 스프링에 등록된 인스턴스의 타입을 보기 때문에 인터페이스가 아닌 구체화를 적어주어도 됨
        // 하지만 역할과 구현을 분리하고 역할에 의존해야 한다. 즉, 다음과 같은 코드는 이상적이지 않다. (유연성이 떨어짐)
        MemberService memberService = ac.getBean("memberService", MemebrServiceImpl.class); //ac.getBean(빈이름, 타입)
        assertThat(memberService).isInstanceOf(MemebrServiceImpl.class); //memberService가 MemebrServiceImpl의 인스턴스인지
    }

    @Test
    @DisplayName("빈 이름으로 조회x")
    void findBeanNameX() {
        //ac.getBean("xxxx", MemberService.class);   //NoSuchBeanDefinitionException
        org.junit.jupiter.api.Assertions.assertThrows(NoSuchBeanDefinitionException.class,  //다음과 같은 에러가 터져야 성공
                () -> ac.getBean("xxxx", MemberService.class)); //이 로직을 실행하면
    }
}
