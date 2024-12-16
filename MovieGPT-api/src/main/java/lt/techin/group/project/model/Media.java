package lt.techin.group.project.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lt.techin.group.project.rest.dto.MediaDto;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"title", "releaseYear", "mediaType", "imageUrl"}),
        }
)
public class Media {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String description;
    private String imageUrl;
    private String thumbnailUrl;
    private Integer releaseYear;
    @Enumerated(EnumType.STRING)
    private MediaType mediaType;
    @ManyToMany(fetch = FetchType.EAGER)
    @JsonManagedReference
    @JoinTable(
            name = "media_genre",
            joinColumns = @JoinColumn(name = "media_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<Genre> genres = new HashSet<>();


    public MediaDto toDto() {
        return new MediaDto(this);
    }

}

