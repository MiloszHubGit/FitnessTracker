package pl.wsb.fitnesstracker.statistics.api;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wsb.fitnesstracker.statistics.internal.Statistics;

public interface StatisticsRepository extends JpaRepository<Statistics, Long> {
}
