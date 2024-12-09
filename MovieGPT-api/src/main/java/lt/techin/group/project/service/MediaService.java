package lt.techin.group.project.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lt.techin.group.project.exception.MediaNotFoundException;
import lt.techin.group.project.model.Media;
import lt.techin.group.project.repository.MediaRepository;
import lt.techin.group.project.rest.dto.MediaDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@Service
public class MediaService {

    public static final String MEDIA_NOT_FOUND_WITH_ID = "Media not found with id = ";
    private MediaRepository mediaRepository;


    public Media findMediaById(Long id) {
        Media media = mediaRepository.findById(id).orElseThrow(() -> new MediaNotFoundException(MEDIA_NOT_FOUND_WITH_ID + id));
        return media;
    }


    public List<MediaDto> findAllMedia() {
        return mediaRepository.findAll().stream().map(Media::toDto).collect(Collectors.toList());
    }
}
