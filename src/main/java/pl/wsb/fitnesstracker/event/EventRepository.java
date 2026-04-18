package pl.wsb.fitnesstracker.event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("SELECT e FROM Event e WHERE e.startDate > :now ORDER BY e.startDate")
    List<Event> findUpcoming(@Param("now") LocalDate now);

    /**
     * Native SQL query: Get all event names with their participant count.
     * Uses LEFT JOIN to include events with no participants (count = 0).
     * Ordered by participant count descending (most popular events first).
     *
     * @return List of Object arrays: [eventName (String), participantCount (Long)]
     */
    @Query(
        value = "SELECT e.name, COUNT(ue.id) FROM event e LEFT JOIN user_event ue ON e.id = ue.event_id GROUP BY e.id, e.name ORDER BY COUNT(ue.id) DESC",
        nativeQuery = true
    )
    List<Object[]> findEventNamesWithParticipantCount();

    /**
     * Count participants for a specific event.
     * Native SQL aggregate query.
     *
     * @param eventId the event ID
     * @return number of users registered for the event
     */
    @Query(
        value = "SELECT COUNT(DISTINCT user_id) FROM user_event WHERE event_id = :eventId",
        nativeQuery = true
    )
    Long countParticipants(@Param("eventId") Long eventId);
}