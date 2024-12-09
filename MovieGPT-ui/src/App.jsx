import 'bootstrap/dist/css/bootstrap.min.css';
import { Route, Routes } from 'react-router-dom';
import { Movies } from './pages/movies/Movies';

const App = () => {
  return (
    <Routes>
      <Route path="/" element={<Movies />} />
    </Routes>
    // pirmas darbas
  );
};

export default App;
