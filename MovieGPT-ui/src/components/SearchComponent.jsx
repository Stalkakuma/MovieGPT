import React, { useState, useEffect, useCallback } from 'react';
import Container from 'react-bootstrap/Container';
import Form from 'react-bootstrap/Form';
import Navbar from 'react-bootstrap/Navbar';
import Dropdown from 'react-bootstrap/Dropdown';
import '../cssStyles/SearchComponent.css';
import { MovieCardComponent } from './MovieCardComponent';
import { DataLoad } from '../data/DataLoad';

export const SearchComponent = () => {
  const [searchQuery, setSearchQuery] = useState('');
  const [filteredMovies, setFilteredMovies] = useState([]);
  const [getData, setGetData] = useState([]);
  const [isLoading, setIsLoading] = useState(true);
  const [selectedGenre, setSelectedGenre] = useState('All Genres');
  const [allGenres, setAllGenres] = useState([]);

  // Hardcoded list of genres
  const genres = [
    'Action',
    'Crime',
    'Thriller',
    'Comedy',
    'Adventure',
    'Drama',
    'Sci-Fi',
    'Biography',
    'Fantasy',
    'Mockumentary',
    'Family',
    'Animation',
    'Romance'
  ];

  useEffect(() => {
    const loadData = async () => {
      setIsLoading(true);
      try {
        // Fetch data from DataLoad() (assuming it's async)
        const data = await DataLoad(); // Replace with actual data fetching logic
        console.log("Fetched Data:", data); // Log the structure to check

        setGetData(data);

      } catch (error) {
        console.error("Error loading data:", error);
      }
      setIsLoading(false);
    };

    loadData();
  }, []);

  useEffect(() => {
    // Sort the genres array alphabetically and add 'All Genres' at the start
    const sortedGenres = ['All Genres', ...genres.sort()];
    setAllGenres(sortedGenres);
  }, []);

  const applyFilters = useCallback(() => {
    let filtered = getData;

    // Apply search query filter
    if (searchQuery) {
      const lowerCaseQuery = searchQuery.toLowerCase();
      filtered = filtered.filter((movie) =>
        movie.title.toLowerCase().includes(lowerCaseQuery)
      );
    }

    // Apply genre filter
    if (selectedGenre !== 'All Genres') {
      filtered = filtered.filter((movie) =>
        movie.genres.some((genre) => genre.name === selectedGenre)
      );
    }

    setFilteredMovies(filtered);
  }, [searchQuery, selectedGenre, getData]);

  useEffect(() => {
    applyFilters();
  }, [searchQuery, selectedGenre, applyFilters]);

  const handleSearchChange = (event) => {
    setSearchQuery(event.target.value);
  };

  const handleGenreChange = (genre) => {
    setSelectedGenre(genre);
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
              {selectedGenre}
            </Dropdown.Toggle>

            <Dropdown.Menu>
              {allGenres.map((genre) => (
                <Dropdown.Item
                  key={genre}
                  onClick={() => handleGenreChange(genre)}
                >
                  {genre}
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
              genres={movie.genres} // Pass genres here
            />
          ))
        ) : (
          <p className="SearchMessage">
            No movies found matching your search criteria.
          </p>
        )}
      </div>

      <DataLoad setData={setGetData} />
    </>
  );
};
