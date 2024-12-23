package lt.techin.group.project.runner;

import lombok.AllArgsConstructor;
import lt.techin.group.project.service.FavoriteService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
@Order(4)
public class InitialFavorites implements CommandLineRunner {

    private FavoriteService favoriteService;

    @Override
    public void run(String... args) throws Exception {

        favoriteService.addMediaToFavorite(2L,1L);
        favoriteService.addMediaToFavorite(2L,3L);
        favoriteService.addMediaToFavorite(2L,16L);
        favoriteService.addMediaToFavorite(2L,21L);

    }
}
