import 'bootstrap/dist/css/bootstrap.min.css';
import { Route, Routes } from 'react-router-dom';
import { Movies } from './pages/movies/Movies';

const App = () => {
  return (
    <div>


    <Routes>
      <Route path="/" element={<Movies />} />
    </Routes>

    </div>
  );
};

export default App;
