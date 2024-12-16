package lt.techin.group.project.servicesTest;

import lt.techin.group.project.exception.GenreAlreadyExistsException;
import lt.techin.group.project.exception.GenreNotFoundException;
import lt.techin.group.project.model.Genre;
import lt.techin.group.project.repository.GenreRepository;
import lt.techin.group.project.rest.dto.GenreDto;
import lt.techin.group.project.service.GenreService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class GenreServiceTest {

    @Mock
    private GenreRepository genreRepository;

    @InjectMocks
    private GenreService genreService;

    private GenreDto genreDto;
    private Genre genre;

    @BeforeEach
    void setUp() {
        openMocks(this);
        genreDto = new GenreDto(1L, "Fantasy");
        genre = new Genre(genreDto);
    }

    @Test
    void testFindAllGenres() {
        when(genreRepository.findAll()).thenReturn(Collections.singletonList(genre));

        List<GenreDto> foundGenres = genreService.findAllGenres();

        assertEquals(1, foundGenres.size());
        assertEquals(genreDto.getName(), foundGenres.getFirst().getName());
    }

    @Test
    void testFindGenreById_shouldFindGenreById() {
        when(genreRepository.findById(1L)).thenReturn(Optional.of(genre));

        GenreDto receivedGenreDto = genreService.findGenreById(1L);

        assertEquals(genreDto.getName(), receivedGenreDto.getName());
    }

    @Test
    void testFindGenreById_shouldThrowGenreNotFoundException() {
        when(genreRepository.findById(1L)).thenReturn(Optional.empty());

        GenreNotFoundException exception = assertThrows(GenreNotFoundException.class, () -> genreService.findGenreById(1L));

        assertEquals("Genre not found with id: 1", exception.getMessage());
    }

    @Test
    void testFindGenresByNameContaining() {
        when(genreRepository.findByNameContainingIgnoreCase("tas")).thenReturn(Collections.singletonList(genre));

        List<GenreDto> foundGenres = genreService.findGenresByNameContaining("tas");

        assertEquals(1, foundGenres.size());
        assertEquals(genreDto.getName(), foundGenres.getFirst().getName());
    }

    @Test
    void testDeleteGenre_shouldDeleteGenre() {
        when(genreRepository.findAll()).thenReturn(Collections.emptyList());
        when(genreRepository.existsById(1L)).thenReturn(true);

        genreService.deleteGenre(1L);
        assertEquals(0, genreService.findAllGenres().size());

    }

    @Test
    void testDeleteGenre_shouldThrowGenreNotFoundException() {
        when(genreRepository.existsById(1L)).thenReturn(false);

        GenreNotFoundException exception = assertThrows(GenreNotFoundException.class, () -> genreService.deleteGenre(1L));

        assertEquals("Genre not found with id: 1", exception.getMessage());
    }

    @Test
    void testAddGenre_shouldThrowGenreAlreadyExistsException() {
        when(genreRepository.findByNameIgnoreCase(genreDto.getName())).thenReturn(Optional.of(genre));

        GenreAlreadyExistsException exception = assertThrows(GenreAlreadyExistsException.class, () -> genreService.addGenre(genreDto));

        assertEquals("Genre with name Fantasy already exists, it's id = 1", exception.getMessage());
    }

    @Test
    void testAddGenre_shouldAddGenre() {
        Genre newGenre = new Genre(2L, "Sci-Fi");
        when(genreRepository.save(any(Genre.class))).thenReturn(newGenre);

        GenreDto addedGenreDto = genreService.addGenre(newGenre.toDto());

        assertEquals(newGenre.getName(), addedGenreDto.getName());

    }

    @Test
    void testUpdateGenre_shouldThrowGenreNotFoundException() {
        when(genreRepository.findById(1L)).thenReturn(Optional.empty());

        GenreNotFoundException exception = assertThrows(GenreNotFoundException.class, () -> genreService.updateGenre(1L, genreDto));

        assertEquals("Genre not found with id: 1", exception.getMessage());
    }

    @Test
    void testUpdateGenre_shouldUpdateGenre() {
        GenreDto updatedGenreDto = new GenreDto(1L, "Sci-Fi");
        Genre updatedGenre = new Genre(updatedGenreDto);
        when(genreRepository.findById(1L)).thenReturn(Optional.of(genre));
        when(genreRepository.save(any(Genre.class))).thenReturn(updatedGenre);

        GenreDto returnedGenreDto = genreService.updateGenre(1L, updatedGenreDto);

        assertEquals(updatedGenreDto.getName(), returnedGenreDto.getName());

    }
}
