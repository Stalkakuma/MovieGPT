package lt.techin.group.project.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lt.techin.group.project.rest.dto.GenreDto;

@Entity
public class Genre {

    //TODO: change back to Lombok once it starts working

    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String name;

    public Genre(GenreDto genreDto) {
        this.name = genreDto.getName();
    }

    public Genre(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Genre() {
    }

    public GenreDto toDto() {
        return new GenreDto(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
