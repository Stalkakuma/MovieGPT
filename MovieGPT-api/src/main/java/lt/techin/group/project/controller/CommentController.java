package lt.techin.group.project.controller;

import lombok.AllArgsConstructor;
import lt.techin.group.project.model.Comment;
import lt.techin.group.project.rest.CommentRequest;
import lt.techin.group.project.rest.CommentPutRequest;
import lt.techin.group.project.rest.dto.CommentDto;
import lt.techin.group.project.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/comments")
public class CommentController {

    private static final String MESSAGE = "message";
    private final CommentService commentService;

    @GetMapping
    public ResponseEntity<List<CommentDto>> getAllComments() {
        return ResponseEntity.ok(commentService.getAllComments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<CommentDto>> getCommentsByMediaId(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.getCommentsByMediaId(id));
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> addComment(@RequestBody CommentRequest commentRequest) {
        commentService.addComment(commentRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(MESSAGE, "Comment added successfully"));
    }

    @PutMapping
    public ResponseEntity<Map<String, String>> updateComment(@RequestBody CommentPutRequest commentPutRequest) {
        commentService.updateComment(commentPutRequest);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(Map.of(MESSAGE, "Comment updated successfully"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.status(HttpStatus.GONE).body(Map.of(MESSAGE, "Comment deleted successfully"));
    }
}
