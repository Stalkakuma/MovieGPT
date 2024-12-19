import '../../cssStyles/MovieCard.css';

export const MovieCardComponent = ({ title, mediaType, releaseYear, thumbnailUrl, genres }) => {
  const genresString = genres.map((genre) => genre.name).join(', ');

  return (
    <div className="movie-card">
      <div className="movie-card-image">
        <img src={thumbnailUrl} alt={`${title} poster`} />
      </div>
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
