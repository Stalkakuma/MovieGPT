package lt.techin.group.project.rest.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lt.techin.group.project.model.Media;
import lt.techin.group.project.model.MediaType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MediaDto {

    private Long id;
    @NotNull(message = "Title cannot be null")
    @Size(min = 1, max = 50, message = "Title's length must be 1 to 50 characters")
    private String title;
    @NotNull(message = "Description cannot be null")
    @Size(min = 1, max = 500, message = "Description's length must be 1 to 500 characters")
    private String description;
    @NotNull(message = "Image url cannot be null")
    private String imageUrl;
    @NotNull(message = "Thumbnail url cannot be null")
    private String thumbnailUrl;
    @NotNull(message = "Release year cannot be null")
    private Integer releaseYear;
    @NotNull(message = "Release year cannot be null")
    @Enumerated(EnumType.STRING)
    private MediaType mediaType;
    @NotNull(message = "Genre cannot be null")
    private GenreDto genre;


    public MediaDto(Media media) {
        this.id = media.getId();
        this.title = media.getTitle();
        this.description = media.getDescription();
        this.imageUrl = media.getImageUrl();
        this.thumbnailUrl = media.getThumbnailUrl();
        this.releaseYear = media.getReleaseYear();
        this.mediaType = media.getMediaType();
        this.genre = media.getGenre().toDto();
    }
}
