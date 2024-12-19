import { createContext, useEffect, useState, useContext } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';

export const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const navigate = useNavigate();
  const location = useLocation();
  const [user, setUser] = useState(null);

  useEffect(() => {
    const storedUser = JSON.parse(localStorage.getItem('user'));
    setUser(storedUser);
  }, []);

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
  };

  return <AuthContext.Provider value={contextValue}>{children}</AuthContext.Provider>;
};
export const useAuth = () => useContext(AuthContext);
