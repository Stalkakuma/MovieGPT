package lt.techin.group.project.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lt.techin.group.project.rest.dto.MediaDto;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Media {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String description;
    private String imageUrl;
    private String thumbnailUrl;
    private Integer releaseYear;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "media_genres",
            joinColumns = @JoinColumn(name = "media_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<Genre> genres = new HashSet<>();
    @Enumerated(EnumType.STRING)
    private MediaType mediaType;

    public MediaDto toDto() {
            return new MediaDto(this);
        }
    }

