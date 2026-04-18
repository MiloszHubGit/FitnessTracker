package pl.wsb.fitnesstracker.training.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.wsb.fitnesstracker.training.api.Training;

public interface TrainingRepository extends JpaRepository<Training, Long> {

    /**
     * Calculate total distance (in kilometers) traveled by a specific user.
     * Sums up the distance column from all trainings for the given user.
     *
     * @param userId the ID of the user
     * @return total distance in kilometers, or 0.0 if user has no trainings
     */
    @Query(
        value = "SELECT COALESCE(SUM(distance), 0.0) FROM trainings WHERE user_id = :userId",
        nativeQuery = true
    )
    Double getTotalDistanceForUser(@Param("userId") Long userId);

    /**
     * Count total number of trainings across all users.
     *
     * @return total count of trainings
     */
    @Query(
        value = "SELECT COUNT(*) FROM trainings",
        nativeQuery = true
    )
    long countAllTrainings();
}
