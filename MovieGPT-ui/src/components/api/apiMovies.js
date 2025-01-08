import axios from 'axios';

const apiMovieClient = axios.create({
  baseURL: 'http://localhost:8080/v1',
  headers: {
    'Content-Type': 'application/json',
  },
});

export const getMovies = async () => {
  const response = await apiMovieClient.get('/media');
  return response;
};

export const getGenres = async () => {
  const response = await apiMovieClient.get('/genres');
  return response;
};

export const deleteGenre = async (genreId, token) => {
  const response = await apiMovieClient.delete(`/genres/${genreId}`, {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });
  return response;
};

export const createGenre = async (genreName, token) => {
  const response = await apiMovieClient.post(
    `/genres?name=${genreName}`,
    {},
    {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    },
  );
  return response;
};

export const createMedia = async (mediaData, token) => {
  const response = await apiMovieClient.post('/media', mediaData, {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });
  return response;
};

export const editMedia = async (mediaData, token) => {
  const response = await apiMovieClient.put('/media', mediaData, {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });
  return response;
};

export const deleteMedia = async (mediaId, token) => {
  const response = await apiMovieClient.delete(`/media/${mediaId}`, {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });
  return response;
};

export default apiMovieClient;
