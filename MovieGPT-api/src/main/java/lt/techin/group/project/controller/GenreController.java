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
        return ResponseEntity.status(HttpStatus.OK).body(genreService.findAllGenres());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenreDto> findGenreById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(genreService.findGenreById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<GenreDto>> findGenresByNameContaining(@RequestParam String name) {
        return ResponseEntity.status(HttpStatus.OK).body(genreService.findGenresByNameContaining(name));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGenre(@PathVariable Long id) {
        genreService.deleteGenre(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<GenreDto> addGenre(@RequestParam String name) {
        return ResponseEntity.status(HttpStatus.CREATED).body(genreService.addGenre(name));
    }

    @PutMapping
    public ResponseEntity<GenreDto> updateGenre(@Valid @RequestBody GenreDto genreDto) {
        return ResponseEntity.status(HttpStatus.OK).body(genreService.updateGenre(genreDto));
    }

}
