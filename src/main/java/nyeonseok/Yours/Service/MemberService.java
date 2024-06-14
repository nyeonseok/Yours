package nyeonseok.Yours.Service;

import nyeonseok.Yours.Entity.Member;
import nyeonseok.Yours.Repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public void saveMember(Member member) {
        memberRepository.save(member);
    }

    public Optional<Member> findByUsername(String Username) {
        return memberRepository.findByUsername(Username);
    }
}
