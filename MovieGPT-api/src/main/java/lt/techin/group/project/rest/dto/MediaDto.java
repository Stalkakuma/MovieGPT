package lt.techin.group.project.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lt.techin.group.project.model.Genre;
import lt.techin.group.project.model.Media;
import lt.techin.group.project.model.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MediaDto {

    private Long id;
    private String title;
    private String description;
    private String imageUrl;
    private String thumbnailUrl;
    private Integer releaseYear;
    private MediaType mediaType;
    private Set<GenreDto> genres;




    public MediaDto(Media media) {
        this.id = media.getId();
        this.title = media.getTitle();
        this.description = media.getDescription();
        this.imageUrl = media.getImageUrl();
        this.thumbnailUrl = media.getThumbnailUrl();
        this.releaseYear = media.getReleaseYear();
        this.mediaType = media.getMediaType();

        if (media.getGenres() != null) {
            this.genres = media.getGenres().stream().map(Genre::toDto).collect(Collectors.toSet());
        } else {
            this.genres = new HashSet<>();
        }}

}
