package net.steelcode.api.models.entity.quote;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.steelcode.api.models.entity.Tag;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Quote implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String book;

    private int page;

    @Column(nullable = false, unique = true, length = 1312,  columnDefinition = "TEXT")
    private String quote;

    @Column(nullable = false)
    private Boolean saidByAnyone;

    private String said;

    private String spoiler;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name="quote_quote_tags", joinColumns=@JoinColumn(name="quote_id"), inverseJoinColumns=@JoinColumn(name="quote_tag_id"))
    private List<Tag> tags;
}
