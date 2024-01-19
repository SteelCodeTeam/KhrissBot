package net.steelcode.api.models.repository;

import net.steelcode.api.models.entity.WeeklyEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WeeklyEntryRepository extends JpaRepository<WeeklyEntry, Long> {

    Optional<WeeklyEntry> findTopByOrderByIdDesc();

    List<WeeklyEntry> findByWeekAndYear(Integer week, Integer year);
}
