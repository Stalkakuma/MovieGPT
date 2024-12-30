import { Navigate } from 'react-router-dom';

export const AdminRoute = ({ children }) => {
  const user = localStorage.getItem('user');

  if (!user) {
    return <Navigate to="/login" />;
  }

  try {
    const userRoles = JSON.parse(user).data.roles;

    if (userRoles.some((role) => role === 'ADMIN')) {
      return children;
    } else {
      //TODO unauthorized page
      return <Navigate to="/unauthorized" />;
    }
  } catch (error) {
    console.error('Token Error', error);
  }
};
