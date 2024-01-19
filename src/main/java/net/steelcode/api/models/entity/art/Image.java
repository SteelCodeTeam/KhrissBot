package net.steelcode.api.models.entity.art;

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
public class Image implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String spoilers;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] image;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy="images", fetch = FetchType.EAGER)
    private List<Artist> artists;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name="image_tags", joinColumns=@JoinColumn(name="image_id"), inverseJoinColumns=@JoinColumn(name="tag_id"))
    private List<Tag> tags;
}