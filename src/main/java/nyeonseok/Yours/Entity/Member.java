package nyeonseok.Yours.Entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Setter
    @Column(nullable = false,length = 10)
    private String userId ;


    @Setter
    @Column(nullable = false,length = 10)
    private String username;


    @Setter
    @Column(nullable = false,length = 10)
    private String password;

    private MemberRole role;

}
