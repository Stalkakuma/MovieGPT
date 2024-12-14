package lt.techin.group.project.rest.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lt.techin.group.project.model.User;

@Data
public class CommentDto {

    @NotNull(message = "Comment ID must be given.")
    private Long commentId;
    @NotNull(message = "Object User must be given.")
    private User user;
    @NotBlank(message = "To delete message use another path.")
    @Size(max = 300, message = "Comment can't extend 300 letters.")
    private String userComment;

}
