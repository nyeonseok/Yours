package nyeonseok.Yours.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nyeonseok.Yours.Entity.Member;
import nyeonseok.Yours.Entity.MemberRole;


@Getter
@Setter
@NoArgsConstructor

public class JoinRequest {


    private String userId;


    private String password;
    private String passwordCheck;


    private String username;

    // 비밀번호 암호화 X
    public Member toEntity() {
        return Member.builder()
                .userId(this.userId)
                .password(this.password)
                .username(this.username)
                .role(MemberRole.USER)
                .build();
    }


}
