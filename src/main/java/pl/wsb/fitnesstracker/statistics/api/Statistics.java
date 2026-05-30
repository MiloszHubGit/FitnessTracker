package pl.wsb.fitnesstracker.statistics.api;

import jakarta.annotation.Nullable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.wsb.fitnesstracker.user.api.User;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Statistics {

    @Nullable
    private Long id;

    private User user;

    private int totalTrainings;

    private double totalDistance;

    private int totalCaloriesBurned;

    public Statistics(User user, int totalTrainings, double totalDistance, int totalCaloriesBurned) {
        this.user = user;
        this.totalTrainings = totalTrainings;
        this.totalDistance = totalDistance;
        this.totalCaloriesBurned = totalCaloriesBurned;
    }
}
