package nyeonseok.Yours.Repository;

import nyeonseok.Yours.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {
    Optional<Member> findByUsername(String Username);

    boolean existsByUserId(String userId);
    boolean existsByUsername(String username);

    Optional<Member> findByUserId(String loginId);
}
