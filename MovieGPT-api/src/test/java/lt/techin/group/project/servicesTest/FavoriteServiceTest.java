package lt.techin.group.project.servicesTest;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import lt.techin.group.project.exception.MediaNotFoundException;
import lt.techin.group.project.exception.UserNotFoundException;
import lt.techin.group.project.model.Media;
import lt.techin.group.project.model.User;
import lt.techin.group.project.repository.MediaRepository;
import lt.techin.group.project.repository.UserRepository;
import lt.techin.group.project.rest.dto.MediaDto;
import lt.techin.group.project.rest.dto.UserDto;
import lt.techin.group.project.service.FavoriteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

class FavoriteServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private MediaRepository mediaRepository;

    private FavoriteService favoriteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        favoriteService = new FavoriteService(userRepository, mediaRepository);
    }

    @Test
    void testGetAllFavoriteMedia_UserExists() {
        Long userId = 1L;
        User user = new User();
        user.setId(userId);
        Set<Media> favoriteMedia = new HashSet<>();
        Media media = new Media();
        media.setId(1L);
        favoriteMedia.add(media);
        user.setFavoritesMedia(favoriteMedia);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        List<MediaDto> mediaDtoList = favoriteService.getAllFavoriteMedia(userId);

        assertNotNull(mediaDtoList);
        assertEquals(1, mediaDtoList.size());
        assertEquals(1L, mediaDtoList.get(0).getId());
    }

    @Test
    void testGetAllFavoriteMedia_UserNotFound() {
        Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        UserNotFoundException exception = assertThrows(UserNotFoundException.class, () ->
                favoriteService.getAllFavoriteMedia(userId));
        assertEquals("User with ID: 1 not found", exception.getMessage());
    }

    @Test
    void testAddMediaToFavorite_UserAndMediaExist() {
        Long userId = 1L;
        Long mediaId = 1L;
        User user = new User();
        user.setId(userId);
        Set<Media> favoriteMedia = new HashSet<>();
        user.setFavoritesMedia(favoriteMedia);
        Media media = new Media();
        media.setId(mediaId);
        UserDto userDto = new UserDto();

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(mediaRepository.findById(mediaId)).thenReturn(Optional.of(media));

        favoriteService.addMediaToFavorite(userId, mediaId, userDto);

        assertTrue(user.getFavoritesMedia().contains(media));
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testAddMediaToFavorite_UserNotFound() {
        Long userId = 1L;
        Long mediaId = 1L;
        UserDto userDto = new UserDto();
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        UserNotFoundException exception = assertThrows(UserNotFoundException.class, () ->
                favoriteService.addMediaToFavorite(userId, mediaId, userDto));
        assertEquals("User with ID: 1 not found", exception.getMessage());
    }

    @Test
    void testAddMediaToFavorite_MediaNotFound() {
        Long userId = 1L;
        Long mediaId = 1L;
        User user = new User();
        user.setId(userId);
        Set<Media> favoriteMedia = new HashSet<>();
        user.setFavoritesMedia(favoriteMedia);
        UserDto userDto = new UserDto();

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(mediaRepository.findById(mediaId)).thenReturn(Optional.empty());

        MediaNotFoundException exception = assertThrows(MediaNotFoundException.class, () ->
                favoriteService.addMediaToFavorite(userId, mediaId, userDto));
        assertEquals("Media with ID: 1 not found", exception.getMessage());
    }

    @Test
    void testRemoveMediaFromFavorite_UserAndMediaExist() {
        Long userId = 1L;
        Long mediaId = 1L;
        User user = new User();
        user.setId(userId);
        Set<Media> favoriteMedia = new HashSet<>();
        Media media = new Media();
        media.setId(mediaId);
        favoriteMedia.add(media);
        user.setFavoritesMedia(favoriteMedia);
        UserDto userDto = new UserDto();

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        favoriteService.removeMediaFromFavorite(userId, mediaId, userDto);

        assertFalse(user.getFavoritesMedia().contains(media));
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testRemoveMediaFromFavorite_UserNotFound() {
        Long userId = 1L;
        Long mediaId = 1L;
        UserDto userDto = new UserDto();
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        UserNotFoundException exception = assertThrows(UserNotFoundException.class, () ->
                favoriteService.removeMediaFromFavorite(userId, mediaId, userDto));
        assertEquals("User with ID: 1 not found", exception.getMessage());
    }

    @Test
    void testRemoveMediaFromFavorite_MediaNotFound() {
        Long userId = 1L;
        Long mediaId = 1L;
        User user = new User();
        user.setId(userId);
        Set<Media> favoriteMedia = new HashSet<>();
        user.setFavoritesMedia(favoriteMedia);
        UserDto userDto = new UserDto();

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        MediaNotFoundException exception = assertThrows(MediaNotFoundException.class, () ->
                favoriteService.removeMediaFromFavorite(userId, mediaId, userDto));
        assertEquals("Media with ID: 1 not found", exception.getMessage());
    }
}
