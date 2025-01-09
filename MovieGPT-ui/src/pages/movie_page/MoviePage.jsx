import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import axios from 'axios';
import { Container, Row, Col, Image, Spinner, Alert, Badge } from 'react-bootstrap';
import '../../scss/movie_page.scss';
import { useAuth } from '../../components/context/AuthContext';
import { AdminButtons } from './AdminButtons';
import { CommentsComponent } from '../../components/comments/CommentsComponent';

export const MoviePage = () => {
  const { id } = useParams();
  const [movieData, setMovieData] = useState(null);
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState(false);
  const { user } = useAuth();

  useEffect(() => {
    const fetchMovieData = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/v1/media/${id}`);
        if (response.data) {
          setMovieData(response.data);
        }
      } catch (error) {
        console.error('Error fetching movie data:', error);
        setError(true);
      } finally {
        setIsLoading(false);
      }
    };

    fetchMovieData();
  }, [id]);

  const updateMovieData = (updatedMovie) => {
    setMovieData(updatedMovie);
  };

  if (isLoading) {
    return (
      <div className="text-center my-5">
        <Spinner animation="border" role="status">
          <span className="visually-hidden">Loading...</span>
        </Spinner>
      </div>
    );
  }

  if (error) {
    return (
      <Alert variant="danger" className="text-center">
        Failed to load movie data.
      </Alert>
    );
  }

  return (
    <>
      <Container fluid className="movie-page">
        {user?.data?.roles?.includes('ADMIN') && (
          <div className="d-inline-flex justify-content-end w-100">
            <AdminButtons movieData={movieData} updateMovieData={updateMovieData} />
          </div>
        )}
        <Row className="justify-content-center align-items-center">
          <Col xs={12} lg={5} className="text-center text-lg-end mb-4 mb-lg-0">
            <Image
              src={movieData.imageUrl || 'https://via.placeholder.com/400x600'}
              alt={`${movieData.title} Poster`}
              fluid
              className="movie-poster"
            />
          </Col>
          <Col xs={12} lg={7} className="movie-details">
            <h1 className="movie-title mb-3">{movieData.title}</h1>
            <p>
              <strong>Type:</strong> {movieData.mediaType || 'No type available.'}
            </p>
            <p>
              <strong>Release Year:</strong> {movieData.releaseYear || 'N/A'}
            </p>
            <p>
              <strong>Genres:</strong>{' '}
              {movieData.genres?.length > 0
                ? movieData.genres.map((genre, index) => (
                    <Badge bg="secondary" key={index} className="me-2">
                      {genre.name}
                    </Badge>
                  ))
                : 'No Genres Available'}
            </p>

            <p>
              <strong>Rating:</strong> {movieData.rating || 'Not Rated'}
            </p>
            <p>
              <strong>Description:</strong> {movieData.description || 'No description available.'}
            </p>
          </Col>
        </Row>
        <CommentsComponent mediaId={id} />
      </Container>
    </>
  );
};
