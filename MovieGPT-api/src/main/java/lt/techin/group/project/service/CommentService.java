package lt.techin.group.project.service;

import lombok.AllArgsConstructor;
import lt.techin.group.project.model.Comment;
import lt.techin.group.project.repository.CommentRepository;
import lt.techin.group.project.rest.CommentRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CommentService {

    private CommentRepository commentRepository;

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }
    public void addComment(CommentRequest commentRequest) {

    }

}
