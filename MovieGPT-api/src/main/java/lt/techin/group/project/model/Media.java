package lt.techin.group.project.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lt.techin.group.project.rest.dto.MediaDto;

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
    @ManyToOne(fetch = FetchType.EAGER)
    private Genre genre;
    @Enumerated(EnumType.STRING)
    private MediaType mediaType;

    public MediaDto toDto() {
        return new MediaDto(this);
    }

}

