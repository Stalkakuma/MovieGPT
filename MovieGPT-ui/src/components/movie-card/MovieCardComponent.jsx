import '../../cssStyles/MovieCard.css';
import { Link } from "react-router-dom";

export const MovieCardComponent = ({ id, title, mediaType, releaseYear, thumbnailUrl, genres }) => {
  const genresString = genres.map((genre) => genre.name).join(', ');

  return (
    <div className="movie-card">
      <Link to={`/movie/${id}`}>
        <div className="movie-card-image">
          <img src={thumbnailUrl} alt={`${title} poster`} />
        </div>
      </Link>
      <div className="movie-card-info">
        <span className="movie-card-meta">
          {releaseYear} • {mediaType}
        </span>
        <p className="movie-card-meta"> {genresString && ` ${genresString}`}</p>
        <h3 className="movie-card-title">{title}</h3>
      </div>
    </div>
  );
};
