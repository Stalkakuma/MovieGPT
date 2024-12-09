package lt.techin.group.project.runner;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lt.techin.group.project.model.Genre;
import lt.techin.group.project.model.Media;
import lt.techin.group.project.model.MediaType;
import lt.techin.group.project.repository.GenreRepository;
import lt.techin.group.project.repository.MediaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Set;

@Slf4j
@AllArgsConstructor
@Component
public class InitialMediaAndGenres implements CommandLineRunner {

    private GenreRepository genreRepository;
    private MediaRepository mediaRepository;

    @Override
    public void run(String... args) throws Exception {
        Genre action = new Genre();
        action.setName("Action");
        genreRepository.save(action);

        Genre drama = new Genre();
        drama.setName("Drama");
        genreRepository.save(drama);

        Genre comedy = new Genre();
        comedy.setName("Comedy");
        genreRepository.save(comedy);

        Media movie1 = new Media();
        movie1.setTitle("The Dark Knight");
        movie1.setDescription("A superhero film featuring Batman.");
        movie1.setImageUrl("https://www.shutterstock.com/image-vector/model-joker-face-vector-funny-smile-2324729523");
        movie1.setThumbnailUrl("https://www.shutterstock.com/image-vector/model-joker-face-vector-funny-smile-2324729523");
        movie1.setReleaseYear(2008);
        movie1.setMediaType(MediaType.MOVIE);
        movie1.setGenres(Set.of(action, drama));
        mediaRepository.save(movie1);

        Media movie2 = new Media();
        movie2.setTitle("Jumanji: Welcome to the Jungle");
        movie2.setDescription("A group of teenagers get trapped in a video game.");
        movie2.setImageUrl("https://www.shutterstock.com/image-photo/bangkok-thailand-dec-20-2019-jumanji-1594954729");
        movie2.setThumbnailUrl("https://www.shutterstock.com/image-photo/bangkok-thailand-dec-20-2019-jumanji-1594954729");
        movie2.setReleaseYear(2017);
        movie2.setMediaType(MediaType.MOVIE);
        movie2.setGenres(Set.of(comedy, action));
        mediaRepository.save(movie2);

        Media movie3 = new Media();
        movie3.setTitle("Forrest Gump");
        movie3.setDescription("The life story of a man with a kind heart and a unique perspective on life.");
        movie3.setImageUrl("https://www.shutterstock.com/image-photo/viersen-germany-may-9-2024-cinema-2476746885");
        movie3.setThumbnailUrl("https://www.shutterstock.com/image-photo/viersen-germany-may-9-2024-cinema-2476746885");
        movie3.setReleaseYear(1994);
        movie3.setMediaType(MediaType.MOVIE);
        movie3.setGenres(Set.of(drama));
        mediaRepository.save(movie3);

        Media tvShow = new Media();
        tvShow.setTitle("Breaking Bad");
        tvShow.setDescription("A high school chemistry teacher turned methamphetamine manufacturer.");
        tvShow.setImageUrl("https://www.shutterstock.com/image-photo/culver-city-ca-december-28-rv-545094478");
        tvShow.setThumbnailUrl("https://www.shutterstock.com/image-photo/culver-city-ca-december-28-rv-545094478");
        tvShow.setReleaseYear(2008);
        tvShow.setMediaType(MediaType.SERIES);
        tvShow.setGenres(Set.of(drama));
        mediaRepository.save(tvShow);
    }
}

