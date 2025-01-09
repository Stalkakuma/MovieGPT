package lt.techin.group.project.rest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommentRequest {

    @NotNull(message = "Media ID must be given.")
    private Long mediaId;
    @NotNull(message = "User ID must be given.")
    private Long userId;
    @NotBlank(message = "Write something.")
    @Size(max = 255, message = "Comment can't extend 255 letters.")
    private String userComment;

}
