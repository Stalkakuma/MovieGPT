package lt.techin.group.project.rest;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lt.techin.group.project.model.User;

@AllArgsConstructor
@Data
public class CommentPutRequest {

    @NotNull(message = "Comment ID must be given.")
    private Long commentId;
    @NotNull(message = "Object User must be given.")
    private User user;
    @NotBlank(message = "Comment cannot be empty.")
    @Size(max = 300, message = "Comment cannot extend 300 letters.")
    private String userComment;

}
