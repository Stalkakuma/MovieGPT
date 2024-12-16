package lt.techin.group.project.controller;

import jakarta.validation.Valid;
import lt.techin.group.project.rest.dto.GenreDto;
import lt.techin.group.project.service.GenreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/genres")
public class GenreController {

    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping
    public ResponseEntity<List<GenreDto>> findAllGenres() {
        List<GenreDto> listOfGenres = genreService.findAllGenres();
        return ResponseEntity.status(HttpStatus.OK).body(listOfGenres);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenreDto> findGenreById(@PathVariable Long id) {
        GenreDto genreDto = genreService.findGenreById(id);
        return ResponseEntity.status(HttpStatus.OK).body(genreDto);
    }

    @GetMapping("/search")
    public ResponseEntity<List<GenreDto>> findGenresByNameContaining(@RequestParam String name) {
        List<GenreDto> listOfGenres = genreService.findGenresByNameContaining(name);
        return ResponseEntity.status(HttpStatus.OK).body(listOfGenres);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGenre(@PathVariable Long id) {
        genreService.deleteGenre(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<GenreDto> addGenre(@RequestParam String name) {
        GenreDto newGenre = genreService.addGenre(name);
        return ResponseEntity.status(HttpStatus.CREATED).body(newGenre);
    }

    @PutMapping
    public ResponseEntity<GenreDto> updateGenre(@Valid @RequestBody GenreDto genreDto) {
        GenreDto newGenre = genreService.updateGenre(genreDto);
        return ResponseEntity.status(HttpStatus.OK).body(newGenre);
    }

}
