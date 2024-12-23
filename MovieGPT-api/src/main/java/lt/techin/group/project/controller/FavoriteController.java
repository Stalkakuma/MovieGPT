package lt.techin.group.project.controller;

import lombok.AllArgsConstructor;
import lt.techin.group.project.exception.MediaNotFoundException;
import lt.techin.group.project.exception.UserNotFoundException;
import lt.techin.group.project.service.FavoriteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/favorite")
public class FavoriteController {

    private static final String MESSAGE = "message";
    private FavoriteService favoriteService;

    @GetMapping
    public ResponseEntity<?> getAllFavorites(@RequestParam Long userId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(favoriteService.getAllFavoriteMedia(userId));
        } catch (UserNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(MESSAGE, ex.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> addFavorite(@RequestParam Long userId, @RequestParam Long mediaId) {
        try {
            favoriteService.addMediaToFavorite(userId, mediaId);
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(MESSAGE, "Media added successfully"));
        } catch (UserNotFoundException | MediaNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(MESSAGE, ex.getMessage()));
        }
    }

    @DeleteMapping
    public ResponseEntity<Map<String, String>> deleteFavorite(@RequestParam Long userId, @RequestParam Long mediaId) {
        try {
            favoriteService.removeMediaFromFavorite(userId, mediaId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(Map.of(MESSAGE, "Media deleted successfully"));
        } catch (UserNotFoundException | MediaNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(MESSAGE, ex.getMessage()));
        }
    }

}
