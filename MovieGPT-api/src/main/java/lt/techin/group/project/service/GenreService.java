package lt.techin.group.project.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lt.techin.group.project.exception.GenreAlreadyExistsException;
import lt.techin.group.project.exception.GenreNotFoundException;
import lt.techin.group.project.model.Genre;
import lt.techin.group.project.repository.GenreRepository;
import lt.techin.group.project.rest.dto.GenreDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Getter
@Setter
@AllArgsConstructor
public class GenreService {


    protected static final String GENRE_NOT_FOUND_WITH_ID = "Genre not found with id: ";
    private GenreRepository genreRepository;


    public List<GenreDto> findAllGenres() {
        List<Genre> allGenres = genreRepository.findAll();
        return convertToDtoList(allGenres);
    }

    public GenreDto findGenreById(Long id) {
        Genre genre = genreRepository.findById(id).orElseThrow(() -> new GenreNotFoundException(GENRE_NOT_FOUND_WITH_ID + id));
        return genre.toDto();
    }


    public List<GenreDto> findGenresByNameContaining(String name) {
        List<Genre> listOfGenres = genreRepository.findByNameContainingIgnoreCase(name);
        return convertToDtoList(listOfGenres);
    }


    public void deleteGenre(Long id) {
        if (!genreRepository.existsById(id)) {
            throw new GenreNotFoundException(GENRE_NOT_FOUND_WITH_ID + id);
        }
        genreRepository.deleteById(id);
    }


    public GenreDto addGenre(String name) {
        genreRepository.findByNameIgnoreCase(name)
                .ifPresent(genre -> {
                    throw new GenreAlreadyExistsException("Genre with name " + name + " already exists, it's id = " + genre.getId());
                });
        Genre genre = genreRepository.save(new Genre(name));
        return genre.toDto();
    }

    public GenreDto updateGenre(GenreDto genreDto) {
        Genre genreToUpdate = genreRepository.findById(genreDto.getId()).orElseThrow(() -> new GenreNotFoundException(GENRE_NOT_FOUND_WITH_ID + genreDto.getId()));
        genreToUpdate.setName(genreDto.getName());
        genreRepository.save(genreToUpdate);
        return genreToUpdate.toDto();
    }

    private List<GenreDto> convertToDtoList(List<Genre> genres) {
        return genres.stream()
                .map(GenreDto::new)
                .collect(Collectors.toList());
    }

}
