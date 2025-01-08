import { FaEdit, FaTrashAlt } from 'react-icons/fa';
import { Button, Toast, ToastContainer } from 'react-bootstrap';
import DeletionModal from './DeletionModal';
import React, { useState, useEffect } from 'react';
import { deleteMedia, getGenres, editMedia } from '../../components/api/apiMovies';
import { useAuth } from '../../components/context/AuthContext';
import { useNavigate } from 'react-router-dom';
import EditMovieModal from './EditMovieModal';

export const AdminButtons = ({ movieData, updateMovieData }) => {
  const [showModal, setShowModal] = useState(false);
  const [showDeletionToast, setShowDeletionToast] = useState(false);
  const [showSuccessToast, setShowSuccessToast] = useState(false);
  const Auth = useAuth();
  const [error, setError] = useState(null);
  const navigate = useNavigate();
  const [showEditModal, setShowEditModal] = useState(false);
  const [updatedMovie, setUpdatedMovie] = useState({
    id: movieData.id,
    title: movieData.title,
    description: movieData.description,
    imageUrl: movieData.imageUrl,
    thumbnailUrl: movieData.thumbnailUrl,
    releaseYear: movieData.releaseYear,
    mediaType: movieData.mediaType,
    genres: movieData.genres,
  });

  const [availableGenres, setAvailableGenres] = useState([]);

  useEffect(() => {
    const fetchGenres = async () => {
      try {
        const response = await getGenres();
        setAvailableGenres(response.data);
      } catch (error) {
        console.error('Error fetching genres:', error);
      }
    };

    fetchGenres();
  }, []);

  const handleEdit = () => {
    setShowEditModal(true);
  };

  const handleCloseEditModal = () => {
    setShowEditModal(false);
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setUpdatedMovie({ ...updatedMovie, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    const movieToUpdate = {
      ...updatedMovie,
      genres: updatedMovie.genres,
    };

    try {
      const adminToken = Auth.user.accessToken;
      await editMedia(movieToUpdate, adminToken);
      setShowSuccessToast(true);
      setShowEditModal(false);
      setTimeout(() => setShowSuccessToast(false), 3000);
      updateMovieData(movieToUpdate);
    } catch (error) {
      setShowEditModal(false);
      setError('Failed to update movie. Please try again.');
      setTimeout(() => {
        setError(null);
      }, 2000);
    }
  };

  const handleDelete = () => {
    setShowModal(true);
  };

  const handleCloseModal = () => {
    setShowModal(false);
  };

  const handleConfirmDelete = async () => {
    try {
      const adminToken = Auth.user.accessToken;
      await deleteMedia(movieData.id, adminToken);
      setShowModal(false);
      setShowDeletionToast(true);
      setTimeout(() => {
        navigate('/');
      }, 2500);
    } catch (error) {
      setShowModal(false);
      setError('Failed to delete media. Please try again later.');
      setTimeout(() => {
        setError(null);
      }, 3000);
    }
  };

  return (
    <div>
      <Button onClick={handleEdit} variant="secondary" className="me-2">
        <FaEdit className="mb-1" />
      </Button>
      <Button onClick={handleDelete} variant="danger">
        <FaTrashAlt className="mb-1" />
      </Button>

      <DeletionModal
        show={showModal}
        onClose={handleCloseModal}
        onConfirm={handleConfirmDelete}
        movieTitle={movieData.title}
      />

      <ToastContainer
        position="top-center"
        className="bg-success-subtle opacity-100 text-secondary-emphasis rounded-3 mt-3"
      >
        <Toast show={showDeletionToast} onClose={() => setShowDeletionToast(false)} delay={3000} autohide>
          <Toast.Body>
            <strong>{movieData.title} deleted successfully!</strong>
          </Toast.Body>
        </Toast>
      </ToastContainer>

      {error && (
        <ToastContainer
          position="top-center"
          className="bg-danger-subtle opacity-100 text-danger rounded-3 border-danger mt-3"
        >
          <Toast delay={3000} autohide>
            <Toast.Body><strong>{error}</strong></Toast.Body>
          </Toast>
        </ToastContainer>
      )}

      <ToastContainer
        position="top-center"
        className="bg-success-subtle opacity-100 text-secondary-emphasis rounded-3 mt-3"
      >
        <Toast show={showSuccessToast} onClose={() => setShowSuccessToast(false)} delay={3000} autohide>
          <Toast.Body><strong>Movie updated successfully!</strong></Toast.Body>
        </Toast>
      </ToastContainer>

      <EditMovieModal
        show={showEditModal}
        onHide={handleCloseEditModal}
        updatedMovie={updatedMovie}
        handleInputChange={handleInputChange}
        handleSubmit={handleSubmit}
        availableGenres={availableGenres}
        selectedGenres={updatedMovie.genres}
      />
    </div>
  );
};
