import React, { useState, useEffect } from 'react';
import Container from 'react-bootstrap/Container';
import Form from 'react-bootstrap/Form';
import Navbar from 'react-bootstrap/Navbar';
import Dropdown from 'react-bootstrap/Dropdown';
import '../../cssStyles/SearchComponent.css';
import { MovieCardComponent } from '../movie-card/MovieCardComponent';
import { getGenres, getMovies } from '../api/apiMovies';
import { Alert } from 'react-bootstrap';
import { UserFavoritesCarousel } from '../carousel/UserFavoritesCarouselComponent';

export const SearchComponent = () => {
  const [movieData, setMovieData] = useState([]);
  const [genresData, setGenresData] = useState([]);
  const [isLoading, setIsLoading] = useState(false);
  const [searchQuery, setSearchQuery] = useState('');
  const [genreFilter, setGenreFilter] = useState('');
  const [filteredMovies, setFilteredMovies] = useState([]);
  const [error, setError] = useState('');
  const [showError, setShowError] = useState(false);

  useEffect(() => {
    const loadData = async () => {
      setIsLoading(true);
      try {
        const moviedData = await getMovies();
        const genresData = await getGenres();
        const sortedGenres = [{ id: genresData.data.length + 1, name: 'All Genres' }, ...genresData.data.sort()];

        setMovieData(moviedData.data);
        setGenresData(sortedGenres);
      } catch (error) {
        setError(error?.message || 'Invalid username or password.');
        handleError();
      }
      setIsLoading(false);
    };
    loadData();
  }, []);

  useEffect(() => {
    const filterMovies = () => {
      let updatedMovies = [...movieData];
      if (searchQuery) {
        updatedMovies = updatedMovies.filter((movie) =>
          movie.title.toLowerCase().includes(searchQuery.toLocaleLowerCase()),
        );
      }

      if (genreFilter) {
        updatedMovies = updatedMovies.filter((movie) => movie.genres.some((genre) => genre.name === genreFilter.name));
      }
      setFilteredMovies(updatedMovies);
    };
    filterMovies();
  }, [searchQuery, genreFilter, movieData]);

  const handleError = () => {
    setShowError(true);
    setTimeout(() => {
      setShowError(false);
      setError('');
    }, 15000);
  };

  const handleSearchChange = (event) => {
    setSearchQuery(event.target.value);
  };

  const handleGenreChange = (genre) => {
    if (genre.name === 'All Genres') {
      setGenreFilter('');
      return;
    }
    setGenreFilter(genre);
  };

  return (
    <>
      <Navbar expand="lg" className="search-navbar my-md-4 p-0 mt-5 mb-3">
        <Container fluid className="search-container g-0 mt-4 mt-md-0">
          <Form className="search-form">
            <Form.Control
              type="search"
              placeholder="Search movies or tv series by title"
              className="search-input"
              aria-label="Search"
              value={searchQuery}
              onChange={handleSearchChange}
            />
          </Form>

          <Dropdown className="genre-dropdown">
            <Dropdown.Toggle variant="secondary" id="dropdown-basic">
              {genreFilter ? genreFilter.name : 'All Genres'}
            </Dropdown.Toggle>

            <Dropdown.Menu>
              {genresData.map((genre) => (
                <Dropdown.Item key={genre.id} onClick={() => handleGenreChange(genre)}>
                  {genre.name}
                </Dropdown.Item>
              ))}
            </Dropdown.Menu>
          </Dropdown>
        </Container>
      </Navbar>

      {showError && (
        <Alert variant="danger" className="alert fs-4">
          {error}
        </Alert>
      )}

      {filteredMovies.length === 0 && !error && (searchQuery || genreFilter) && (
        <Alert className="fs-5">No movies found matching your search criteria.</Alert>
      )}

      {isLoading && (
        <Alert variant="success" className="fs-5">
          Loading movies...
        </Alert>
      )}

      <UserFavoritesCarousel />

      <div className="ReturnedMovieCard">
        {!isLoading &&
          filteredMovies.map((movie) => (
            <MovieCardComponent
              key={movie.id}
              media={movie}
              id={movie.id}
              thumbnailUrl={movie.thumbnailUrl}
              releaseYear={movie.releaseYear}
              mediaType={movie.mediaType}
              title={movie.title}
              genres={movie.genres}
            />
          ))}
      </div>
    </>
  );
};
