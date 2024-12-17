import { useState } from 'react';
import { Form, Button, Container, Alert } from 'react-bootstrap';
import '../../scss/register.scss';
import svg from '../../assets/Movie.svg';
import { Link } from 'react-router-dom';
import { useAuth } from '../../components/context/AuthContext';
import { authenticate } from '../../components/api/apiService';

export const Login = () => {
  const Auth = useAuth();
  const isAuthenticated = Auth.userIsAuthenticated();
  const [formData, setFormData] = useState({
    username: '',
    password: '',
  });
  const [error, setError] = useState('');

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  const handleLogout = () => {
    Auth.logoutUser();
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError('');

    try {
      const authenticatedUser = await authenticate(formData.username, formData.password);
      if (authenticatedUser != 'error') {
        Auth.loginUser(authenticatedUser);
      }
    } catch (err) {
      setError(err.response?.data?.error || 'Invalid username or password.');
    }
  };

  return (
    <main className="main">
      <img src={svg} alt="movie svg" />
      {isAuthenticated && (
        <Container className="form-container" style={{ maxWidth: '400px' }}>
          <Alert variant="success" className="text-center">
            Already logged in
          </Alert>
          <Button variant="primary" className="w-100 form-button" onClick={handleLogout}>
            Logout
          </Button>
        </Container>
      )}
      {!isAuthenticated && (
        <Container className="form-container" style={{ maxWidth: '400px' }}>
          <h3 className="text-right">Login</h3>
          {error && <Alert variant="danger">{error}</Alert>}
          <Form onSubmit={handleSubmit}>
            <Form.Group controlId="formUsername" className="mb-3">
              <Form.Control
                className="form-input"
                type="text"
                placeholder="Username"
                name="username"
                value={formData.username}
                onChange={handleChange}
                required
              />
            </Form.Group>
            <Form.Group controlId="formPassword" className="mb-3">
              <Form.Control
                className="form-input"
                type="password"
                placeholder="Password"
                name="password"
                value={formData.password}
                onChange={handleChange}
                required
              />
            </Form.Group>
            <Button variant="primary" type="submit" className="w-100 form-button">
              Login to your account
            </Button>
          </Form>
          <p>
            Don't have an account?{' '}
            <Link to="/register" className="register-link">
              Sign Up
            </Link>
          </p>
        </Container>
      )}
    </main>
  );
};
