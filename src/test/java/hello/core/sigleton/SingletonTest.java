package hello.core.sigleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();
        //1. 조회 : 호출할 때마다 객체를 생성
        MemberService memberService = appConfig.memberService();

        //2. 조회 : 호출할 때마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();

        //참조 값이 다른 것을 확인(JVM 메모리에 계속 객체 생성)
        System.out.println("memberService = " + memberService);
        System.out.println("memberService2 = " + memberService2);

        Assertions.assertThat(memberService).isNotEqualTo(memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest(){
        //new SingletonService();   //싱글톤 적용 new 생성 X

        SingletonService singletonService = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        System.out.println("singletonService = " + singletonService);
        System.out.println("singletonService2 = " + singletonService2);

        Assertions.assertThat(singletonService).isSameAs(singletonService2);    //인스턴스 값 비교
        //same ==
        //equal java에 equals
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer(){
        //AppConfig appConfig = new AppConfig();
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberService memberService = ac.getBean("memberService", MemberService.class);
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        System.out.println("memberService = " + memberService);
        System.out.println("memberService2 = " + memberService2);

        Assertions.assertThat(memberService).isSameAs(memberService2);          //인스턴스 값 비교
    }
}
