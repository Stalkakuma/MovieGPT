package lt.techin.group.project.runner;

import lombok.AllArgsConstructor;
import lt.techin.group.project.model.Roles;
import lt.techin.group.project.rest.dto.UserDto;
import lt.techin.group.project.service.FavoriteService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Set;

@AllArgsConstructor
@Component
@Order(4)
public class InitialFavorites implements CommandLineRunner {

    private FavoriteService favoriteService;

    @Override
    public void run(String... args) throws Exception {

        UserDto userDto = new UserDto();
        userDto.setId(2L);
        userDto.setEmail("user@gmail.com");
        userDto.setUsername("user");
        userDto.setRoles(Set.of("USER"));

        favoriteService.addMediaToFavorite(2L,1L, userDto);
        favoriteService.addMediaToFavorite(2L,3L, userDto);
        favoriteService.addMediaToFavorite(2L,16L, userDto);
        favoriteService.addMediaToFavorite(2L,21L, userDto);

    }
}
