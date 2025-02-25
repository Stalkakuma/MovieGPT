package lt.techin.group.project.rest.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lt.techin.group.project.model.Genre;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GenreDto {


    private Long id;
    @NotNull(message = "Name must be provided")
    @Size(min = 1, max = 20, message = "Name's length must be 1 to 20 characters")
    private String name;

    public GenreDto(Genre genre) {
        this.id = genre.getId();
        this.name = genre.getName();
    }

}
