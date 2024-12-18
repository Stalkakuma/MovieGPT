import React, { useEffect, useState, useRef, Suspense } from "react";
import axios from "axios";
import { useNavigate, useLocation } from "react-router-dom";

export const MoviePage = () => {
  const [movieData, setMovieData] = useState({
    title: "Default Movie Title",
    description: "This is a default description for the movie/series.",
    imageUrl: "https://via.placeholder.com/1920x1080",
    releaseYear: 2024,
    mediaType: "MOVIE",
    genres: [{ name: "Drama" }, { name: "Action" }],
  });

  const [isImageLoaded, setImageLoaded] = useState(false);
  const backgroundRef = useRef(null);
  const navigate = useNavigate();
  const location = useLocation();

  useEffect(() => {
    // Fetch movie data
    const fetchMovieData = async () => {
      try {
        // Replace api/movie/1 with the correct endpoint: v1/media/{id}
        const response = await axios.get("http://localhost:8080/v1/media/1");
        if (response.data) {
          const { title, description, imageUrl, releaseYear, mediaType, genres } = response.data;
          setMovieData({
            title,
            description,
            imageUrl,
            releaseYear,
            mediaType,
            genres,
          });
        }
      } catch (error) {
        console.error("Error fetching movie data:", error);
      }
    };
  
    fetchMovieData();
  }, []);
  

  useEffect(() => {
    // Lazy loading the background image
    const observer = new IntersectionObserver(
      ([entry]) => {
        if (entry.isIntersecting) {
          setImageLoaded(true);
          observer.disconnect();
        }
      },
      { threshold: 0.5 }
    );

    if (backgroundRef.current) observer.observe(backgroundRef.current);
  }, []);

  return (
    <div
      ref={backgroundRef}
      style={{
        backgroundImage: isImageLoaded ? `url(${movieData.imageUrl})` : "none",
        backgroundSize: "cover",
        backgroundPosition: "center",
        height: "100vh",
      }}
    >
      <div>
        <h1>{movieData.title}</h1>
        <p>Release Year: {movieData.releaseYear}</p>
        <p>Media Type: {movieData.mediaType}</p>
        <div>
          Genres: {movieData.genres.map((genre, index) => (
            <span key={index}>{genre.name} </span>
          ))}
        </div>
        <p>{movieData.description}</p>
      </div>
    </div>
  );
};
