package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepositroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

    // 컴포넌트 스캔과 충돌에서 중복 테스트 코드
    /*
    @Bean(name = "memoryMemberRepositroy")
    MemberRepository memberRepository() {
        return new MemoryMemberRepositroy();
    }
    */
}
