package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemebrServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Autowired  //ac.getBen(MemberRepository.class) 와 같은 동작함
    public MemebrServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {

        return memberRepository.findById(memberId);
    }

    //테스트 용
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }

}
