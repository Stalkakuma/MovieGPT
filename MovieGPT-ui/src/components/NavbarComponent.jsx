import { FaHome, FaStar, FaUser } from 'react-icons/fa'; 
import '../cssStyles/Navbar.css'

export const NavbarComponent = () => {
  return (
    <div className="vertical-navbar">
  
      <a href="/" className="nav-link">
        <FaHome size={24} />
      </a>

      
      <a href="/favorites" className="nav-link">
        <FaStar size={24} />
      </a>

      
      <a href="/login" className="nav-link">
        <FaUser size={24} />
      </a>
    </div>
  );
};
