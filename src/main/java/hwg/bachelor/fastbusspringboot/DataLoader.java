package hwg.bachelor.fastbusspringboot;

import hwg.bachelor.fastbusspringboot.login.UserRepository;
import hwg.bachelor.fastbusspringboot.model.Routes;
import hwg.bachelor.fastbusspringboot.model.User;
import hwg.bachelor.fastbusspringboot.routes.RoutesRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Component
public class DataLoader {
    @Bean
    CommandLineRunner run(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.findByEmail("test@abc.de").isEmpty()) {
                User user = new User();
                user.setEmail("test@abc.de");
                user.setPassword(passwordEncoder.encode("123456"));
                user.setFirstname("John");
                user.setLastname("Doe");
                user.setBirthDate(LocalDate.parse("2001-12-23"));
                userRepository.save(user);
            }
        };
    }
    @Bean
    @Transactional
    CommandLineRunner initDatabase(RoutesRepository repository) {
        return args -> {
            repository.saveAll(List.of(
                    new Routes("BERMUN", "Berlin", "München", BigDecimal.valueOf(7.00), BigDecimal.valueOf(20.00)),
                    new Routes("BERFRA", "Berlin", "Frankfurt", BigDecimal.valueOf(6.30), BigDecimal.valueOf(28.00)),
                    new Routes("BERHAM", "Berlin", "Hamburg", BigDecimal.valueOf(3.45), BigDecimal.valueOf(20.00)),
                    new Routes("BERMAN", "Berlin", "Mannheim", BigDecimal.valueOf(7.00), BigDecimal.valueOf(35.00)),
                    new Routes("BERLEI", "Berlin", "Leipzig", BigDecimal.valueOf(2.40), BigDecimal.valueOf(10.00)),
                    new Routes("BERSTU", "Berlin", "Stuttgart", BigDecimal.valueOf(7.30), BigDecimal.valueOf(27.00)),
                    new Routes("BERDUS", "Berlin", "Düsseldorf", BigDecimal.valueOf(6.55), BigDecimal.valueOf(28.00)),

                    new Routes("MUNBER", "München", "Berlin", BigDecimal.valueOf(7.00), BigDecimal.valueOf(20.00)),
                    new Routes("MUNFRA", "München", "Frankfurt", BigDecimal.valueOf(4.50), BigDecimal.valueOf(22.00)),
                    new Routes("MUNHAM", "München", "Hamburg", BigDecimal.valueOf(8.30), BigDecimal.valueOf(36.00)),
                    new Routes("MUNMAN", "München", "Mannheim", BigDecimal.valueOf(4.25), BigDecimal.valueOf(19.00)),
                    new Routes("MUNLEI", "München", "Leipzig", BigDecimal.valueOf(4.40), BigDecimal.valueOf(29.00)),
                    new Routes("MUNSTU", "München", "Stuttgart", BigDecimal.valueOf(3.10), BigDecimal.valueOf(13.00)),
                    new Routes("MUNDUS", "München", "Düsseldorf", BigDecimal.valueOf(7.10), BigDecimal.valueOf(32.00)),

                    new Routes("FRABER", "Frankfurt", "Berlin", BigDecimal.valueOf(6.30), BigDecimal.valueOf(28.00)),
                    new Routes("FRAMUN", "Frankfurt", "München", BigDecimal.valueOf(4.50), BigDecimal.valueOf(22.00)),
                    new Routes("FRAHAM", "Frankfurt", "Hamburg", BigDecimal.valueOf(6.00), BigDecimal.valueOf(19.00)),
                    new Routes("FRAMAN", "Frankfurt", "Mannheim", BigDecimal.valueOf(1.20), BigDecimal.valueOf(11.00)),
                    new Routes("FRALEI", "Frankfurt", "Leipzig", BigDecimal.valueOf(4.50), BigDecimal.valueOf(25.00)),
                    new Routes("FRASTU", "Frankfurt", "Stuttgart", BigDecimal.valueOf(3.00), BigDecimal.valueOf(16.00)),
                    new Routes("FRADUS", "Frankfurt", "Düsseldorf", BigDecimal.valueOf(3.30), BigDecimal.valueOf(19.00)),

                    new Routes("HAMBER", "Hamburg", "Berlin", BigDecimal.valueOf(3.40), BigDecimal.valueOf(20.00)),
                    new Routes("HAMMUN", "Hamburg", "München", BigDecimal.valueOf(8.30), BigDecimal.valueOf(36.00)),
                    new Routes("HAMFRA", "Hamburg", "Frankfurt", BigDecimal.valueOf(6.00), BigDecimal.valueOf(19.00)),
                    new Routes("HAMMAN", "Hamburg", "Mannheim", BigDecimal.valueOf(7.00), BigDecimal.valueOf(29.00)),
                    new Routes("HAMLEI", "Hamburg", "Leipzig", BigDecimal.valueOf(4.45), BigDecimal.valueOf(19.00)),
                    new Routes("HAMSTU", "Hamburg", "Stuttgart", BigDecimal.valueOf(7.40), BigDecimal.valueOf(27.00)),
                    new Routes("HAMDUS", "Hamburg", "Düsseldorf", BigDecimal.valueOf(5.00), BigDecimal.valueOf(22.00)),

                    new Routes("MANBER", "Mannheim", "Berlin", BigDecimal.valueOf(7.00), BigDecimal.valueOf(35.00)),
                    new Routes("MANMUN", "Mannheim", "München", BigDecimal.valueOf(4.25), BigDecimal.valueOf(19.00)),
                    new Routes("MANFRA", "Mannheim", "Frankfurt", BigDecimal.valueOf(1.20), BigDecimal.valueOf(11.00)),
                    new Routes("MANHAM", "Mannheim", "Hamburg", BigDecimal.valueOf(7.00), BigDecimal.valueOf(29.00)),
                    new Routes("MANLEI", "Mannheim", "Leipzig", BigDecimal.valueOf(5.25), BigDecimal.valueOf(25.00)),
                    new Routes("MANSTU", "Mannheim", "Stuttgart", BigDecimal.valueOf(2.15), BigDecimal.valueOf(13.00)),
                    new Routes("MANDUS", "Mannheim", "Düsseldorf", BigDecimal.valueOf(4.00), BigDecimal.valueOf(18.00)),

                    new Routes("LEIBER", "Leipzig", "Berlin", BigDecimal.valueOf(2.40), BigDecimal.valueOf(10.00)),
                    new Routes("LEIMUN", "Leipzig", "München", BigDecimal.valueOf(4.40), BigDecimal.valueOf(29.00)),
                    new Routes("LEIFRA", "Leipzig", "Frankfurt", BigDecimal.valueOf(4.50), BigDecimal.valueOf(25.00)),
                    new Routes("LEIHAM", "Leipzig", "Hamburg", BigDecimal.valueOf(4.45), BigDecimal.valueOf(19.00)),
                    new Routes("LEIMAN", "Leipzig", "Mannheim", BigDecimal.valueOf(5.25), BigDecimal.valueOf(25.00)),
                    new Routes("LEISTU", "Leipzig", "Stuttgart", BigDecimal.valueOf(5.50), BigDecimal.valueOf(40.00)),
                    new Routes("LEIDUS", "Leipzig", "Düsseldorf", BigDecimal.valueOf(6.00), BigDecimal.valueOf(22.00)),

                    new Routes("STUBER", "Stuttgart", "Berlin", BigDecimal.valueOf(7.30), BigDecimal.valueOf(27.00)),
                    new Routes("STUMUN", "Stuttgart", "München", BigDecimal.valueOf(3.10), BigDecimal.valueOf(13.00)),
                    new Routes("STUFRA", "Stuttgart", "Frankfurt", BigDecimal.valueOf(3.00), BigDecimal.valueOf(16.00)),
                    new Routes("STUHAM", "Stuttgart", "Hamburg", BigDecimal.valueOf(7.40), BigDecimal.valueOf(27.00)),
                    new Routes("STUMAN", "Stuttgart", "Mannheim", BigDecimal.valueOf(2.15), BigDecimal.valueOf(13.00)),
                    new Routes("STULEI", "Stuttgart", "Leipzig", BigDecimal.valueOf(5.50), BigDecimal.valueOf(40.00)),
                    new Routes("STUDUS", "Stuttgart", "Düsseldorf", BigDecimal.valueOf(5.15), BigDecimal.valueOf(24.00)),

                    new Routes("DUSBER", "Düsseldorf", "Berlin", BigDecimal.valueOf(6.55), BigDecimal.valueOf(28.00)),
                    new Routes("DUSMUN", "Düsseldorf", "München", BigDecimal.valueOf(7.10), BigDecimal.valueOf(32.00)),
                    new Routes("DUSFRA", "Düsseldorf", "Frankfurt", BigDecimal.valueOf(3.30), BigDecimal.valueOf(19.00)),
                    new Routes("DUSHAM", "Düsseldorf", "Hamburg", BigDecimal.valueOf(5.00), BigDecimal.valueOf(22.00)),
                    new Routes("DUSMAN", "Düsseldorf", "Mannheim", BigDecimal.valueOf(4.00), BigDecimal.valueOf(18.00)),
                    new Routes("DUSLEI", "Düsseldorf", "Leipzig", BigDecimal.valueOf(6.00), BigDecimal.valueOf(22.00)),
                    new Routes("DUSSTU", "Düsseldorf", "Stuttgart", BigDecimal.valueOf(5.15), BigDecimal.valueOf(24.00))
            ));
        };
    }
}

