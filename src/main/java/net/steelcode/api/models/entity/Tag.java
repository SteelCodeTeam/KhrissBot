package net.steelcode.api.models.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.steelcode.api.models.entity.art.Image;
import net.steelcode.api.models.entity.quote.Quote;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Tag implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String tag;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ToString.Exclude
    @ManyToMany(cascade=CascadeType.ALL, mappedBy="tags", fetch = FetchType.EAGER)
    private List<Quote> quotes;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ToString.Exclude
    @ManyToMany(cascade=CascadeType.ALL, mappedBy="tags", fetch = FetchType.EAGER)
    private List<Image> images;
}
