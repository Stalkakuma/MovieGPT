package lt.techin.group.project.service;

import lombok.AllArgsConstructor;
import lt.techin.group.project.exception.CannotEditAnotherUserFavoriteListException;
import lt.techin.group.project.exception.MediaNotFoundException;
import lt.techin.group.project.exception.UserNotFoundException;
import lt.techin.group.project.model.Media;
import lt.techin.group.project.model.User;
import lt.techin.group.project.repository.MediaRepository;
import lt.techin.group.project.repository.UserRepository;
import lt.techin.group.project.rest.dto.MediaDto;
import lt.techin.group.project.rest.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class FavoriteService {

    private static final String USER_NOT_FOUND_MESSAGE = "User with ID: %d not found";
    private static final String MEDIA_NOT_FOUND_MESSAGE = "Media with ID: %d not found";

    private UserRepository userRepository;
    private MediaRepository mediaRepository;

    public List<MediaDto> getAllFavoriteMedia(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(String.format(USER_NOT_FOUND_MESSAGE, userId)));

        return user.getFavoritesMedia()
                .stream()
                .map(MediaDto::new)
                .toList();
    }

    public void addMediaToFavorite(Long userId, Long mediaId, UserDto userDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(String.format(USER_NOT_FOUND_MESSAGE, userId)));

        Media media = mediaRepository.findById(mediaId)
                .orElseThrow(() -> new MediaNotFoundException(String.format(MEDIA_NOT_FOUND_MESSAGE, mediaId)));

        if(user.getId().compareTo(userDto.getId()) == 0) {
            user.getFavoritesMedia().add(media);
            userRepository.save(user);
        } else {
            throw new CannotEditAnotherUserFavoriteListException("User with ID: " + userDto.getId() + " cannot add favorite to user ID: " + userId + " favorite list.");
        }
    }

    public void removeMediaFromFavorite(Long userId, Long mediaId, UserDto userDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(String.format(USER_NOT_FOUND_MESSAGE, userId)));

        Media media = user.getFavoritesMedia()
                .stream()
                .filter(m -> m.getId().equals(mediaId))
                .findFirst()
                .orElseThrow(() -> new MediaNotFoundException(String.format(MEDIA_NOT_FOUND_MESSAGE, mediaId)));

        if(user.getId().compareTo(userDto.getId()) == 0) {
        user.getFavoritesMedia().remove(media);
        userRepository.save(user);
        } else {
            throw new CannotEditAnotherUserFavoriteListException("User with ID: " + userDto.getId() + " cannot delete favorite from user ID: " + userId + " favorite list.");
        }
    }

}
