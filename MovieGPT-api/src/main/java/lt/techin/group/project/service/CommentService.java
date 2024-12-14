package lt.techin.group.project.service;

import lombok.AllArgsConstructor;
import lt.techin.group.project.exception.CannotEditAnotherUserException;
import lt.techin.group.project.exception.CommentNotFoundException;
import lt.techin.group.project.exception.UserNotFoundException;
import lt.techin.group.project.model.Comment;
import lt.techin.group.project.model.User;
import lt.techin.group.project.repository.CommentRepository;
import lt.techin.group.project.repository.UserRepository;
import lt.techin.group.project.rest.CommentRequest;
import lt.techin.group.project.rest.dto.CommentDto;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CommentService {

    private CommentRepository commentRepository;
    private UserRepository userRepository;

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public void addComment(CommentRequest commentRequest) {
        Comment comment = new Comment();
        User user = userRepository.findById(commentRequest.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User with ID: " + commentRequest.getUserId() + " not found"));
        comment.setUser(user);
        comment.setUserComment(commentRequest.getUserComment());
        commentRepository.save(comment);
    }

    public void updateComment(CommentDto commentDto) {
        Comment comment = commentRepository.findById(commentDto.getCommentId())
                .orElseThrow(() -> new CommentNotFoundException("Comment with ID: " + commentDto.getCommentId() + " not found"));
        if(comment.getUser().getId().compareTo(commentDto.getUser().getId()) == 0
                ||
        commentDto.getUser().getRoles().stream().anyMatch(roles -> roles.name().matches("ADMIN"))) {
            comment.setUserComment(commentDto.getUserComment());
            commentRepository.save(comment);
        } else {
            throw new CannotEditAnotherUserException("User with ID: " + commentDto.getUser().getId() + " cannot edit user ID: " + comment.getUser().getId() + " comment.");
        }
    }

    public void deleteComment(Long id) {
        if (commentRepository.existsById(id)){
            commentRepository.deleteById(id);
        } else {
            throw new CommentNotFoundException("Comment with ID: " + id + " not found");
        }
    }
}
