package lt.techin.group.project.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import lt.techin.group.project.rest.dto.GenreDto;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Genre {


    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @ManyToMany(mappedBy = "genres")
    @JsonBackReference
    private Set<Media> medias = new HashSet<>();

    public Genre(GenreDto genreDto) {
        this.name = genreDto.getName();
        if (genreDto.getId() != null) {
            this.id = genreDto.getId();
        }
    }

    public Genre(String name) {
        this.name = name;
    }

    public GenreDto toDto() {
        return new GenreDto(this.id, this.name);
    }

}
