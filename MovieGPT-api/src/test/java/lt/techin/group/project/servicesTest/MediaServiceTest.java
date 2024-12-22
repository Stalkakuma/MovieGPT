package lt.techin.group.project.servicesTest;

import lt.techin.group.project.exception.GenreNotFoundException;
import lt.techin.group.project.exception.MediaNotFoundException;
import lt.techin.group.project.model.Comment;
import lt.techin.group.project.model.Genre;
import lt.techin.group.project.model.Media;
import lt.techin.group.project.model.MediaType;
import lt.techin.group.project.repository.GenreRepository;
import lt.techin.group.project.repository.MediaRepository;
import lt.techin.group.project.rest.dto.MediaDto;
import lt.techin.group.project.service.MediaService;
import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MediaServiceTest {

    private MediaService mediaService;
    private GenreRepository genreRepository;
    private MediaRepository mediaRepository;

    private Genre genre1;
    private Genre genre2;
    private Set<Genre> genres;
    private List<Comment> comments;

    @BeforeEach
    public void setUp() {
        genreRepository = mock(GenreRepository.class);
        mediaRepository = mock(MediaRepository.class);

        mediaService = new MediaService(genreRepository, mediaRepository);

        genre1 = new Genre();
        genre1.setId(1L);
        genre1.setName("Action");

        genre2 = new Genre();
        genre2.setId(2L);
        genre2.setName("Adventure");

        genres = new HashSet<>();
        genres.add(genre1);
        genres.add(genre2);

        when(genreRepository.findById(1L)).thenReturn(Optional.of(genre1));
        when(genreRepository.findById(2L)).thenReturn(Optional.of(genre2));
    }


    @Test
    void testCreateMedia() throws BadRequestException {
        Media mockMedia = new Media(1L, "Inception", "A mind-bending thriller",
                "imageUrl", "thumbUrl", 2010, MediaType.MOVIE, genres, comments);

        when(mediaRepository.save(any(Media.class))).thenReturn(mockMedia);

        MediaDto savedMedia = mediaService.createMedia(mockMedia.toDto());

        assertNotNull(savedMedia);
        assertEquals("Inception", savedMedia.getTitle());
        assertEquals(2, savedMedia.getGenres().size());
    }

    @Test
    void testUpdateMedia() throws BadRequestException {
        Media mockMedia = new Media(2L, "Titanic", "A love story on the Titanic",
                "imageUrl", "thumbUrl", 1997, MediaType.MOVIE, genres, comments);

        when(mediaRepository.findById(2L)).thenReturn(Optional.of(mockMedia));
        when(mediaRepository.save(any(Media.class))).thenReturn(mockMedia);

        MediaDto updatedMedia = mediaService.updateMedia(mockMedia.toDto());

        assertNotNull(updatedMedia);
        assertEquals("Titanic", updatedMedia.getTitle());
        assertEquals(2L, updatedMedia.getGenres().size());
    }

    @Test
    void testFindMediaByTitleContaining() {

        Media mockMedia = new Media(3L, "The Matrix", "A cyberpunk thriller",
                "imageUrl", "thumbUrl", 1999, MediaType.MOVIE, genres, comments);

        when(mediaRepository.findByTitleContainingIgnoreCase("matrix")).thenReturn(List.of(mockMedia));

        List<MediaDto> returnedList = mediaService.findByTitleContainingIgnoreCase("matrix");

        assertNotNull(returnedList);
        assertEquals(1, returnedList.size());
        assertEquals("The Matrix", returnedList.getFirst().getTitle());
        assertEquals(2, returnedList.getFirst().getGenres().size());
    }

    @Test
    void testDeleteMedia() {

        Media mockMedia = new Media(4L, "The Matrix Reloaded", "A cyberpunk thriller",
                "imageUrl", "thumbUrl", 1999, MediaType.MOVIE, genres, comments);

        when(mediaRepository.findById(4L)).thenReturn(Optional.of(mockMedia));

        mediaService.deleteMedia(4L);

        verify(mediaRepository, times(1)).deleteById(4L);
    }

    @Test
    void testFindAllMediaByGenre() {

        Media mockMedia = new Media(5L, "The Avengers", "A superhero movie",
                "imageUrl", "thumbUrl", 2012, MediaType.MOVIE, genres, comments);

        when(mediaRepository.findByGenresId(1L)).thenReturn(List.of(mockMedia));

        List<MediaDto> returnedList = mediaService.findAllMediaByGenreId(1L);

        assertNotNull(returnedList);
        assertEquals(1, returnedList.size());
        assertEquals("The Avengers", returnedList.getFirst().getTitle());
        assertEquals(2, returnedList.getFirst().getGenres().size());
    }

    @Test
    void testCreateMediaWithInvalidReleaseYear_shouldThrowBadRequestException() {

        Media mockMedia = new Media(6L, "Old Movie", "A movie from the 1800s",
                "imageUrl", "thumbUrl", 1799, MediaType.MOVIE, genres, comments);

        assertThrows(BadRequestException.class, () -> mediaService.createMedia(mockMedia.toDto()));
    }

    @Test
    void testUpdateMediaWithInvalidGenres_shouldThrowGenreNotFoundException() {

        Media mockMedia = new Media(7L, "Comedy Show", "A hilarious comedy",
                "imageUrl", "thumbUrl", 2023, MediaType.MOVIE, genres, comments);

        when(mediaRepository.findById(7L)).thenReturn(java.util.Optional.of(mockMedia));
        when(genreRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(GenreNotFoundException.class, () -> mediaService.updateMedia(mockMedia.toDto()));
    }

    @Test
    void testDeleteMediaThatDoesNotExist_shouldThrowMediaNotFoundException() {
        when(mediaRepository.existsById(999L)).thenReturn(false);

        assertThrows(MediaNotFoundException.class, () -> mediaService.deleteMedia(999L));
    }

    @Test
    void testUpdateMediaThatDoesNotExist_shouldThrowMediaNotFoundException() {
        when(mediaRepository.findById(8L)).thenReturn(Optional.empty());

        Media mockMedia = new Media(8L, "Titanic", "A love story on the Titanic",
                "imageUrl", "thumbUrl", 1997, MediaType.MOVIE, genres, comments);

        assertThrows(MediaNotFoundException.class, () -> mediaService.updateMedia(mockMedia.toDto()));

    }

    @Test
    void testFindAllMediaByGenreName() {

        Media mockMedia = new Media(9L, "The Truman show", "An insurance salesman begins to suspect that his whole life is actually some sort of reality TV show",
                "imageUrl", "thumbUrl", 1998, MediaType.MOVIE, genres, comments);

        when(mediaRepository.findByGenresNameIgnoreCase("Adventure")).thenReturn(List.of(mockMedia));

        List<MediaDto> returnedList = mediaService.findAllMediaByGenreName("Adventure");

        assertNotNull(returnedList);
        assertEquals(1, returnedList.size());
        assertEquals("The Truman show", returnedList.getFirst().getTitle());
    }
}
