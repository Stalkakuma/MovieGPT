package lt.techin.group.project.rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lt.techin.group.project.model.Comment;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class CommentDto {

    @NotNull(message = "Comment ID must be given.")
    private Long commentId;
    @NotBlank(message = "Comment cannot be empty.")
    @Size(max = 300, message = "Comment cannot extend 300 letters.")
    private String userComment;
    @NotNull(message = "User object must be given.")
    private UserDto user;
    private String dateCreated;


    public CommentDto(Comment comment) {
        this.commentId = comment.getId();
        this.userComment = comment.getUserComment();
        this.user = new UserDto(comment.getUser());
        LocalDateTime createdAt = comment.getCreatedAt();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.dateCreated = createdAt.format(formatter);
    }
}
