import { useEffect, useState } from 'react';
import { getGenres } from '../../components/api/apiMovies';
import styles from '../../scss/admin.module.scss';

export const AdminPage = () => {
  const [error, setError] = useState('');
  const [genres, setGenres] = useState([]);
  console.log(genres);

  useEffect(() => {
    const loadData = async () => {
      try {
        const genresData = await getGenres();
        setGenres(genresData.data);
      } catch (error) {
        setError('We have error, CHANGE this');
        alert(error);
      }
    };
    loadData();
  }, []);

  return (
    <main className="container-fluid px-4">
      <h1>Admin Page</h1>
      <h2>Genres</h2>
      <section className="row justify-content-center row-cols-3 row-cols-md-6 gap-3 g-0">
        {genres.map((genre) => {
          return (
            <div className={`col ${styles.genreCard}`}>
              {genre.name}
              <span>
                <button></button>
              </span>
            </div>
          );
        })}
        <div className="col">Add New</div>
      </section>
    </main>
  );
};
