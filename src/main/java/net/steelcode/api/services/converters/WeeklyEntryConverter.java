package net.steelcode.api.services.converters;

import net.steelcode.api.models.dto.WeeklyEntryDTO;
import net.steelcode.api.models.entity.WeeklyEntry;
import org.springframework.stereotype.Service;

@Service
public class WeeklyEntryConverter extends AbstractConverter<WeeklyEntry, WeeklyEntryDTO> {
    @Override
    public WeeklyEntry fromDTO(WeeklyEntryDTO weeklyEntryDTO) {
        WeeklyEntry entry = new WeeklyEntry();
        entry.setId(weeklyEntryDTO.getId());
        entry.setWeek(weeklyEntryDTO.getWeek());
        entry.setUrlMessage(weeklyEntryDTO.getUrlMessage());
        entry.setUrlVideo(weeklyEntryDTO.getUrlVideo());
        entry.setIndexLines(weeklyEntryDTO.getIndexLines());
        entry.setDate(weeklyEntryDTO.getDate());
        entry.setYear(weeklyEntryDTO.getYear());
        return entry;
    }

    @Override
    public WeeklyEntryDTO fromEntity(WeeklyEntry weeklyEntry) {
        WeeklyEntryDTO dto = new WeeklyEntryDTO();
        dto.setId(weeklyEntry.getId());
        dto.setWeek(weeklyEntry.getWeek());
        dto.setUrlMessage(weeklyEntry.getUrlMessage());
        dto.setUrlVideo(weeklyEntry.getUrlVideo());
        dto.setIndexLines(weeklyEntry.getIndexLines());
        dto.setDate(weeklyEntry.getDate());
        dto.setYear(weeklyEntry.getYear());
        return dto;
    }
}
