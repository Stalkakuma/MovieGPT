import { FavoritesButton } from '../favorite/favorites';
import { useAuth } from '../context/AuthContext';

export const FavoriteCard = ({ thumbnail, title, genres, releaseYear, media }) => {
  const { user } = useAuth();
  const genresString = genres.map((genre) => genre.name).join(', ');

  return (
    <div id="favoriteCard" className="card text-bg-dark">
      <img
        style={{ objectFit: 'cover', width: '100%', height: '100%' }}
        src={thumbnail}
        className="card-img"
        alt="..."
      />
      <div className="card-img-overlay">
        <div
          className="bg-opacity-50 rounded-3 p-1 mb-2"
          style={{ boxShadow: 'inset 30px 30px 30px rgba(0, 0, 0, 0.5)' }}
        >
          <h5 className="card-title">{title}</h5>
          <p className="card-text">{genresString && `${genresString}`}</p>
          <p className="card-text">
            <small>{releaseYear}</small>
          </p>
        </div>
        <div>{user && <FavoritesButton media={media} />}</div>
      </div>
    </div>
  );
};
