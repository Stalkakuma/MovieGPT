package lt.techin.group.project.rest.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lt.techin.group.project.model.Genre;
import lt.techin.group.project.model.Media;
import lt.techin.group.project.model.MediaType;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MediaDto {

    private Long id;
    @NotNull(message = "Title must be provided")
    @Size(min = 1, max = 50, message = "Title's length must be 1 to 50 characters")
    private String title;
    @NotNull(message = "Description must be provided")
    @Size(min = 1, max = 500, message = "Description's length must be 1 to 500 characters")
    private String description;
    @NotNull(message = "Image url must be provided")
    private String imageUrl;
    @NotNull(message = "Thumbnail url must be provided")
    private String thumbnailUrl;
    @NotNull(message = "Release year must be provided")
    private Integer releaseYear;
    @NotNull(message = "Media type must be provided")
    @Enumerated(EnumType.STRING)
    private MediaType mediaType;
    @NotNull(message = "Genre(s) must be provided")
    private Set<GenreDto> genres = new HashSet<>();


    public MediaDto(Media media) {
        this.id = media.getId();
        this.title = media.getTitle();
        this.description = media.getDescription();
        this.imageUrl = media.getImageUrl();
        this.thumbnailUrl = media.getThumbnailUrl();
        this.releaseYear = media.getReleaseYear();
        this.mediaType = media.getMediaType();
        this.genres = media.getGenres().stream().map(Genre::toDto).collect(Collectors.toSet());
    }
}
