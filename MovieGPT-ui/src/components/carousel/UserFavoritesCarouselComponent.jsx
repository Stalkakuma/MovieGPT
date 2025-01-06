import { useEffect, useState } from 'react';
import { useAuth } from '../context/AuthContext';
import { FavoriteCard } from './FavoriteCard';
import { getMovies } from '../api/apiMovies';
import { getFavorites } from '../api/apiFavorite';

const getCardsPerSlide = () => {
  if (window.innerWidth >= 992) return 4;
  if (window.innerWidth >= 768) return 3;
  return 2;
};

export const UserFavoritesCarousel = () => {
  const { user } = useAuth();
  const [movieData, setMovieData] = useState([]);
  const [recommendedMovies, setRecommendedMovies] = useState([]);
  const [favoritesMedia, setFavoritesMedia] = useState([]);
  const [cardsPerSlide, setCardsPerSlide] = useState(getCardsPerSlide);

  useEffect(() => {
    getMovies()
      .then((response) => {
        setMovieData(response.data);
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);

  useEffect(() => {
    if (user) {
      const token = user.accessToken;

      getFavorites(user.data.id, token)
        .then((response) => {
          setFavoritesMedia(response.data);
        })
        .catch((error) => {
          console.log(error);
        });
    }
  }, [user]);

  useEffect(() => {
    setRecommendedMovies(() => {
      return movieData.sort(() => 0.5 - Math.random()).slice(0, 12);
    });
  }, [movieData]);

  useEffect(() => {
    const handleResize = () => setCardsPerSlide(getCardsPerSlide());
    window.addEventListener('resize', handleResize);
    return () => window.removeEventListener('resize', handleResize);
  }, []);

  if (!recommendedMovies.length) {
    return <p>Loading carousel...</p>;
  }

  const slideMedia = (filteredMovies, slideSize) => {
    const slide = [];
    for (let i = 0; i < filteredMovies.length; i += slideSize) {
      slide.push(filteredMovies.slice(i, i + slideSize));
    }
    return slide;
  };

  const allSlides = slideMedia(favoritesMedia.length !== 0 ? favoritesMedia : recommendedMovies, cardsPerSlide);

  return (
    <>
      <div>
        <h2>{favoritesMedia.length > 0 ? 'Your Favorites' : 'Trending for You'}</h2>
        <div id="carouselExampleAutoplaying" className="carousel slide" data-bs-ride="carousel">
          <div className="carousel-inner">
            {allSlides.map((mediaGroup, index) => (
              <div key={index} className={`carousel-item ${index === 0 ? 'active' : ''}`}>
                <div className="row">
                  {mediaGroup.map((media) => (
                    <div key={media.id} className="col-lg-3 col-md-4 col-6">
                      <FavoriteCard
                        mediaId={media.id}
                        thumbnail={media.thumbnailUrl}
                        title={media.title}
                        genres={media.genres}
                        releaseYear={media.releaseYear}
                        userFavorites={favoritesMedia}
                      />
                    </div>
                  ))}
                </div>
              </div>
            ))}
          </div>
          <button
            className="carousel-control-prev"
            type="button"
            data-bs-target="#carouselExampleAutoplaying"
            data-bs-slide="prev"
          >
            <span className="carousel-control-prev-icon" aria-hidden="true"></span>
            <span className="visually-hidden">Previous</span>
          </button>
          <button
            className="carousel-control-next"
            type="button"
            data-bs-target="#carouselExampleAutoplaying"
            data-bs-slide="next"
          >
            <span className="carousel-control-next-icon" aria-hidden="true"></span>
            <span className="visually-hidden">Next</span>
          </button>
        </div>
      </div>
      <h2>Recommended for you</h2>
    </>
  );
};
