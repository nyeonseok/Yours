package nyeonseok.Yours.Repository;

import nyeonseok.Yours.Entity.LostThings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LostThingsRepository extends JpaRepository<LostThings, String> {
    Optional<LostThings> findByitemName(String itemName);

    Optional<LostThings> findById(Long id);
}
