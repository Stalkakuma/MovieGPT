import axios from 'axios';

export const getComments = async (mediaId) => {
  const apiComments = axios.create({
    baseURL: 'http://localhost:8080/v1',
    headers: {
      'Content-Type': 'application/json',
    },
  });
  const response = await apiComments.get(`/comments/${mediaId}`);
  return response.data;
};

export const postComments = async (newComment, token) => {
  const apiComments = axios.create({
    baseURL: 'http://localhost:8080/v1',
    headers: {
      'Content-Type': 'application/json',
      Authorization: `Bearer ${token}`,
    },
  });
  const response = await apiComments.post(`/comments`, newComment);
  return response.data;
};
