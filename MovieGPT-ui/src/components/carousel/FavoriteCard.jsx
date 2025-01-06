import { FaStar } from 'react-icons/fa';
import { useAuth } from '../context/AuthContext';
import { deleteFavorites } from '../api/apiFavorite';

export const FavoriteCard = ({ thumbnail, title, genres, releaseYear, mediaId, userFavorites }) => {
  const genresString = genres.map((genre) => genre.name).join(', ');
  const { user } = useAuth();

  const isFavorite = userFavorites.some((favorite) => favorite.id == mediaId);
  
  const handleDeleteFavorite = async () => {
    if (user) {
        const token = user.accessToken;
        await deleteFavorites(user.data.id, mediaId, token);
      }
  };

  return (
    <div id="favoriteCard" className="card text-bg-dark">
      <img
        style={{ objectFit: 'cover', width: '100%', height: '100%' }}
        src={thumbnail}
        className="card-img"
        alt="..."
      />
      <div className="card-img-overlay">
        <div className="bg-secondary bg-opacity-50 rounded-3 shadow p-1 mb-2 rounded">
          <h5 className="card-title">{title}</h5>
          <p className="card-text">{genresString && `${genresString}`}</p>
          <p className="card-text">
            <small>{releaseYear}</small>
          </p>
        </div>
        {isFavorite && (
          <button onClick={handleDeleteFavorite}>
            <FaStar size={24} className="position-absolute" style={{ bottom: '10px', right: '10px', color: 'gold' }} />
          </button>
        )}
      </div>
    </div>
  );
};
