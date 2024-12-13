package lt.techin.group.project.controller;

import lt.techin.group.project.model.Media;
import lt.techin.group.project.rest.dto.MediaDto;
import lt.techin.group.project.service.MediaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
