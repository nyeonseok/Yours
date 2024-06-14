package nyeonseok.Yours.Repository;

import nyeonseok.Yours.Entity.LostThings;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LostThingsRepository extends JpaRepository<LostThings, String> {
    Optional<LostThings> findByitemName(String itemName);

}
