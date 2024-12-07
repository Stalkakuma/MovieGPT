package lt.techin.group.project.rest.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lt.techin.group.project.model.Genre;

public class GenreDto {

    //TODO: change back to Lombok once it starts working

    private Long id;
    @NotNull(message = "Name cannot be null")
    @Size(max = 20, message = "Name must be up to 20 characters")
    private String name;

    public GenreDto(Genre genre) {
        this.id = genre.getId();
        this.name = genre.getName();
    }

    public GenreDto() {}

    public GenreDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull(message = "Name cannot be null") @Size(max = 20, message = "Name must be up to 20 characters") String getName() {
        return name;
    }

    public void setName(@NotNull(message = "Name cannot be null") @Size(max = 20, message = "Name must be up to 20 characters") String name) {
        this.name = name;
    }
}
