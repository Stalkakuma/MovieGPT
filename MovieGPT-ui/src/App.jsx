import 'bootstrap/dist/css/bootstrap.min.css';
import { Route, Routes } from 'react-router-dom';
import { Movies } from './pages/movies/Movies';
import { Register } from './pages/forms/Register';
import { Login } from './pages/forms/Login';

const App = () => {
  return (
    <Routes>
      <Route path="/" element={<Movies />} />
      <Route path="/register" element={<Register />} />
      <Route path="/login" element={<Login />} />
    </Routes>
  );
};

export default App;
