package lt.techin.group.project.service;


import lombok.AllArgsConstructor;
import lombok.Data;
import lt.techin.group.project.exception.MediaNotFoundException;
import lt.techin.group.project.model.Media;
import lt.techin.group.project.repository.MediaRepository;
import lt.techin.group.project.rest.dto.MediaDto;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.List;

@Data
@AllArgsConstructor
@Service
public class MediaService {

    public static final String MEDIA_NOT_FOUND_WITH_ID = "Media not found with id = ";
    private MediaRepository mediaRepository;


    public MediaDto findMediaById(Long id) {
        Media media = mediaRepository.findById(id).orElseThrow(() -> new MediaNotFoundException(MEDIA_NOT_FOUND_WITH_ID + id));
        return media.toDto();
    }

    public List<MediaDto> findAllMedia() {
        return mediaRepository.findAll().stream().map(Media::toDto).toList();
    }

    public MediaDto createMedia(MediaDto mediaDto) throws BadRequestException {
        validateReleaseYear(mediaDto);
        Media newMedia = new Media();
        newMedia = setAllMediaDetailsFromDto(mediaDto, newMedia);
        return mediaRepository.save(newMedia).toDto();
        //todo Genres
    }

    public void deleteMedia(Long id) {
        if (!mediaRepository.existsById(id)) {
            throw new MediaNotFoundException(MEDIA_NOT_FOUND_WITH_ID + id);
        }
        mediaRepository.deleteById(id);
    }

    public List<MediaDto> findByTitleContainingIgnoreCase(String title) {
        List<Media> listOfMedias = mediaRepository.findByTitleContainingIgnoreCase(title);
        return convertToDtoList(listOfMedias);
    }

    public MediaDto updateMedia(MediaDto mediaDto) throws BadRequestException {
        validateReleaseYear(mediaDto);
        Media media = mediaRepository.findById(mediaDto.getId()).orElseThrow(() -> new MediaNotFoundException(MEDIA_NOT_FOUND_WITH_ID + mediaDto.getId()));
        media = setAllMediaDetailsFromDto(mediaDto, media);
        return mediaRepository.save(media).toDto();
        //todo genres
    }

    private List<MediaDto> convertToDtoList(List<Media> listOfMedias) {
        return listOfMedias.stream().map(Media::toDto).toList();
    }

    private Media setAllMediaDetailsFromDto(MediaDto mediaDto, Media newMedia) {
        newMedia.setTitle(mediaDto.getTitle());
        newMedia.setDescription(mediaDto.getDescription());
        newMedia.setMediaType(mediaDto.getMediaType());
        newMedia.setImageUrl(mediaDto.getImageUrl());
        newMedia.setThumbnailUrl(mediaDto.getThumbnailUrl());
        newMedia.setReleaseYear(mediaDto.getReleaseYear());
        return newMedia;
    }

    private void validateReleaseYear(MediaDto mediaDto) throws BadRequestException {
        int currentYear = Year.now().getValue();
        int allowedYearTill = currentYear + 5;
        if (mediaDto.getReleaseYear() < 1880 || mediaDto.getReleaseYear() >= allowedYearTill) {
            throw new BadRequestException("Release year must be between 1880 and " + allowedYearTill);
        }
    }

}
