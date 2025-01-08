import React, { useState, useEffect } from 'react';
import { Modal, Form, Button, Alert } from 'react-bootstrap';

const EditMovieModal = ({ show, onHide, updatedMovie, handleInputChange, handleSubmit }) => {
  const [errors, setErrors] = useState([]);

  const validateForm = () => {
    let validationErrors = [];

    if (updatedMovie.title.length < 1 || updatedMovie.title.length > 50) {
      validationErrors.push("Title's length must be 1 to 50 characters");
    }

    if (updatedMovie.description.length < 1 || updatedMovie.description.length > 500) {
      validationErrors.push("Description's length must be 1 to 500 characters");
    }

    if (!updatedMovie.imageUrl) {
      validationErrors.push('Image URL must be provided');
    }

    if (!updatedMovie.thumbnailUrl) {
      validationErrors.push('Thumbnail URL must be provided');
    }

    const currentYear = new Date().getFullYear();
    if (!updatedMovie.releaseYear || updatedMovie.releaseYear < 1880 || updatedMovie.releaseYear > currentYear + 4) {
      validationErrors.push('Release year must be provided and it must be between 1880 and ' + (currentYear + 4));
    }

    return validationErrors;
  };

  const handleFormSubmit = async (e) => {
    e.preventDefault();
    const validationErrors = validateForm();
    if (validationErrors.length > 0) {
      setErrors(validationErrors);
    } else {
      try {
        await handleSubmit(e);
      } catch (error) {
        if (error.response && error.response.data && error.response.data.message) {
          setErrors([error.response.data.message]);
        } else {
          setErrors(['An unknown error occurred. Please try again.']);
        }
      }
    }
  };

  return (
    <Modal show={show} onHide={onHide} className="pe-5 ps-5">
      <Modal.Header closeButton className="bg-dark pe-5 ps-5">
        <Modal.Title>Edit Movie Details</Modal.Title>
      </Modal.Header>
      <Modal.Body className="bg-dark pe-5 ps-5">
        {errors.length > 0 && (
          <div className="mb-3">
            {errors.map((error, index) => (
              <Alert key={index} variant="danger">
                {error}
              </Alert>
            ))}
          </div>
        )}

        <Form onSubmit={handleFormSubmit}>
          <Form.Group className="mb-3" controlId="formTitle">
            <Form.Label>Title</Form.Label>
            <Form.Control type="text" name="title" value={updatedMovie.title} onChange={handleInputChange} required />
          </Form.Group>

          <Form.Group className="mb-3" controlId="formDescription">
            <Form.Label>Description</Form.Label>
            <Form.Control
              as="textarea"
              name="description"
              value={updatedMovie.description}
              onChange={handleInputChange}
              required
            />
          </Form.Group>

          <Form.Group className="mb-3" controlId="formReleaseYear">
            <Form.Label>Release Year</Form.Label>
            <Form.Control
              type="number"
              name="releaseYear"
              value={updatedMovie.releaseYear}
              onChange={handleInputChange}
              required
            />
          </Form.Group>

          <Form.Group className="mb-3" controlId="formImageUrl">
            <Form.Label>Image URL</Form.Label>
            <Form.Control
              type="text"
              name="imageUrl"
              value={updatedMovie.imageUrl}
              onChange={handleInputChange}
              required
            />
          </Form.Group>

          <Form.Group className="mb-3" controlId="formThumbnailUrl">
            <Form.Label>Thumbnail URL</Form.Label>
            <Form.Control
              type="text"
              name="thumbnailUrl"
              value={updatedMovie.thumbnailUrl}
              onChange={handleInputChange}
              required
            />
          </Form.Group>

          <Form.Group className="mb-3" controlId="formMediaType">
            <Form.Label>Media Type</Form.Label>
            <Form.Select name="mediaType" value={updatedMovie.mediaType} onChange={handleInputChange} required>
              <option value="MOVIE">MOVIE</option>
              <option value="SERIES">SERIES</option>
            </Form.Select>
          </Form.Group>

          <div className="d-flex justify-content-between pt-3 pb-3">
            <Button variant="secondary" onClick={onHide}>
              Cancel
            </Button>
            <Button variant="primary" type="submit">
              Save Changes
            </Button>
          </div>
        </Form>
      </Modal.Body>
    </Modal>
  );
};

export default EditMovieModal;
