package lt.techin.group.project.repository;

import lt.techin.group.project.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

    List<Genre> findByNameContainingIgnoreCase(String searchKeyword);

    Optional<Genre> findByNameIgnoreCase(String name);
}
