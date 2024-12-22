import { FaHome, FaStar, FaUser } from 'react-icons/fa';
import styles from '../../scss/navbar.module.scss';
import svg from '../../assets/Movie.svg';

export const NavbarComponent = () => {
  return (
    <nav className={`navbar navbar-expand-md navbar-dark flex-row sticky-md-top fixed-top  ${styles.navbarStyles}`}>
      <div className="container-fluid g-0 px-4 flex-row">
        <button
          className={`navbar-toggler`}
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#sidebarMenu"
          aria-controls="sidebarMenu"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span className="navbar-toggler-icon"></span>
        </button>
        <div className="collapse navbar-collapse flex-column gap-5 my-md-5" id="sidebarMenu">
          <img src={svg} alt="movie svg" className="d-none d-md-block" />
          <ul className="navbar-nav flex-md-column flex-row gap-5 justify-content-start p-3 mx-5 mx-md-0 p-md-2 gap-md-4">
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
      </div>
    </nav>
  );
};
