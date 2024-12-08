package lt.techin.group.project.rest.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lt.techin.group.project.model.Genre;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenreDto {


    private Long id;
    @NotNull(message = "Name cannot be null")
    @Size(min = 1, max = 20, message = "Name's length must 1 to 20 characters")
    private String name;

    public GenreDto(Genre genre) {
        this.id = genre.getId();
        this.name = genre.getName();
    }

}
