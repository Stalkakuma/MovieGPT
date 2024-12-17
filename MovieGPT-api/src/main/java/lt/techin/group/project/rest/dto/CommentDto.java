package lt.techin.group.project.rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lt.techin.group.project.model.Comment;

@Data
public class CommentDto {

    @NotNull(message = "Comment ID must be given.")
    private Long commentId;
    @NotNull(message = "Media ID must be given.")
    private Long mediaId;
    @NotBlank(message = "To delete message use another path.")
    @Size(max = 300, message = "Comment can't extend 300 letters.")
    private String userComment;

    public CommentDto(Comment comment) {
        this.commentId = comment.getId();
        this.mediaId = comment.getMedia().getId();
        this.userComment = comment.getUserComment();
    }
}
