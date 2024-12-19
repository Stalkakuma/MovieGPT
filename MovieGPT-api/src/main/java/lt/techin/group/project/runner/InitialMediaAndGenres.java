package lt.techin.group.project.runner;

import lombok.AllArgsConstructor;
import lt.techin.group.project.model.Genre;
import lt.techin.group.project.model.Media;
import lt.techin.group.project.model.MediaType;
import lt.techin.group.project.repository.GenreRepository;
import lt.techin.group.project.repository.MediaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Set;

@AllArgsConstructor
@Component
@Order(2)
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

        Genre thriller = new Genre();
        thriller.setName("Thriller");
        genreRepository.save(thriller);

        Genre sciFi = new Genre();
        sciFi.setName("Sci-Fi");
        genreRepository.save(sciFi);

        Genre romance = new Genre();
        romance.setName("Romance");
        genreRepository.save(romance);

        Genre horror = new Genre();
        horror.setName("Horror");
        genreRepository.save(horror);

        Genre fantasy = new Genre();
        fantasy.setName("Fantasy");
        genreRepository.save(fantasy);

        Genre mystery = new Genre();
        mystery.setName("Mystery");
        genreRepository.save(mystery);

        Genre adventure = new Genre();
        adventure.setName("Adventure");
        genreRepository.save(adventure);

        Genre animation = new Genre();
        animation.setName("Animation");
        genreRepository.save(animation);

        Genre documentary = new Genre();
        documentary.setName("Documentary");
        genreRepository.save(documentary);

        Genre crime = new Genre();
        crime.setName("Crime");
        genreRepository.save(crime);

        Genre biography = new Genre();
        biography.setName("Biography");
        genreRepository.save(biography);

        Genre history = new Genre();
        history.setName("History");
        genreRepository.save(history);

        Genre family = new Genre();
        family.setName("Family");
        genreRepository.save(family);

        Genre mockumentary = new Genre();
        mockumentary.setName("Mockumentary");
        genreRepository.save(mockumentary);

        Genre musical = new Genre();
        musical.setName("Musical");
        genreRepository.save(musical);

        Genre war = new Genre();
        war.setName("War");
        genreRepository.save(war);

        Genre western = new Genre();
        western.setName("Western");
        genreRepository.save(western);

        Media movie1 = new Media();
        movie1.setTitle("The Dark Knight");
        movie1.setDescription("A superhero film featuring Batman.");
        movie1.setImageUrl("https://image.tmdb.org/t/p/w1280/qJ2tW6WMUDux911r6m7haRef0WH.jpg");
        movie1.setThumbnailUrl("https://image.tmdb.org/t/p/w1280/qJ2tW6WMUDux911r6m7haRef0WH.jpg");
        movie1.setReleaseYear(2008);
        movie1.setMediaType(MediaType.MOVIE);
        movie1.setGenres(Set.of(action, crime, thriller));
        mediaRepository.save(movie1);

        Media movie2 = new Media();
        movie2.setTitle("Jumanji: Welcome to the Jungle");
        movie2.setDescription("A group of teenagers get trapped in a video game.");
        movie2.setImageUrl("https://image.tmdb.org/t/p/w1280/r9YhfuHiled6YfOs2bjyqrOKns4.jpg");
        movie2.setThumbnailUrl("https://image.tmdb.org/t/p/w1280/r9YhfuHiled6YfOs2bjyqrOKns4.jpg");
        movie2.setReleaseYear(2017);
        movie2.setMediaType(MediaType.MOVIE);
        movie2.setGenres(Set.of(comedy, action, adventure));
        mediaRepository.save(movie2);

        Media movie3 = new Media();
        movie3.setTitle("Forrest Gump");
        movie3.setDescription("The life story of a man with a kind heart and a unique perspective on life.");
        movie3.setImageUrl("https://image.tmdb.org/t/p/w1280/arw2vcBveWOVZr6pxd9XTd1TdQa.jpg");
        movie3.setThumbnailUrl("https://image.tmdb.org/t/p/w1280/arw2vcBveWOVZr6pxd9XTd1TdQa.jpg");
        movie3.setReleaseYear(1994);
        movie3.setMediaType(MediaType.MOVIE);
        movie3.setGenres(Set.of(drama));
        mediaRepository.save(movie3);

        Media tvShow = new Media();
        tvShow.setTitle("Breaking Bad");
        tvShow.setDescription("A high school chemistry teacher turned methamphetamine manufacturer.");
        tvShow.setImageUrl("https://image.tmdb.org/t/p/w1280/ztkUQFLlC19CCMYHW9o1zWhJRNq.jpg");
        tvShow.setThumbnailUrl("https://image.tmdb.org/t/p/w1280/ztkUQFLlC19CCMYHW9o1zWhJRNq.jpg");
        tvShow.setReleaseYear(2008);
        tvShow.setMediaType(MediaType.SERIES);
        tvShow.setGenres(Set.of(drama, crime, thriller));
        mediaRepository.save(tvShow);

        Media movie4 = new Media();
        movie4.setTitle("The Matrix");
        movie4.setDescription("A hacker discovers a dystopian future controlled by machines.");
        movie4.setImageUrl("https://image.tmdb.org/t/p/w1280/f89U3ADr1oiB1s9GkdPOEpXUk5H.jpg");
        movie4.setThumbnailUrl("https://image.tmdb.org/t/p/w1280/f89U3ADr1oiB1s9GkdPOEpXUk5H.jpg");
        movie4.setReleaseYear(1999);
        movie4.setMediaType(MediaType.MOVIE);
        movie4.setGenres(Set.of(sciFi, action));
        mediaRepository.save(movie4);

        Media movie5 = new Media();
        movie5.setTitle("Jurassic Park");
        movie5.setDescription("Dinosaurs are resurrected and placed in a theme park.");
        movie5.setImageUrl("https://image.tmdb.org/t/p/w1280/oU7Oq2kFAAlGqbU4VoAE36g4hoI.jpg");
        movie5.setThumbnailUrl("https://image.tmdb.org/t/p/w1280/oU7Oq2kFAAlGqbU4VoAE36g4hoI.jpg");
        movie5.setReleaseYear(1993);
        movie5.setMediaType(MediaType.MOVIE);
        movie5.setGenres(Set.of(adventure, sciFi));
        mediaRepository.save(movie5);

        Media series2 = new Media();
        series2.setTitle("Stranger Things");
        series2.setDescription("A group of kids discover a secret government experiment.");
        series2.setImageUrl("https://image.tmdb.org/t/p/w1280/uOOtwVbSr4QDjAGIifLDwpb2Pdl.jpg");
        series2.setThumbnailUrl("https://image.tmdb.org/t/p/w1280/uOOtwVbSr4QDjAGIifLDwpb2Pdl.jpg");
        series2.setReleaseYear(2016);
        series2.setMediaType(MediaType.SERIES);
        series2.setGenres(Set.of(sciFi, thriller));
        mediaRepository.save(series2);

        Media series3 = new Media();
        series3.setTitle("Game of Thrones");
        series3.setDescription("Noble families vie for control of the Iron Throne in a fantasy world.");
        series3.setImageUrl("https://image.tmdb.org/t/p/w1280/1XS1oqL89opfnbLl8WnZY1O1uJx.jpg");
        series3.setThumbnailUrl("https://image.tmdb.org/t/p/w1280/1XS1oqL89opfnbLl8WnZY1O1uJx.jpg");
        series3.setReleaseYear(2011);
        series3.setMediaType(MediaType.SERIES);
        series3.setGenres(Set.of(fantasy, drama));
        mediaRepository.save(series3);

        Media series4 = new Media();
        series4.setTitle("The Witcher");
        series4.setDescription("A monster hunter seeks to protect humanity in a magical world.");
        series4.setImageUrl("https://image.tmdb.org/t/p/w1280/cZ0d3rtvXPVvuiX22sP79K3Hmjz.jpg");
        series4.setThumbnailUrl("https://image.tmdb.org/t/p/w1280/cZ0d3rtvXPVvuiX22sP79K3Hmjz.jpg");
        series4.setReleaseYear(2019);
        series4.setMediaType(MediaType.SERIES);
        series4.setGenres(Set.of(fantasy, drama));
        mediaRepository.save(series4);

        Media series5 = new Media();
        series5.setTitle("The Office");
        series5.setDescription("A mockumentary about office workers and their quirky behaviors.");
        series5.setImageUrl("https://image.tmdb.org/t/p/w1280/7DJKHzAi83BmQrWLrYYOqcoKfhR.jpg");
        series5.setThumbnailUrl("https://image.tmdb.org/t/p/w1280/7DJKHzAi83BmQrWLrYYOqcoKfhR.jpg");
        series5.setReleaseYear(2005);
        series5.setMediaType(MediaType.SERIES);
        series5.setGenres(Set.of(comedy, drama, mockumentary));
        mediaRepository.save(series5);

        Media movie6 = new Media();
        movie6.setTitle("Inception");
        movie6.setDescription("A skilled thief is tasked with planting an idea in the mind of a CEO.");
        movie6.setImageUrl("https://image.tmdb.org/t/p/w1280/oYuLEt3zVCKq57qu2F8dT7NIa6f.jpg");
        movie6.setThumbnailUrl("https://image.tmdb.org/t/p/w1280/oYuLEt3zVCKq57qu2F8dT7NIa6f.jpg");
        movie6.setReleaseYear(2010);
        movie6.setMediaType(MediaType.MOVIE);
        movie6.setGenres(Set.of(sciFi, action, thriller));
        mediaRepository.save(movie6);

        Media movie7 = new Media();
        movie7.setTitle("The Godfather");
        movie7.setDescription("The story of a powerful mafia family and its complex dynamics.");
        movie7.setImageUrl("https://image.tmdb.org/t/p/w1280/3bhkrj58Vtu7enYsRolD1fZdja1.jpg");
        movie7.setThumbnailUrl("https://image.tmdb.org/t/p/w1280/3bhkrj58Vtu7enYsRolD1fZdja1.jpg");
        movie7.setReleaseYear(1972);
        movie7.setMediaType(MediaType.MOVIE);
        movie7.setGenres(Set.of(crime, drama));
        mediaRepository.save(movie7);

        Media movie8 = new Media();
        movie8.setTitle("Pulp Fiction");
        movie8.setDescription("A series of interconnected stories in the world of organized crime.");
        movie8.setImageUrl("https://image.tmdb.org/t/p/w1280/d5iIlFn5s0ImszYzBPb8JPIfbXD.jpg");
        movie8.setThumbnailUrl("https://image.tmdb.org/t/p/w1280/d5iIlFn5s0ImszYzBPb8JPIfbXD.jpg");
        movie8.setReleaseYear(1994);
        movie8.setMediaType(MediaType.MOVIE);
        movie8.setGenres(Set.of(crime, drama));
        mediaRepository.save(movie8);

        Media movie9 = new Media();
        movie9.setTitle("The Shawshank Redemption");
        movie9.setDescription("A man wrongly convicted of murder forms a friendship with a fellow inmate.");
        movie9.setImageUrl("https://image.tmdb.org/t/p/w1280/9cqNxx0GxF0bflZmeSMuL5tnGzr.jpg");
        movie9.setThumbnailUrl("https://image.tmdb.org/t/p/w1280/9cqNxx0GxF0bflZmeSMuL5tnGzr.jpg");
        movie9.setReleaseYear(1994);
        movie9.setMediaType(MediaType.MOVIE);
        movie9.setGenres(Set.of(drama));
        mediaRepository.save(movie9);

        Media movie10 = new Media();
        movie10.setTitle("The Lion King");
        movie10.setDescription("A young lion prince is exiled and returns to reclaim his throne.");
        movie10.setImageUrl("https://image.tmdb.org/t/p/w1280/sKCr78MXSLixwmZ8DyJLrpMsd15.jpg");
        movie10.setThumbnailUrl("https://image.tmdb.org/t/p/w1280/sKCr78MXSLixwmZ8DyJLrpMsd15.jpg");
        movie10.setReleaseYear(1994);
        movie10.setMediaType(MediaType.MOVIE);
        movie10.setGenres(Set.of(animation, adventure, drama, family));
        mediaRepository.save(movie10);

        Media movie11 = new Media();
        movie11.setTitle("Titanic");
        movie11.setDescription("A young couple fall in love aboard the ill-fated RMS Titanic.");
        movie11.setImageUrl("https://image.tmdb.org/t/p/w1280/9xjZS2rlVxm8SFx8kPC3aIGCOYQ.jpg");
        movie11.setThumbnailUrl("https://image.tmdb.org/t/p/w1280/9xjZS2rlVxm8SFx8kPC3aIGCOYQ.jpg");
        movie11.setReleaseYear(1997);
        movie11.setMediaType(MediaType.MOVIE);
        movie11.setGenres(Set.of(drama, romance));
        mediaRepository.save(movie11);

        Media movie12 = new Media();
        movie12.setTitle("The Avengers");
        movie12.setDescription("A team of superheroes must work together to save Earth from an alien invasion.");
        movie12.setImageUrl("https://image.tmdb.org/t/p/w1280/vchDkX1DtqTy3bIDJ7YqmSbX965.jpg");
        movie12.setThumbnailUrl("https://image.tmdb.org/t/p/w1280/vchDkX1DtqTy3bIDJ7YqmSbX965.jpg");
        movie12.setReleaseYear(2012);
        movie12.setMediaType(MediaType.MOVIE);
        movie12.setGenres(Set.of(action, adventure, sciFi));
        mediaRepository.save(movie12);

        Media movie13 = new Media();
        movie13.setTitle("Gladiator");
        movie13.setDescription("A betrayed Roman general seeks revenge against the emperor who murdered his family.");
        movie13.setImageUrl("https://image.tmdb.org/t/p/w1280/ty8TGRuvJLPUmAR1H1nRIsgwvim.jpg");
        movie13.setThumbnailUrl("https://image.tmdb.org/t/p/w1280/ty8TGRuvJLPUmAR1H1nRIsgwvim.jpg");
        movie13.setReleaseYear(2000);
        movie13.setMediaType(MediaType.MOVIE);
        movie13.setGenres(Set.of(action, adventure, drama));
        mediaRepository.save(movie13);

        Media movie14 = new Media();
        movie14.setTitle("The Dark Knight Rises");
        movie14.setDescription("Batman returns to fight a new foe and protect Gotham City.");
        movie14.setImageUrl("https://image.tmdb.org/t/p/w1280/hr0L2aueqlP2BYUblTTjmtn0hw4.jpg");
        movie14.setThumbnailUrl("https://image.tmdb.org/t/p/w1280/hr0L2aueqlP2BYUblTTjmtn0hw4.jpg");
        movie14.setReleaseYear(2012);
        movie14.setMediaType(MediaType.MOVIE);
        movie14.setGenres(Set.of(action, adventure, drama));
        mediaRepository.save(movie14);

        Media movie15 = new Media();
        movie15.setTitle("Avatar");
        movie15.setDescription("A paraplegic marine becomes part of a project to colonize a moon.");
        movie15.setImageUrl("https://image.tmdb.org/t/p/w1280/kyeqWdyUXW608qlYkRqosgbbJyK.jpg");
        movie15.setThumbnailUrl("https://image.tmdb.org/t/p/w1280/kyeqWdyUXW608qlYkRqosgbbJyK.jpg");
        movie15.setReleaseYear(2009);
        movie15.setMediaType(MediaType.MOVIE);
        movie15.setGenres(Set.of(action, adventure, sciFi));
        mediaRepository.save(movie15);

        Media movie16 = new Media();
        movie16.setTitle("The Social Network");
        movie16.setDescription("The story of the founding of Facebook and its controversial rise.");
        movie16.setImageUrl("https://image.tmdb.org/t/p/w1280/n0ybibhJtQ5icDqTp8eRytcIHJx.jpg");
        movie16.setThumbnailUrl("https://image.tmdb.org/t/p/w1280/n0ybibhJtQ5icDqTp8eRytcIHJx.jpg");
        movie16.setReleaseYear(2010);
        movie16.setMediaType(MediaType.MOVIE);
        movie16.setGenres(Set.of(drama, biography));
        mediaRepository.save(movie16);

        Media movie17 = new Media();
        movie17.setTitle("Interstellar");
        movie17.setDescription("A group of explorers travels through a wormhole in search of a new home for humanity.");
        movie17.setImageUrl("https://image.tmdb.org/t/p/w1280/gEU2QniE6E77NI6lCU6MxlNBvIx.jpg");
        movie17.setThumbnailUrl("https://image.tmdb.org/t/p/w1280/gEU2QniE6E77NI6lCU6MxlNBvIx.jpg");
        movie17.setReleaseYear(2014);
        movie17.setMediaType(MediaType.MOVIE);
        movie17.setGenres(Set.of(sciFi, drama, adventure));
        mediaRepository.save(movie17);

        Media movie18 = new Media();
        movie18.setTitle("Guardians of the Galaxy");
        movie18.setDescription("A group of misfit criminals band together to protect a powerful orb.");
        movie18.setImageUrl("https://image.tmdb.org/t/p/w1280/r7vmZjiyZw9rpJMQJdXpjgiCOk9.jpg");
        movie18.setThumbnailUrl("https://image.tmdb.org/t/p/w1280/r7vmZjiyZw9rpJMQJdXpjgiCOk9.jpg");
        movie18.setReleaseYear(2014);
        movie18.setMediaType(MediaType.MOVIE);
        movie18.setGenres(Set.of(sciFi, action, adventure, comedy));
        mediaRepository.save(movie18);

        Media movie19 = new Media();
        movie19.setTitle("The Matrix Reloaded");
        movie19.setDescription("Neo and his allies fight a new set of challenges within the Matrix.");
        movie19.setImageUrl("https://image.tmdb.org/t/p/w1280/9TGHDvWrqKBzwDxDodHYXEmOE6J.jpg");
        movie19.setThumbnailUrl("https://image.tmdb.org/t/p/w1280/9TGHDvWrqKBzwDxDodHYXEmOE6J.jpg");
        movie19.setReleaseYear(2003);
        movie19.setMediaType(MediaType.MOVIE);
        movie19.setGenres(Set.of(sciFi, action, thriller));
        mediaRepository.save(movie19);

        Media movie20 = new Media();
        movie20.setTitle("Fight Club");
        movie20.setDescription("An insomniac office worker forms an underground fight club as a form of stress relief.");
        movie20.setImageUrl("https://image.tmdb.org/t/p/w1280/pB8BM7pdSp6B6Ih7QZ4DrQ3PmJK.jpg");
        movie20.setThumbnailUrl("https://image.tmdb.org/t/p/w1280/pB8BM7pdSp6B6Ih7QZ4DrQ3PmJK.jpg");
        movie20.setReleaseYear(1999);
        movie20.setMediaType(MediaType.MOVIE);
        movie20.setGenres(Set.of(drama, thriller));
        mediaRepository.save(movie20);

        Media movie21 = new Media();
        movie21.setTitle("The Matrix Revolutions");
        movie21.setDescription("Neo and his allies fight to end the war between humans and machines.");
        movie21.setImageUrl("https://image.tmdb.org/t/p/w1280/t1wm4PgOQ8e4z1C6tk1yDNrps4T.jpg");
        movie21.setThumbnailUrl("https://image.tmdb.org/t/p/w1280/t1wm4PgOQ8e4z1C6tk1yDNrps4T.jpg");
        movie21.setReleaseYear(2003);
        movie21.setMediaType(MediaType.MOVIE);
        movie21.setGenres(Set.of(sciFi, action, thriller));
        mediaRepository.save(movie21);
    }
}

