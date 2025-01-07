import { createContext, useEffect, useState, useContext } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import { getFavorites } from '../api/apiFavorite';

export const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const navigate = useNavigate();
  const location = useLocation();
  const [user, setUser] = useState(null);
  const [userFavorites, setUserFavorites] = useState([]);

  useEffect(() => {
    const storedUser = JSON.parse(localStorage.getItem('user'));
    setUser(storedUser);
  }, []);

  useEffect(() => {
    const loadFavorites = async () => {
      try {
        const response = await getFavorites(user.data.id, user.accessToken);

        setUserFavorites(response.data);
      } catch (error) {
        console.error(error);
      }
    };
    user?.data && loadFavorites();
  }, [user]);

  const getUser = () => {
    return JSON.parse(localStorage.getItem('user'));
  };

  const userIsAuthenticated = () => {
    let storedUser = localStorage.getItem('user');
    if (!storedUser) {
      return false;
    }
    storedUser = JSON.parse(storedUser);
    if (Date.now() > storedUser.data.exp * 1000) {
      logoutUser();
      return false;
    }
    return true;
  };

  const loginUser = (user) => {
    localStorage.setItem('user', JSON.stringify(user));
    setUser(user);
    navigate(location.state?.path || '/');
  };

  const logoutUser = () => {
    localStorage.removeItem('user');
    setUser(null);
  };

  const contextValue = {
    user,
    getUser,
    loginUser,
    logoutUser,
    userIsAuthenticated,
    userFavorites,
    setUserFavorites,
  };

  return <AuthContext.Provider value={contextValue}>{children}</AuthContext.Provider>;
};
export const useAuth = () => useContext(AuthContext);
