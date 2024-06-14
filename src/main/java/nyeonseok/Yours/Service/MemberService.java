package nyeonseok.Yours.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
//import nyeonseok.Yours.Dto.JoinRequest;
import nyeonseok.Yours.Dto.JoinRequest;
import nyeonseok.Yours.Dto.LoginRequest;
import nyeonseok.Yours.Entity.Member;
import nyeonseok.Yours.Repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public boolean checkLoginIdDuplicate(String loginId) {
        return memberRepository.existsByUserId(loginId);
    }

    /**
     * nickname 중복 체크
     * 회원가입 기능 구현 시 사용
     * 중복되면 true return
     */
    public boolean checkNicknameDuplicate(String username) {
        return memberRepository.existsByUsername(username);
    }


    public void join(JoinRequest req) {
        memberRepository.save(req.toEntity());
    }

//    public void join2(JoinRequest req) {
//        memberRepository.save(req.toEntity(encoder.encode(req.getPassword())));
//    }

    public Member login(LoginRequest req) {
        Optional<Member> optionalUser = memberRepository.findByUserId(req.getUserId());

        // loginId와 일치하는 User가 없으면 null return
        if(optionalUser.isEmpty()) {
            return null;
        }

        Member member = optionalUser.get();

        // 찾아온 User의 password와 입력된 password가 다르면 null return
        if(!member.getPassword().equals(req.getPassword())) {
            return null;
        }

        return member;
    }

    public Member getLoginUserByLoginId(String userId) {
        if(userId == null) return null;

        Optional<Member> optionalUser = memberRepository.findByUsername(userId);
        if(optionalUser.isEmpty()) return null;

        return optionalUser.get();
    }

    public void saveMember(Member member) {
        memberRepository.save(member);
    }

    public Optional<Member> findByUsername(String Username) {
        return memberRepository.findByUsername(Username);
    }


}
