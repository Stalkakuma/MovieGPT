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

export default apiMovieClient;
