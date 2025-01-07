import { useEffect, useState } from 'react';
import { FiBookmark } from 'react-icons/fi';
import { useAuth } from '../context/AuthContext';
import { addFavorite, deleteFavorites } from '../api/apiFavorite';
import styles from './favorite.module.scss';

export const FavoritesButton = ({ media }) => {
  const Auth = useAuth();
  const [isFavorite, setIsFavorite] = useState(false);
  console.log(isFavorite);

  useEffect(() => {
    if (Auth.userFavorites.some((favorite) => favorite.id === media.id)) {
      setIsFavorite(true);
    } else setIsFavorite(false);
  }, [media, Auth.isFavorite]);

  const handleAddFavorite = async () => {
    try {
      const response = await addFavorite(Auth.user.data.id, media.id, Auth.user.accessToken, Auth.user.data);
      Auth.setUserFavorites([...Auth.userFavorites, media]);
      setIsFavorite(true);
    } catch (err) {
      console.error(err);
    }
  };

  const handleRemoveFavorite = async () => {
    try {
      const response = await deleteFavorites(Auth.user.data.id, media.id, Auth.user.accessToken, Auth.user);
      const updatedFavorites = Auth.userFavorites.filter((favoriteMedia) => favoriteMedia.id !== media.id);
      Auth.setUserFavorites(updatedFavorites);
      setIsFavorite(false);
    } catch (err) {
      console.error(err);
    }
  };

  return (
    <span
      className={`position-absolute ${styles.favorite}`}
      onClick={isFavorite ? handleRemoveFavorite : handleAddFavorite}
    >
      <FiBookmark className={`${isFavorite ? styles.iconFilled : styles.icon}`} />
    </span>
  );
};
