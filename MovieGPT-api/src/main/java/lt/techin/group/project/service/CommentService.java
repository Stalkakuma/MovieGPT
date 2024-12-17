package lt.techin.group.project.service;

import lombok.AllArgsConstructor;
import lt.techin.group.project.exception.CannotEditAnotherUserException;
import lt.techin.group.project.exception.CommentNotFoundException;
import lt.techin.group.project.exception.MediaNotFoundException;
import lt.techin.group.project.exception.UserNotFoundException;
import lt.techin.group.project.model.Comment;
import lt.techin.group.project.model.Media;
import lt.techin.group.project.model.User;
import lt.techin.group.project.repository.CommentRepository;
import lt.techin.group.project.repository.MediaRepository;
import lt.techin.group.project.repository.UserRepository;
import lt.techin.group.project.rest.CommentRequest;
import lt.techin.group.project.rest.CommentPutRequest;
import lt.techin.group.project.rest.dto.CommentDto;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CommentService {

    private static final String NOT_FOUND = " not found.";

    private CommentRepository commentRepository;
    private UserRepository userRepository;
    private MediaRepository mediaRepository;

    public List<CommentDto> getAllComments() {
        return commentRepository.findAll().stream().map(CommentDto::new).toList();
    }

    public List<CommentDto> getCommentsByMediaId(Long mediaId) {
        return commentRepository.findByMedia_Id(mediaId).stream().map(CommentDto::new).toList();
    }

    public void addComment(CommentRequest commentRequest) {
        User user = userRepository.findById(commentRequest.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User with ID: " + commentRequest.getUserId() + NOT_FOUND));
        Media media = mediaRepository.findById(commentRequest.getMovieId())
                .orElseThrow(() -> new MediaNotFoundException("Media with ID: " + commentRequest.getMovieId() + NOT_FOUND));
        Comment comment = new Comment();
        comment.setUser(user);
        comment.setUserComment(commentRequest.getUserComment());
        comment.setMedia(media);
        commentRepository.save(comment);
    }

    public void updateComment(CommentPutRequest commentPutRequest) {
        Comment comment = commentRepository.findById(commentPutRequest.getCommentId())
                .orElseThrow(() -> new CommentNotFoundException("Comment with ID: " + commentPutRequest.getCommentId() + NOT_FOUND));
        if(comment.getUser().getId().compareTo(commentPutRequest.getUser().getId()) == 0
                ||
        commentPutRequest.getUser().getRoles().stream().anyMatch(roles -> roles.name().matches("ADMIN"))) {
            comment.setUserComment(commentPutRequest.getUserComment());
            commentRepository.save(comment);
        } else {
            throw new CannotEditAnotherUserException("User with ID: " + commentPutRequest.getUser().getId() + " cannot edit user ID: " + comment.getUser().getId() + " comment.");
        }
    }

    public void deleteComment(Long id) {
        if (commentRepository.existsById(id)){
            commentRepository.deleteById(id);
        } else {
            throw new CommentNotFoundException("Comment with ID: " + id + NOT_FOUND);
        }
    }
}
