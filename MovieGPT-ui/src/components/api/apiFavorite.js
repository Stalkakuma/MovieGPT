import axios from 'axios';

export const getFavorites = async (userId, token) => {
  const apiFavorite = axios.create({
    baseURL: 'http://localhost:8080/v1',
    headers: {
      'Content-Type': 'application/json',
      Authorization: `Bearer ${token}`,
    },
  });
  const response = await apiFavorite.get(`/favorite?userId=${userId}`);
  return response;
};
export const deleteFavorites = async (userId, mediaId, token, user) => {
  const apiFavorite = axios.create({
    baseURL: 'http://localhost:8080/v1',
    headers: {
      'Content-Type': 'application/json',
      Authorization: `Bearer ${token}`,
    },
  });
  const response = await apiFavorite.delete(`/favorite?userId=${userId}&mediaId=${mediaId}`, user);
  return response;
};

export const addFavorite = async (userId, mediaId, token, user) => {
  const apiFavorite = axios.create({
    baseURL: 'http://localhost:8080/v1',
    headers: {
      'Content-Type': 'application/json',
      Authorization: `Bearer ${token}`,
    },
  });
  const response = await apiFavorite.post(`/favorite?userId=${userId}&mediaId=${mediaId}`, user);
  return response;
};
