package hello.core.member;

public class MemebrServiceImpl implements MemberService{

    private final MemberRepository memberRepository = new MemoryMemberRepositroy();

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {

        return memberRepository.findById(memberId);
    }
}
