import 'bootstrap/dist/css/bootstrap.min.css';
import { Route, Routes } from 'react-router-dom';
import { Movies } from './pages/movies/Movies';
import { Register } from './pages/register/Register';
import { Login } from './pages/login/Login';
import { AuthProvider } from './components/context/AuthContext';
import { NavbarComponent } from './components/NavbarComponent';
import { Favorites } from './pages/favorites/Favorites';
import { NotFound } from './pages/notFoundPage/NotFoundPage';

const App = () => {
  return (
    <AuthProvider>
      <div className="app-container">
        <div>
          <NavbarComponent></NavbarComponent>
        </div>

        <div className="main-content">
          <Routes>
            <Route path="/" element={<Movies />} />
            <Route path="/favorites" element={<Favorites />} />
            <Route path="/register" element={<Register />} />
            <Route path="/login" element={<Login />} />
            <Route path="/*" element={<NotFound />} />
          </Routes>
        </div>
      </div>
    </AuthProvider>
  );
};

export default App;
