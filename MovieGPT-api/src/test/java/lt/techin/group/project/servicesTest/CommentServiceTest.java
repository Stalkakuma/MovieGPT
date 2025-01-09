package lt.techin.group.project.servicesTest;

import lt.techin.group.project.exception.CannotEditAnotherUserCommentException;
import lt.techin.group.project.exception.CommentNotFoundException;
import lt.techin.group.project.exception.UserNotFoundException;
import lt.techin.group.project.model.Comment;
import lt.techin.group.project.model.Media;
import lt.techin.group.project.model.Roles;
import lt.techin.group.project.model.User;
import lt.techin.group.project.repository.CommentRepository;
import lt.techin.group.project.repository.MediaRepository;
import lt.techin.group.project.repository.UserRepository;
import lt.techin.group.project.rest.CommentPutRequest;
import lt.techin.group.project.rest.CommentRequest;
import lt.techin.group.project.rest.dto.CommentDto;
import lt.techin.group.project.service.CommentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CommentServiceTest {

    private CommentService commentService;
    private CommentRepository commentRepository;
    private UserRepository userRepository;
    private MediaRepository mediaRepository;

    private User mockUser;
    private Media mockMedia;
    private Comment mockComment;

    @BeforeEach
    public void setUp() {
        commentRepository = mock(CommentRepository.class);
        userRepository = mock(UserRepository.class);
        mediaRepository = mock(MediaRepository.class);

        commentService = new CommentService(commentRepository, userRepository, mediaRepository);

        mockUser = new User();
        mockUser.setId(1L);
        mockUser.setUsername("Test User");
        mockUser.setPassword("Test Password");
        mockUser.setEmail("test@test.com");
        mockUser.setRoles(Set.of(Roles.USER));

        mockMedia = new Media();
        mockMedia.setId(1L);
        mockMedia.setTitle("Test Media");

        mockComment = new Comment();
        mockComment.setId(1L);
        mockComment.setUser(mockUser);
        mockComment.setMedia(mockMedia);
        mockComment.setUserComment("Test Comment");
        mockComment.setCreatedAt(LocalDateTime.now());
    }

    @Test
    void testGetAllComments() {
        when(commentRepository.findAll()).thenReturn(List.of(mockComment));

        List<CommentDto> comments = commentService.getAllComments();

        assertNotNull(comments);
        assertEquals(1, comments.size());
        assertEquals("Test Comment", comments.get(0).getUserComment());
    }

    @Test
    void testGetCommentsByMediaId() {
        when(commentRepository.findByMedia_Id(1L)).thenReturn(List.of(mockComment));

        List<CommentDto> comments = commentService.getCommentsByMediaId(1L);

        assertNotNull(comments);
        assertEquals(1, comments.size());
        assertEquals("Test Comment", comments.get(0).getUserComment());
    }

    @Test
    void testAddComment() {
        CommentRequest commentRequest = new CommentRequest(1L, 1L, "New Comment");

        when(userRepository.findById(1L)).thenReturn(Optional.of(mockUser));
        when(mediaRepository.findById(1L)).thenReturn(Optional.of(mockMedia));

        commentService.addComment(commentRequest);

        verify(commentRepository, times(1)).save(any(Comment.class));
    }

    @Test
    void testAddComment_UserNotFound() {
        CommentRequest commentRequest = new CommentRequest(1L, 1L, "New Comment");

        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> commentService.addComment(commentRequest));
    }

    @Test
    void testUpdateComment() {
        CommentPutRequest commentPutRequest = new CommentPutRequest(1L, mockUser, "Updated Comment");

        when(commentRepository.findById(1L)).thenReturn(Optional.of(mockComment));

        commentService.updateComment(commentPutRequest);

        verify(commentRepository, times(1)).save(mockComment);
        assertEquals("Updated Comment", mockComment.getUserComment());
    }

    @Test
    void testUpdateComment_NotAuthorized() {
        User anotherUser = new User();
        anotherUser.setId(2L);
        anotherUser.setUsername("Another User");
        anotherUser.setPassword("Another Password");
        anotherUser.setEmail("test@test.com");
        anotherUser.setRoles(Set.of(Roles.USER));

        CommentPutRequest commentPutRequest = new CommentPutRequest(1L, anotherUser, "Updated Comment");

        when(commentRepository.findById(1L)).thenReturn(Optional.of(mockComment));

        assertThrows(CannotEditAnotherUserCommentException.class, () -> commentService.updateComment(commentPutRequest));
    }

    @Test
    void testDeleteComment() {
        when(commentRepository.existsById(1L)).thenReturn(true);

        commentService.deleteComment(1L);

        verify(commentRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteComment_NotFound() {
        when(commentRepository.existsById(999L)).thenReturn(false);

        assertThrows(CommentNotFoundException.class, () -> commentService.deleteComment(999L));
    }
}
