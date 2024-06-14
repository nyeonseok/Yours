package nyeonseok.Yours.Entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Getter
@Table
@NoArgsConstructor(access = AccessLevel.PUBLIC)
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

}
