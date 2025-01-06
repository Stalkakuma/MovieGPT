import { useState } from 'react';
import { Form, Button, Container, Alert } from 'react-bootstrap';
import { useAuth } from '../../components/context/AuthContext';
import { createMedia } from '../../components/api/apiMovies';

import styles from '../../scss/admin.module.scss';

// @Size(min = 1, max = 50, message = "Title's length must be 1 to 50 characters")
//   @NotNull(message = "Description must be provided")
//   private String title;
//   @Size(min = 1, max = 500, message = "Description's length must be 1 to 500 characters")
//   private String description;
//   @NotNull(message = "Image url must be provided")
//   private String imageUrl;
//   @NotNull(message = "Thumbnail url must be provided")
//   private String thumbnailUrl;
//   @NotNull(message = "Release year must be provided")
//   private Integer releaseYear;
//   @NotNull(message = "Media type must be provided")
//   @Enumerated(EnumType.STRING)
//   private MediaType mediaType;
//   @NotNull(message = "Genre(s) must be provided")
//   private Set<GenreDto> genres = new HashSet<>();

export const NewMedia = () => {
  const Auth = useAuth();

  const [mediaFormData, setMediaFormData] = useState({
    title: '',
    description: '',
    imageUrl: '',
    thumbnailUrl: '',
    releaseYear: 1880,
    mediaType: '',
    genres: [],
  });

  const handleMediaFormChange = (e) => {
    const { name, value } = e.target;
    setMediaFormData({
      ...mediaFormData,
      [name]: value,
    });
  };

  const handleMediaTypeChange = (e) => {
    e.preventDefault();
    setMediaFormData({
      ...mediaFormData,
      mediaType: e.target.name === 'movie' ? 'MOVIE' : 'SERIES',
    });
  };

  const handleMediaSubmit = (e) => {
    e.preventDefault();
    const adminToken = Auth.user.accessToken;
    try {
      const response = createMedia(
        {
          title: 'SADf',
          description: 'asdadqwqwfqf',
          imageUrl: 'asdasdqwqwdq',
          thumbnailUrl: 'asdasdasdqwe',
          releaseYear: 1881,
          mediaType: 'SERIES',
          genres: [{ id: 1, name: 'Action' }],
        },
        adminToken,
      );
      console.log(response);
      //   setGenres(genresData.data);
    } catch (error) {
      console.log(error);
    }
    alert('Submited');
  };

  return (
    <Form onSubmit={handleMediaSubmit}>
      <Form.Group controlId="formTitle" className="mb-3">
        <Form.Control
          className="form-input"
          type="text"
          placeholder="Title..."
          name="title"
          value={mediaFormData.title}
          onChange={handleMediaFormChange}
          required
        />
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
        <div className="col-5">
          <Form.Group controlId="formYear" className="mb-3">
            <Form.Control
              className="form-input"
              min="1880"
              max="2099"
              type="number"
              name="releaseYear"
              value={mediaFormData.releaseYear}
              onChange={handleMediaFormChange}
              required
            />
          </Form.Group>
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
        </div>
      </div>
      <button type="submit">Submit</button>
    </Form>
  );
};
