import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min';

import { Route, Routes } from 'react-router-dom';
import { Movies } from './pages/movies/Movies';
import { Register } from './pages/register/Register';
import { Login } from './pages/login/Login';
import { AuthProvider } from './components/context/AuthContext';
import { NavbarComponent as Sidebar } from './components/sidebar/NavbarComponent';
import { NotFound } from './pages/notFoundPage/NotFoundPage';
import styles from './scss/App.module.scss';

const App = () => {
  return (
    <AuthProvider>
      <div className={`d-flex ${styles.appStyles}`}>
        <Sidebar />
        <Routes>
          <Route path="/*" element={<NotFound />} />
          <Route path="/" element={<Movies />} />
          <Route path="/register" element={<Register />} />
          <Route path="/login" element={<Login />} />
        </Routes>
      </div>
    </AuthProvider>
  );
};

export default App;
