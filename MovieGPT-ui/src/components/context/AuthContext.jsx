import { createContext, useEffect, useState, useContext } from 'react';

export const AuthContext = createContext();

export const parseJwt = (token) => {
  if (!token) {
    return;
  }
  const base64Url = token.split('.')[1];
  const base64 = base64Url.replace('-', '+').replace('_', '/');
  return JSON.parse(window.atob(base64));
};

export const AuthProvider = ({ children }) => {
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
      userLogout();
      return false;
    }
    return true;
  };

  const loginUser = (user) => {
    localStorage.setItem('user', JSON.stringify(user));
    setUser(user);
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
