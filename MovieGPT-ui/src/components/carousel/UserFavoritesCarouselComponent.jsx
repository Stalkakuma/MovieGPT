import { useEffect, useState } from 'react';
import { useAuth } from '../context/AuthContext';
import { FavoriteCard } from './FavoriteCard';
import { getMovies } from '../api/apiMovies';

const getCardsPerSlide = () => {
  if (window.innerWidth >= 992) return 4;
  if (window.innerWidth >= 768) return 3;
  return 2;
};

export const UserFavoritesCarousel = () => {
  const { userFavorites } = useAuth();
  const [movieData, setMovieData] = useState([]);
  const [recommendedMovies, setRecommendedMovies] = useState([]);
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

  const allSlides = slideMedia(userFavorites.length !== 0 ? userFavorites : recommendedMovies, cardsPerSlide);

  return (
    <>
      <div>
        <h2>{userFavorites.length > 0 ? 'Your Favorites' : 'Trending for You'}</h2>
        <div id="carouselExampleAutoplaying" className="carousel slide" data-bs-ride="carousel">
          <div className="carousel-indicators" style={{ bottom: '-35px' }}>
            {allSlides.map((slide, index) => (
              <button
                key={index}
                type="button"
                data-bs-target="#carouselExampleAutoplaying"
                data-bs-slide-to={index}
                className={index === 0 ? 'active' : ''}
                aria-current={index === 0 ? 'true' : 'false'}
                aria-label={`Slide ${index + 1}`}
              ></button>
            ))}
          </div>
          <div className="carousel-inner">
            {allSlides.map((mediaGroup, index) => (
              <div key={index} className={`carousel-item ${index === 0 ? 'active' : ''}`}>
                <div className="row">
                  {mediaGroup.map((media) => (
                    <div key={media.id} className="col-lg-3 col-md-4 col-6">
                      <FavoriteCard
                        media={media}
                        mediaId={media.id}
                        thumbnail={media.thumbnailUrl}
                        title={media.title}
                        genres={media.genres}
                        releaseYear={media.releaseYear}
                        userFavorites={userFavorites}
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
            style={{ height: '25%', width: '50px', top: '50%' }}
          >
            <span className="carousel-control-prev-icon visually-hidden" aria-hidden="true"></span>
            <span className="visually-hidden">Previous</span>
          </button>
          <button
            className="carousel-control-next"
            type="button"
            data-bs-target="#carouselExampleAutoplaying"
            data-bs-slide="next"
            style={{ height: '25%', width: '50px', top: '50%' }}
          >
            <span className="carousel-control-next-icon visually-hidden" aria-hidden="true"></span>
            <span className="visually-hidden">Next</span>
          </button>
        </div>
      </div>
      <h2 style={{ marginTop: '20px' }}>Recommended for you</h2>
    </>
  );
};
