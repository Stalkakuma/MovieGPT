import { Form, Button, Container, Alert } from 'react-bootstrap';
import { useAuth } from '../../components/context/AuthContext';

import styles from '../../scss/admin.module.scss';
import { createGenre } from '../../components/api/apiMovies';

export const NewGenre = ({ setGenres }) => {
  const Auth = useAuth();

  const handleSubmit = async (e) => {
    e.preventDefault();
    const adminToken = Auth.user.accessToken;
    try {
      const response = await createGenre('genresas', adminToken);
      setGenres((prevGenres) => [...prevGenres, response.data]);
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <div className="modal fade" id="genreFormModal" tabIndex="-1" aria-labelledby="genreFormModal" aria-hidden="true">
      <div className="modal-dialog">
        <div className={`modal-content bg-pdark ${styles.genreModalContent}`}>
          <div className="modal-header">
            <h3 className="modal-title fs-5" id="genreModalLabel">
              Create new Genre
            </h3>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
            <Form onSubmit={handleSubmit}>
              <Form.Group controlId="formGenreName" className="mb-3">
                <Form.Control
                  className="form-input"
                  type="text"
                  placeholder="Enter genre name..."
                  name="name"
                  //   value={formData.username}
                  //   onChange={handleChange}
                  //   required
                />
              </Form.Group>
              <button type="submit" class="btn btn-primary">
                Save changes
              </button>
            </Form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
              Close
            </button>
            <button type="submit" class="btn btn-primary">
              Save changes
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};
