package lt.techin.group.project.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lt.techin.group.project.rest.dto.GenreDto;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Genre {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;

    public Genre(GenreDto genreDto) {
        this.name = genreDto.getName();
        if (genreDto.getId() != null) {
            this.id = genreDto.getId();
        }
    }

    public GenreDto toDto() {
        return new GenreDto(this);
    }

}
