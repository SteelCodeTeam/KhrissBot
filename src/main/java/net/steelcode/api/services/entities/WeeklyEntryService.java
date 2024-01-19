package net.steelcode.api.services.entities;

import lombok.extern.java.Log;
import net.steelcode.api.models.dto.WeeklyEntryDTO;
import net.steelcode.api.models.entity.WeeklyEntry;
import net.steelcode.api.models.repository.WeeklyEntryRepository;
import net.steelcode.api.services.converters.WeeklyEntryConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log
public class WeeklyEntryService {

    @Autowired
    private WeeklyEntryConverter converter;

    @Autowired
    private WeeklyEntryRepository repository;


    public WeeklyEntryDTO getLastWeeklyEntry() {

        Optional<WeeklyEntry> entryOptional = repository.findTopByOrderByIdDesc();

        return entryOptional.map(weeklyEntry -> converter.fromEntity(weeklyEntry)).orElse(null);
    }

    public WeeklyEntryDTO getWeeklyEntryFromWeek(Integer week) {

        List<WeeklyEntry> entryOptional = repository.findByWeekAndYear(week, 2024);

        if (entryOptional.size() != 1) {
            return converter.fromEntity(entryOptional.get(0));
        } else {
            return null;
        }
    }

    public WeeklyEntryDTO getWeeklyEntryFromWeekAndYear(Integer week, Integer year) {

        List<WeeklyEntry> entryOptional = repository.findByWeekAndYear(week, year);

        if (entryOptional.size() == 1) {
            return converter.fromEntity(entryOptional.get(0));
        } else {
            return null;
        }
    }
}
