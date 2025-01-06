import { Form } from 'react-bootstrap';
import { useAuth } from '../../components/context/AuthContext';

import styles from '../../scss/admin.module.scss';
import { createGenre } from '../../components/api/apiMovies';
import { useState } from 'react';

export const NewGenre = ({ setGenres }) => {
  const Auth = useAuth();
  const [genreName, setGenreName] = useState('');

  const handleGenreNameChange = (e) => {
    setGenreName(e.target.value);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const adminToken = Auth.user.accessToken;
    try {
      const response = await createGenre(genreName, adminToken);
      setGenres((prevGenres) => [...prevGenres, response.data]);
      setModalClose(true);
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <div className="modal fade" id="genreFormModal" tabIndex="-1" aria-labelledby="genreFormModal">
      <div className="modal-dialog">
        <div className={`modal-content ${styles.genreModalContent}`}>
          <div className="modal-header">
            <h3 className="modal-title fs-5 text-secondary" id="genreModalLabel">
              Create new Genre
            </h3>
            <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close" />
          </div>
          <div className="modal-body">
            <Form onSubmit={handleSubmit}>
              <Form.Group controlId="formGenreName" className="mb-3">
                <Form.Control
                  className="form-input"
                  type="text"
                  placeholder="Enter genre name..."
                  value={genreName}
                  onChange={handleGenreNameChange}
                  required
                />
              </Form.Group>
              <div className="d-flex justify-content-end gap-2">
                <button type="submit" className="btn btn-primary" data-bs-dismiss="modal">
                  Create
                </button>
                <button type="button" className="btn btn-secondary" data-bs-dismiss="modal">
                  Close
                </button>
              </div>
            </Form>
          </div>
        </div>
      </div>
    </div>
  );
};
