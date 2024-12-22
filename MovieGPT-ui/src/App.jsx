import 'bootstrap/dist/js/bootstrap.bundle.min';

import { Route, Routes } from 'react-router-dom';
import { Movies } from './pages/movies/Movies';
import { Register } from './pages/register/Register';
import { Login } from './pages/login/Login';
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
        </Routes>
      </Layout>
    </AuthProvider>
  );
};

export default App;
