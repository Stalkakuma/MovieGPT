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
        MediaDto mediaDto = mediaService.findMediaById(id);
        return ResponseEntity.status(HttpStatus.OK).body(mediaDto);
    }

    @GetMapping
    public ResponseEntity<List<MediaDto>> findAllMedia() {
        List<MediaDto> listOfMedia = mediaService.findAllMedia();
        return ResponseEntity.status(HttpStatus.OK).body(listOfMedia);
    }

    @PostMapping
    public ResponseEntity<MediaDto> createMedia(@Valid @RequestBody MediaDto mediaDto) throws BadRequestException {
        MediaDto createdMediaDto = mediaService.createMedia(mediaDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMediaDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedia(@PathVariable Long id) {
        mediaService.deleteMedia(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<MediaDto>> findByTitleContainingIgnoreCase(@RequestParam String name) {
        List<MediaDto> listOfMedias = mediaService.findByTitleContainingIgnoreCase(name);
        return ResponseEntity.status(HttpStatus.OK).body(listOfMedias);
    }

    @PutMapping
    public ResponseEntity<MediaDto> updateMedia(@Valid @RequestBody MediaDto mediaDto) throws BadRequestException {
        MediaDto updatedMediaDto = mediaService.updateMedia(mediaDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedMediaDto);
    }


    @GetMapping("/genreId")
    public ResponseEntity<List<MediaDto>> findAllMediaByGenreId(@RequestParam Long id) {
        List<MediaDto> listOfMedias = mediaService.findAllMediaByGenreId(id);
        return ResponseEntity.status(HttpStatus.OK).body(listOfMedias);
    }

    @GetMapping("/genre/{name}")
    public ResponseEntity<List<MediaDto>> findAllMediaByGenreName(@PathVariable String name) {
        List<MediaDto> listOfMedias = mediaService.findAllMediaByGenreName(name);
        return ResponseEntity.status(HttpStatus.OK).body(listOfMedias);
    }
}
