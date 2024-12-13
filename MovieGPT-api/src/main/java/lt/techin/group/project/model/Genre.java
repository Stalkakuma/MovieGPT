package lt.techin.group.project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lt.techin.group.project.rest.dto.GenreDto;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Genre {


    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "genres")
    private Set<Media> medias = new HashSet<>();

    public Genre(GenreDto genreDto) {
        this.name = genreDto.getName();
        if (genreDto.getId() != null) {
            this.id = genreDto.getId();
        }
    }

    public GenreDto toDto() {
        return new GenreDto(this.id, this.name);
    }

}
