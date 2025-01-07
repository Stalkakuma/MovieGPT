import { useCallback, useEffect, useState } from 'react';
import { deleteGenre, getGenres } from '../../components/api/apiMovies';
import { AiTwotoneDelete } from 'react-icons/ai';
import { CiCirclePlus } from 'react-icons/ci';
import { Alert } from 'react-bootstrap';

import styles from '../../scss/admin.module.scss';
import { useAuth } from '../../components/context/AuthContext';

import { NewGenre } from './NewGenre';
import { NewMedia } from './NewMedia';

export const AdminPage = () => {
  const Auth = useAuth();
  const [genreError, setGenreError] = useState('');
  const [genres, setGenres] = useState([]);

  const loadData = useCallback(async () => {
    try {
      const genresData = await getGenres();
      setGenres(genresData.data);
    } catch (error) {
      setError('We have error, CHANGE this');
      alert(error);
    }
  }, []);

  useEffect(() => {
    loadData();
  }, [loadData]);

  const handleGenreDelete = async (genreId) => {
    const adminToken = Auth.user.accessToken;
    deleteGenre(genreId, adminToken);
    setGenres((prevGenres) => prevGenres.filter((genre) => genre.id !== genreId));
  };

  return (
    <>
      <main className="container-fluid px-4">
        <h1>Admin Page</h1>
        <h2 className="my-4">Genres:</h2>
        {genreError && <Alert variant="danger">{genreError}</Alert>}
        <section className="row justify-content-center row-cols-3 row-cols-md-6 gap-3 g-0">
          {genres.map((genre) => {
            return (
              <div key={genre.id} className={`d-flex flex-column justify-content-center  ${styles.genreCard}`}>
                <span>{genre.name}</span>
                <span>
                  <AiTwotoneDelete className={`${styles.genreDelete}`} onClick={() => handleGenreDelete(genre.id)} />
                </span>
              </div>
            );
          })}
          <div className="col d-flex align-content-center justify-content-center" key="0">
            <CiCirclePlus data-bs-toggle="modal" data-bs-target="#genreFormModal" className={`${styles.genreNew}`} />
          </div>
        </section>
        <h2 className="my-4">Media:</h2>
        <NewMedia genres={genres} />
      </main>
      <NewGenre setGenres={setGenres} setGenreError={setGenreError} />
    </>
  );
};
