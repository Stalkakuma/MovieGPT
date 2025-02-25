import { useState } from 'react';
import { Form, Alert } from 'react-bootstrap';
import { useAuth } from '../../components/context/AuthContext';
import { createMedia } from '../../components/api/apiMovies';
import { FaPlus } from 'react-icons/fa6';
import { FaMinus } from 'react-icons/fa';

import styles from '../../scss/admin.module.scss';

export const NewMedia = ({ genres }) => {
  const Auth = useAuth();
  const thisYear = new Date().getFullYear();
  const [mediaFormData, setMediaFormData] = useState({
    title: '',
    description: '',
    imageUrl: '',
    thumbnailUrl: '',
    releaseYear: 1880,
    mediaType: '',
    genres: [],
  });

  const [mediaFormErrors, setMediaFormErrors] = useState({
    tittleError: '',
    descriptionError: '',
    releaseYearError: '',
    mediaTypeError: '',
    genresError: '',
  });

  const [genresCount, setGenresCount] = useState(1);
  const [genreSelectError, setGenreSelectError] = useState('');
  const [responseMessage, setResponseMessage] = useState('');

  const handleMediaFormChange = (e) => {
    const { name, value } = e.target;
    setResponseMessage('');
    setMediaFormErrors({
      ...mediaFormErrors,
      tittleError: '',
      descriptionError: '',
      releaseYearError: '',
      mediaTypeError: '',
      genresError: '',
    });
    setMediaFormData({
      ...mediaFormData,
      [name]: value,
    });
  };

  const handleMediaTypeChange = (e) => {
    e.preventDefault();

    setMediaFormErrors({ ...mediaFormErrors, mediaTypeError: '' });

    setMediaFormData({
      ...mediaFormData,
      mediaType: e.target.name === 'movie' ? 'MOVIE' : 'SERIES',
    });
  };

  const handleGenreSelect = (index, selectedGenre, e) => {
    e.preventDefault();
    setGenreSelectError('');
    setMediaFormErrors({ ...mediaFormErrors, genresError: '' });
    setMediaFormData((prev) => {
      if (prev.genres.some((genre) => genre?.id === selectedGenre.id)) {
        setGenreSelectError(`The genre ${selectedGenre.name} has already been selected.`);
        return prev;
      }
      const updatedGenres = [...prev.genres];
      updatedGenres[index] = selectedGenre;
      return { ...prev, genres: updatedGenres };
    });
  };

  const handleMediaSubmit = async (e) => {
    e.preventDefault();
    const adminToken = Auth.user.accessToken;
    setResponseMessage('');
    const submitFormErrors = {};

    mediaFormData.title.length > 50
      ? (submitFormErrors.tittleError = 'Title must be less than 50 characters')
      : mediaFormData.title.length <= 0
      ? (submitFormErrors.tittleError = 'Title cannot be empty')
      : 'Title is invalid';

    mediaFormData.description.length > 500
      ? (submitFormErrors.descriptionError = 'Description must be less than 500 characters')
      : mediaFormData.description.length <= 0
      ? (submitFormErrors.descriptionError = 'Description cannot be empty')
      : 'Description is invalid';

    mediaFormData.releaseYear < 1880
      ? (submitFormErrors.releaseYearError = 'Release year must be 1880 or higher')
      : mediaFormData.releaseYear >= thisYear + 5
      ? (submitFormErrors.releaseYearError = 'Release year should be less than 5 years from today')
      : 'Publishing year is invalid';

    mediaFormData.genres.length === 0 ? (submitFormErrors.genresError = 'At least one Genre must be selected') : '';
    !mediaFormData.mediaType ? (submitFormErrors.mediaTypeError = 'Must select Media type') : '';

    if (Object.keys(submitFormErrors).length > 0) {
      setMediaFormErrors({ ...mediaFormErrors, ...submitFormErrors });
    } else {
      try {
        const response = await createMedia(mediaFormData, adminToken);
        response.status === 201 && setResponseMessage('Media created successfully!');
      } catch (error) {
        setResponseMessage(
          error.status === 409
            ? 'Duplicated data error, try changing one of the fields'
            : error.message || 'An unexpected error occurred.',
        );
      }
    }
  };

  const handleGenreCountRemove = () => {
    if (genresCount > 1) {
      setGenresCount(genresCount - 1);
    }
  };

  return (
    <section className={`container-fluid p-5 ${styles.genreCard}`}>
      <h2 className="py-4">Create new Media</h2>
      {responseMessage && <Alert>{responseMessage}</Alert>}
      <Form onSubmit={handleMediaSubmit}>
        <Form.Group controlId="formTitle" className="mb-3">
          <Form.Control
            className="form-input"
            type="text"
            placeholder="Title..."
            name="title"
            value={mediaFormData.title}
            onChange={handleMediaFormChange}
          />
          {mediaFormErrors.tittleError && (
            <Alert className="p-0" variant="danger">
              {mediaFormErrors.tittleError}
            </Alert>
          )}
        </Form.Group>

        <Form.Group controlId="formDescription" className="mb-3">
          <textarea
            className="form-control"
            name="description"
            id="formDescriptionArea"
            rows={3}
            placeholder="Description of your Media..."
            value={mediaFormData.description}
            onChange={handleMediaFormChange}
          />
          {mediaFormErrors.descriptionError && (
            <Alert className="p-0" variant="danger">
              {mediaFormErrors.descriptionError}
            </Alert>
          )}
        </Form.Group>
        <Form.Group controlId="formImage" className="mb-3">
          <Form.Control
            className="form-input"
            type="text"
            placeholder="Url of the big Image..."
            name="imageUrl"
            value={mediaFormData.imageUrl}
            onChange={handleMediaFormChange}
            required
          />
        </Form.Group>
        <Form.Group controlId="formThumbnail" className="mb-3">
          <Form.Control
            className="form-input"
            type="text"
            placeholder="Url of the small Image..."
            name="thumbnailUrl"
            value={mediaFormData.thumbnailUrl}
            onChange={handleMediaFormChange}
            required
          />
        </Form.Group>
        <div className="row justify-content-between">
          <div className="col-7">
            <Form.Group controlId="formYear" className="mb-3 d-flex align-items-end">
              <Form.Control
                className="form-input"
                type="number"
                name="releaseYear"
                value={mediaFormData.releaseYear}
                onChange={handleMediaFormChange}
                required
              />
              <span className="mx-2"> Publication </span>
              <span> Year.</span>
            </Form.Group>
            {mediaFormErrors.releaseYearError && (
              <Alert className="p-0" variant="danger">
                {mediaFormErrors.releaseYearError}
              </Alert>
            )}
          </div>

          <div className="col-5">
            <Form.Group controlId="formType" className="mb-3 d-flex justify-content-end">
              <div className="dropdown">
                <button
                  className="btn btn-secondary dropdown-toggle"
                  type="button"
                  data-bs-toggle="dropdown"
                  aria-expanded="false"
                >
                  {mediaFormData.mediaType === 'MOVIE'
                    ? 'Movie'
                    : mediaFormData.mediaType === 'SERIES'
                    ? 'Tv-Series'
                    : 'Media type'}
                </button>
                <ul className="dropdown-menu">
                  <li>
                    <button className="dropdown-item" name="movie" onClick={handleMediaTypeChange}>
                      Movie
                    </button>
                  </li>
                  <li>
                    <button className="dropdown-item" name="series" onClick={handleMediaTypeChange}>
                      Tv-Series
                    </button>
                  </li>
                </ul>
              </div>
            </Form.Group>
            {mediaFormErrors.mediaTypeError && <Alert variant="danger">{mediaFormErrors.mediaTypeError}</Alert>}
          </div>

          <div className="container-fluid d-flex justify-content-center">
            <div className={`d-flex flex-row justify-content-between g-0`}>
              <div className={`my-3 ${styles.genreCard}`}>
                <div className="d-flex justify-content-between">
                  <FaPlus className={styles.genreNew} onClick={() => setGenresCount(genresCount + 1)} />
                  <FaMinus className={styles.genreNew} onClick={handleGenreCountRemove} />
                </div>
                <h3>Add or Remove Genre</h3>
              </div>
            </div>
          </div>

          <div className="row justify-content-start  row-cols-3 row-cols-md-6 gap-1 g-0">
            {Array.from({ length: genresCount }, (_, index) => (
              <Form.Group key={index} index={index} controlId="formGenres">
                <div className="dropdown">
                  <button
                    className="btn btn-secondary dropdown-toggle"
                    type="button"
                    data-bs-toggle="dropdown"
                    aria-expanded="false"
                  >
                    {!mediaFormData.genres[index] ? 'Select Genre' : mediaFormData?.genres[index].name}
                  </button>

                  <ul className="dropdown-menu">
                    {genres.map((genre) => (
                      <button
                        key={genre.id}
                        name={genre.name}
                        className="col dropdown-item"
                        onClick={(e) => handleGenreSelect(index, genre, e)}
                      >
                        {genre.name}
                      </button>
                    ))}
                  </ul>
                </div>
              </Form.Group>
            ))}
            {mediaFormErrors.genresError && <Alert variant="danger">{mediaFormErrors.genresError}</Alert>}
          </div>
          {genreSelectError && (
            <div className='container-fluid p-5"'>
              <Alert variant="danger">{genreSelectError}</Alert>
            </div>
          )}
        </div>

        <button type="submit" className="btn btn-primary px-5 py-2 my-4">
          Create
        </button>
      </Form>
    </section>
  );
};
