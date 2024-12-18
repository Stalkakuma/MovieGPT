package lt.techin.group.project.repository;

import lt.techin.group.project.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByMedia_Id(Long mediaId);
}
