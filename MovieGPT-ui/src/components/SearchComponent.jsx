import React, { useState, useEffect } from 'react';
import Container from 'react-bootstrap/Container';
import Form from 'react-bootstrap/Form';
import Navbar from 'react-bootstrap/Navbar';
import '../cssStyles/SearchComponent.css';
import { MovieCardComponent } from './MovieCardComponent';
import { DataLoad } from '../data/DataLoad';
import { toggleFavorite } from "../data/ChangeFavorite" 

export const SearchComponent = () => {
  const [searchQuery, setSearchQuery] = useState('');
  const [filteredMovies, setFilteredMovies] = useState([]);
  const [getData, setGetData] = useState([]);

  
  useEffect(() => {
    const loadData = () => {
      setGetData([]); 
    };
    loadData();
  }, []);

  
  useEffect(() => {
    if (searchQuery === '') {
      setFilteredMovies(getData);
    } else {
      const filtered = getData.filter(
        (movie) =>
          movie.title.toLowerCase().includes(searchQuery.toLowerCase()) ||
          movie.genre?.toLowerCase().includes(searchQuery.toLowerCase()),
      );
      setFilteredMovies(filtered);
    }
  }, [searchQuery, getData]);

  
  const handleSearchChange = (event) => {
    const query = event.target.value;
    setSearchQuery(query);
  };

  
  const handleToggleFavorite = async (id, favorite) => {
    try {
      await toggleFavorite(id, favorite); 
      setGetData((prevData) =>
        prevData.map((movie) =>
          movie.id === id ? { ...movie, favorite } : movie
        )
      );
    } catch (error) {
      console.error('Failed to toggle favorite:', error);
    }
  };

  return (
    <>
      <Navbar expand="lg" className="search-navbar">
        <Container fluid className="search-container">
          <Form className="search-form">
            <Form.Control
              type="search"
              placeholder="Search movies or tv series by title or genre"
              className="search-input"
              aria-label="Search"
              value={searchQuery}
              onChange={handleSearchChange}
            />
          </Form>
        </Container>
      </Navbar>

      <div className="ReturnedMovieCard">
        {filteredMovies.length > 0 ? (
          filteredMovies.map((movie) => (
            <MovieCardComponent
              key={movie.id}
              id={movie.id} 
              miniature={movie.miniature}
              rating={movie.rating}
              year={movie.year}
              genre={movie.genre}
              title={movie.title}
              favorite={movie.favorite}
              onToggleFavorite={handleToggleFavorite} 
            />
          ))
        ) : (
          <p className='SearchMessage' >No movies found matching your search criteria.</p>
        )}
      </div>

      <DataLoad setData={setGetData} />
    </>
  );
};
