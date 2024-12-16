import axios from 'axios';

const apiClient = axios.create({
  baseURL: 'http://localhost:8080/auth/',
  headers: {
    'Content-Type': 'application/json',
  },
});

export const authenticate = async (username, password) => {
  const loginData = { username, password };
  const response = await apiClient.post('/login', loginData);
  if (response.status === 200) {
    const accessToken = response.data.token;
    const data = parseJwt(response.data.token);
    const authenticatedUser = { data, accessToken };
    return authenticatedUser;
  }
  return 'error';
};

export const register = async (signupData) => {
  const response = await apiClient.post('/signup', signupData);
  return response;
};

export const parseJwt = (token) => {
  if (!token) {
    return;
  }
  const base64Url = token.split('.')[1];
  const base64 = base64Url.replace('-', '+').replace('_', '/');
  return JSON.parse(window.atob(base64));
};

export default apiClient;
