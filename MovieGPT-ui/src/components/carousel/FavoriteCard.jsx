import { FaStar } from 'react-icons/fa';
import { useAuth } from '../context/AuthContext';
import { deleteFavorites } from '../api/apiFavorite';

export const FavoriteCard = ({ thumbnail, title, genres, releaseYear, mediaId, userFavorites }) => {
  const genresString = genres.map((genre) => genre.name).join(', ');
  const { user } = useAuth();

  let userDto;

  if (user) {
    userDto = {
      id: `${user.data.id}`,
      username: `${user.data.username}`,
      email: `${user.data.email}`,
      roles: [`${user.data.roles}`],
    };
  }

  const isFavorite = userFavorites.some((favorite) => favorite.id == mediaId);

  const handleDeleteFavorite = async () => {
    if (user) {
      const token = user.accessToken;
      await deleteFavorites(user.data.id, mediaId, token, userDto);
      window.location.reload();
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
        {isFavorite && (
          <button
            onClick={handleDeleteFavorite}
            style={{
              background: 'none',
              border: 'none',
              position: 'absolute',
              bottom: '10px',
              right: '10px',
              cursor: 'pointer',
            }}
          >
            <FaStar size={24} className="position-absolute" style={{ bottom: '10px', right: '10px', color: 'gold' }} />
          </button>
        )}
      </div>
    </div>
  );
};
