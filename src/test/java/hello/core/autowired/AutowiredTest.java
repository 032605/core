package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption(){
        //TestBean.class를 넣어주면 컴포넌트 스캔하는 것과 같이 TestBean이 Bean으로 등록됨
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }


    static class TestBean {
        //No qualifying bean of type 'hello.core.member.Member'
        //@Autowired(required=true)

        // 메서드 호출 안됨
        @Autowired(required=false)
        public void setNoBean1(Member noBean1){
        // Member는 스프링 빈에 관리되는 대상이 아니므로 아무거나 집어넣은 것임
            System.out.println("noBean1 = " + noBean1);
        }

        //noBean2 = null
        @Autowired
        public void setNoBean2(@Nullable Member noBean2){
            System.out.println("noBean2 = " + noBean2);
        }

        // noBean3 = Optional.empty
        @Autowired
        public void setNoBean3(Optional<Member> noBean3){
            //스피링 빈 값이 없으면 Optional.empty
            System.out.println("noBean3 = " + noBean3);
        }

    }
}
