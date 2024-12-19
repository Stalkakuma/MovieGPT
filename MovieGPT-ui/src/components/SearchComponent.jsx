import React, { useState, useEffect } from 'react';
import Container from 'react-bootstrap/Container';
import Form from 'react-bootstrap/Form';
import Navbar from 'react-bootstrap/Navbar';
import Dropdown from 'react-bootstrap/Dropdown';
import '../cssStyles/SearchComponent.css';
import { MovieCardComponent } from './MovieCardComponent';
import { getGenres, getMovies } from './api/apiMovies';

export const SearchComponent = () => {
  const [movieData, setMovieData] = useState([]);
  const [genresData, setGenresData] = useState([]);
  const [isLoading, setIsLoading] = useState(false);

  const [searchQuery, setSearchQuery] = useState('');
  const [genreFilter, setGenreFilter] = useState('');

  const [filteredMovies, setFilteredMovies] = useState([]);

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
        console.error('Error loading data:', error);
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
      <Navbar expand="lg" className="search-navbar">
        <Container fluid className="search-container">
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
              All Genres
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

      <div className="ReturnedMovieCard">
        {isLoading ? (
          <p className="SearchMessage">Loading movies...</p>
        ) : filteredMovies.length > 0 ? (
          filteredMovies.map((movie) => (
            <MovieCardComponent
              key={movie.id}
              id={movie.id}
              thumbnailUrl={movie.thumbnailUrl}
              releaseYear={movie.releaseYear}
              mediaType={movie.mediaType}
              title={movie.title}
              genres={movie.genres}
            />
          ))
        ) : (
          <p className="SearchMessage">No movies found matching your search criteria.</p>
        )}
      </div>
    </>
  );
};
