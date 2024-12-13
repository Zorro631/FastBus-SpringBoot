package hwg.bachelor.fastbusspringboot.routes;

import hwg.bachelor.fastbusspringboot.model.Routes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoutesRepository extends JpaRepository<Routes, String> {
    Routes findByDepartureAndDestination(String departure, String destination);

}
