import '../../cssStyles/MovieCard.css';
import { FavoritesButton } from '../favorite/favorites';
import { useAuth } from '../context/AuthContext';

export const MovieCardComponent = ({ id, title, mediaType, releaseYear, thumbnailUrl, genres, media }) => {
  const { user } = useAuth();
  const genresString = genres.map((genre) => genre.name).join(', ');

  return (
    <div className="movie-card">
      <div className="movie-card-image position-relative">
        {user && <FavoritesButton media={media} />}
        <img src={thumbnailUrl} alt={`${title} poster`} />
      </div>
      <div className="movie-card-info">
        <span className="movie-card-meta">
          {releaseYear} • {mediaType}
        </span>
        <p className="movie-card-meta"> {genresString && ` ${genresString}`}</p>
        <a className="movie-link" href={`/movie/${id}`}>
          <h3 className="movie-card-title">{title}</h3>
        </a>
      </div>
    </div>
  );
};
