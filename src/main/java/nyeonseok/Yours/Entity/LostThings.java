package nyeonseok.Yours.Entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "lost_things") // 테이블 이름을 명시적으로 지정
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class LostThings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Setter
    @Column(nullable = false)
    private String itemLocation;

    @Setter
    @Column(nullable = false)
    private String selectedPlaceName;

    @Setter
    @Column(nullable = false)
    private String selectedPlaceAddress;

    @Setter
    @Column(nullable = false)
    private String detailContents;

    @Setter
    @Column(nullable = false)
    private String itemName;

    @Setter
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createDate; // 자동으로 생성일 저장

//    // URL 컬럼 추가
//    @Setter
//    @Column(nullable = false, unique = true) // URL은 고유해야 함
//    private String url;
//
//    // URL을 생성하고 설정하는 메서드
//    public void generateUrl() {
//        this.url = "/lostThings/" + this.id;
//    }
}
