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
export const deleteFavorites = async (userId, mediaId, token, userDto) => {
  const apiFavorite = axios.create({
    baseURL: 'http://localhost:8080/v1',
    headers: {
      'Content-Type': 'application/json',
      Authorization: `Bearer ${token}`,
    },
  });
    const response = await apiFavorite.delete(`/favorite?userId=${userId}&mediaId=${mediaId}`, {data: userDto});
    return response;
};