package hello.core.sigleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void  statefulServiceSingleton() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfing.class);
        StatefulService statefulService = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //ThreadA : A사용자 1만원 주문
        statefulService.order("userA", 10000);
        //ThreadB : B사용자 2만원 주문
        statefulService2.order("userB", 20000);

        //ThreadA : A사용자 주문 금액 조회
        int price = statefulService.getPrice();
        System.out.println("price = " + price);

        Assertions.assertThat(statefulService.getPrice()).isEqualTo(20000);
    }

    static class TestConfing {

        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }

}