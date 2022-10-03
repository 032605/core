package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepositroy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextSameBeanFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);

    @Test
    @DisplayName("타입으로 조회 시 같은 타입이 둘 이상있으면 중복 오류가 발생한다.")
    void FindBeanByTypeDuplicate() {
        //MemberRepository bean = ac.getBean(MemberRepository.class); //NoUniqueBeanDefinitionException
        assertThrows(NoUniqueBeanDefinitionException.class,
                () -> ac.getBean(MemberRepository.class));
    }

    @Test
    @DisplayName("타입으로 조회 시 같은 타입이 둘 이상 있으면 빈 이름을 지정하면 된다")
    void findBeanByName() {
        // 디테일하게 테스트 시 memberRepository1 리턴 인스턴스에 파라미터 값 넣어서 내부 값 꺼내서 검증하는 방법(지금은 스프링 믿고 가자)
        MemberRepository memberRepository = ac.getBean("memberRepository1", MemberRepository.class);
        org.assertj.core.api.Assertions.assertThat(memberRepository).isInstanceOf(MemberRepository.class);
    }

    @Test
    @DisplayName("특정 타입을 모두 조회하기")
    void findALLBeanByType(){
        //Autowired로 자동주입할 때 이런 기능이 다 적용이 됨
        Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + " value = " + beansOfType.get(key)) ;
        }

        System.out.println("beansOfType = " + beansOfType);
        org.assertj.core.api.Assertions.assertThat(beansOfType.size()).isEqualTo(2);

    }
    

    @Configuration
    static class SameBeanConfig {
        //static : class 안에서 class를 쓴다는 것은 내부에서만 사용하겠다는 뜻

        //아래와 같이 Bean의 이름이 다르고 객체의 인스턴스 가입이 같을 수 있음.
        // 파라미터로 "10", "1000"를 보내면 한번에 성능을 N개 씩 저장하는 레포지터리로 설정할 때
        @Bean
        public MemberRepository memberRepository1() {
            return new MemoryMemberRepositroy();
        }

        @Bean
        public MemberRepository memberRepository2() {
            return new MemoryMemberRepositroy();
        }
    }

}
