package lt.techin.group.project.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Setter
@Getter
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String userComment;
    @ManyToOne(fetch = FetchType.EAGER)
    private Media media;
    private LocalDateTime createdAt;


}
