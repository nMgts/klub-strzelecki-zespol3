package pl.klubstrzelecki.serwer_klub_strzelecki.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.klubstrzelecki.serwer_klub_strzelecki.model.Competition;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition, Long> {
    @Query("SELECT c FROM Competition c WHERE c.start_date > :startDateThreshold AND c.start_date <= :endDateThreshold AND c.reminder_sent = false")
    List<Competition> findCompetitionsForReminder(@Param("startDateThreshold") LocalDateTime startDateThreshold, @Param("endDateThreshold") LocalDateTime endDateThreshold);
}
