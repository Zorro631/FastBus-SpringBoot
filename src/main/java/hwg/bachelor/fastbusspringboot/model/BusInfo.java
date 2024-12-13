package hwg.bachelor.fastbusspringboot.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "businfo")
public class BusInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "businfoid")
    private Integer busInfoId;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate destinationDate;

    @Column(nullable = false)
    private LocalTime startTime;

    @Column(nullable = false)
    private LocalTime destinationTime;

//    @ManyToOne
//    @JoinColumn(name = "route_id", nullable = false, foreignKey = @ForeignKey(name = "fk_route"))
//    private BusRoutes route; // Assuming Route is another entity

    @Column(nullable = false)
    private Integer availableSeats;

    @ManyToOne // Beziehung zur BusRoutes-Klasse
    @JoinColumn(name = "route_id", referencedColumnName = "routeId", nullable = false, foreignKey = @ForeignKey(name = "fk_route"))
    private Routes route; // Bezug zur BusRoutes-Klasse
}

