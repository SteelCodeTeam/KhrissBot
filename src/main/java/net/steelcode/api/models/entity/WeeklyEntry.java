package net.steelcode.api.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class WeeklyEntry {

    @Id
    @GeneratedValue
    private Long id;

    private String date;

    private Integer week;

    private Integer year;

    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    private List<String> indexLines;

    private String urlVideo;

    private String urlMessage;

}
