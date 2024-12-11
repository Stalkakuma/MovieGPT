import "../cssStyles/MovieCard.css";

export const MovieCardComponent = ({
  id, // Add id to identify the movie
  title,
  genre,
  rating,
  year,
  miniature,
  favorite,
  onToggleFavorite, // Function to handle toggling
}) => {
  return (
    <div className="movie-card">
      <div className="movie-card-image">
        <img src={miniature} alt={`${title} poster`} />
        <div
          className={`bookmark-icon ${favorite ? "favorite" : ""}`}
          onClick={() => onToggleFavorite(id, !favorite)} // Trigger toggle on click
        >
          🔖
        </div>
      </div>
      <div className="movie-card-info">
        <span className="movie-card-meta">
          {year} • {genre} • {rating}
        </span>
        <h3 className="movie-card-title">{title}</h3>
      </div>
    </div>
  );
};
