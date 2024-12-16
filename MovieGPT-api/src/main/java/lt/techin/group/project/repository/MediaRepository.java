package lt.techin.group.project.repository;

import lt.techin.group.project.model.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MediaRepository extends JpaRepository<Media, Long> {

    List<Media> findByTitleContainingIgnoreCase(String title);

    List<Media> findByGenresId(Long id);

    List<Media> findByGenresNameIgnoreCase(String name);
}
