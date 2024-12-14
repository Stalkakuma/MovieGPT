package lt.techin.group.project.rest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentRequest {

//    @NotNull(message = "Movie ID must be given.")
//    private Long movieId;
    @NotNull(message = "User ID must be given.")
    private Long userId;
    @NotBlank(message = "Write something.")
    @Size(max = 300, message = "Comment can't extend 300 letters.")
    private String userComment;

}
