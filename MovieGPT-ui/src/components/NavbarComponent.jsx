import { FaHome, FaStar, FaUser } from 'react-icons/fa';
import styles from '../scss/navbar.module.scss';
import svg from '../assets/Movie.svg';

export const NavbarComponent = () => {
  return (
    <>
      <nav
        className={`navbar navbar-expand-md navbar-dark justify-content-center align-items-start vh-100  ${styles.navbarStyles}`}
      >
        <button
          className={`navbar-toggler mt-2`}
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#sidebarMenu"
          aria-controls="sidebarMenu"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span className="navbar-toggler-icon"></span>
        </button>
        <div
          className="collapse navbar-collapse flex-column align-items-center gap-5 align-self-md-center"
          id="sidebarMenu"
        >
          <img src={svg} alt="movie svg" className="d-none d-md-block" />
          <ul className="navbar-nav flex-column gap-4">
            <li>
              <a href="/" className={`${styles.navItem}`}>
                <FaHome size={24} />
              </a>
            </li>
            <li>
              <a href="/*" className={`${styles.navItem}`}>
                <FaStar size={24} />
              </a>
            </li>
            <li>
              <a href="/login" className={`${styles.navItem}`}>
                <FaUser size={24} />
              </a>
            </li>
          </ul>
        </div>
      </nav>
    </>
  );
};
