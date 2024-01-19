package net.steelcode.api.models.entity.art;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Artist {

    @Id
    @GeneratedValue
    private Long Id;
    @Column(nullable = false, unique = true)
    private String nick;
    private String imageProfile;
    private String description;
    private String instagram;
    private String twitter;
    private String discord;
    private String behance;
    private String web;
    private String patreon;
    private String kofi;
    @Column(nullable = false)
    private Boolean hasCommissionsOpen;
    private String commissionSite;
    private String redPrincipal;
    private String linksDirectory;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name="artist_images", joinColumns=@JoinColumn(name="artist_id"), inverseJoinColumns=@JoinColumn(name="image_id"))
    private List<Image> images;
}