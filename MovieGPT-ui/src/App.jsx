import 'bootstrap/dist/js/bootstrap.bundle.min';

import { Route, Routes } from 'react-router-dom';
import { Movies } from './pages/movies/Movies';
import { Register } from './pages/register/Register';
import { Login } from './pages/login/Login';
import { MoviePage } from './pages/movie_page/MoviePage';
import { AuthProvider } from './components/context/AuthContext';
import { NotFound } from './pages/notFoundPage/NotFoundPage';
import { Layout } from './components/layout/Layout';

const App = () => {
  return (
    <AuthProvider>
      <Layout>
        <Routes>
          <Route path="/*" element={<NotFound />} />
          <Route path="/" element={<Movies />} />
          <Route path="/register" element={<Register />} />
          <Route path="/login" element={<Login />} />
          <Route path="/movie/:id" element={<MoviePage />} />
        </Routes>
      </Layout>
    </AuthProvider>
  );
};

export default App;
