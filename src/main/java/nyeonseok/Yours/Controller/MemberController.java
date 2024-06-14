package nyeonseok.Yours.Controller;

import nyeonseok.Yours.Entity.Member;
import nyeonseok.Yours.Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/signup")
    public String showSignupForm(Model model) {
//        model.addAttribute("member", new Member());
        return "signup"; // signup.html
    }

    @PostMapping("/signup")
    public String submitSignupForm(@ModelAttribute Member member) {
        memberService.saveMember(member);
        return "redirect:/signup-success"; // 회원가입 성공 시 리디렉션할 페이지
    }


    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("loginType", "security-login");
        model.addAttribute("pageName", "Security 로그인");

        return "login";
    }




    @GetMapping("/findMember")
    public String findMemberByUsername(@RequestParam("username") String Username, Model model) {
        Optional<Member> member = memberService.findByUsername(Username);
        if (member.isPresent()) {
            model.addAttribute("member", member.get());
            return "memberDetails"; // memberDetails.html
        } else {
            return "memberNotFound"; // memberNotFound.html
        }
    }
}
