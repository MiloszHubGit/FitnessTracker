package pl.wsb.fitnesstracker.workoutsession;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface WorkoutSessionRepository extends JpaRepository<WorkoutSession, Long> {

    @Query("SELECT ws FROM WorkoutSession ws WHERE ws.training.id = :trainingId ORDER BY ws.timestamp DESC")
    List<WorkoutSession> findByTrainingId(@Param("trainingId") Long trainingId);

    @Query("SELECT ws FROM WorkoutSession ws WHERE ws.timestamp >= :startTime AND ws.timestamp <= :endTime ORDER BY ws.timestamp")
    List<WorkoutSession> findSessionsBetween(
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime);

    @Query(
        value = "SELECT AVG(altitude) FROM workout_session WHERE training_id = :trainingId",
        nativeQuery = true
    )
    Double findAverageAltitudeForTraining(@Param("trainingId") Long trainingId);
}
