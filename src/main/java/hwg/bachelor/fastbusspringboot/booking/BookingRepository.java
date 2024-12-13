package hwg.bachelor.fastbusspringboot.booking;

import hwg.bachelor.fastbusspringboot.model.Booking;
import hwg.bachelor.fastbusspringboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    // Finde die Buchung mit der höchsten bookingId für einen bestimmten Kunden
    Optional<Booking> findTopByCustomerOrderByBookingIdDesc(User customer);

    Optional<List<Booking>>  findAllByCustomer(User user);
}
