package lt.techin.group.project.controller;

import jakarta.validation.Valid;
import lt.techin.group.project.rest.dto.MediaDto;
import lt.techin.group.project.service.MediaService;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/media")
public class MediaController {

    private final MediaService mediaService;

    public MediaController(MediaService mediaService) {
        this.mediaService = mediaService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<MediaDto> findMediaById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(mediaService.findMediaById(id));
    }

    @GetMapping
    public ResponseEntity<List<MediaDto>> findAllMedia() {
        return ResponseEntity.status(HttpStatus.OK).body(mediaService.findAllMedia());
    }

    @PostMapping
    public ResponseEntity<MediaDto> createMedia(@Valid @RequestBody MediaDto mediaDto) throws BadRequestException {
        return ResponseEntity.status(HttpStatus.CREATED).body(mediaService.createMedia(mediaDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedia(@PathVariable Long id) {
        mediaService.deleteMedia(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<MediaDto>> findByTitleContainingIgnoreCase(@RequestParam String name) {
        return ResponseEntity.status(HttpStatus.OK).body(mediaService.findByTitleContainingIgnoreCase(name));
    }

    @PutMapping
    public ResponseEntity<MediaDto> updateMedia(@Valid @RequestBody MediaDto mediaDto) throws BadRequestException {
        return ResponseEntity.status(HttpStatus.CREATED).body(mediaService.updateMedia(mediaDto));
    }


    @GetMapping("/genreId")
    public ResponseEntity<List<MediaDto>> findAllMediaByGenreId(@RequestParam Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(mediaService.findAllMediaByGenreId(id));
    }

    @GetMapping("/genre/{name}")
    public ResponseEntity<List<MediaDto>> findAllMediaByGenreName(@PathVariable String name) {
        return ResponseEntity.status(HttpStatus.OK).body(mediaService.findAllMediaByGenreName(name));
    }
}
