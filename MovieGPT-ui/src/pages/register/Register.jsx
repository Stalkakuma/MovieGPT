import { useState } from 'react';
import { Form, Button, Container, Alert } from 'react-bootstrap';
import '../../scss/register.scss';
import svg from '../../assets/Movie.svg';
import { Link } from 'react-router-dom';
import { useAuth } from '../../components/context/AuthContext';
import { register, authenticate } from '../../components/api/apiService';

export const Register = () => {
  const Auth = useAuth();
  const isAuthenticated = Auth.userIsAuthenticated();
  const [formData, setFormData] = useState({
    username: '',
    email: '',
    password: '',
  });
  const [error, setError] = useState('');
  const [usernameError, setUsernameError] = useState('');
  const [passwordError, setPasswordError] = useState('');

  // Regex for password validation
  const passwordRegex = /^(?=.*[A-Z])(?=.*[!@#$%^&*])[A-Za-z\d!@#$%^&*]{6,}$/;

  // Username validation: 2 to 30 characters
  const usernameRegex = /^.{2,30}$/;

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });

    // Validate username
    if (name === 'username') {
      if (!usernameRegex.test(value)) {
        setUsernameError('Username must be between 2 and 30 characters long.');
      } else {
        setUsernameError('');
      }
    }

    // Validate password as user types
    if (name === 'password') {
      if (!passwordRegex.test(value)) {
        setPasswordError(
          'Password must be at least 6 characters, include one uppercase letter, and one special symbol.',
        );
      } else {
        setPasswordError('');
      }
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError('');

    // Check username validation
    if (!usernameRegex.test(formData.username)) {
      setUsernameError('Username must be between 2 and 30 characters long.');
      return;
    }

    // Check password validation
    if (!passwordRegex.test(formData.password)) {
      setPasswordError('Password must be at least 6 characters, include one uppercase letter, and one special symbol.');
      return;
    }

    try {
      const registerResponse = await register(formData);
      if (registerResponse.status === 201) {
        isAuthenticated ? Auth.logoutUser() : null;
        const authenticatedUser = await authenticate(formData.username, formData.password);
        if (authenticatedUser != 'error') {
          Auth.loginUser(authenticatedUser);
          setFormData({
            username: '',
            email: '',
            password: '',
          });
        }
      }
    } catch (err) {
      setError(err.response?.data?.message || 'An error occurred during signup.');
    }
  };

  return (
    <main className="main">
      <img src={svg} alt="movie svg" />
      <Container className="form-container" style={{ maxWidth: '400px' }}>
        <h3 className="text-right">Sign Up</h3>
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
              isInvalid={usernameError !== ''}
            />
            <Form.Control.Feedback type="invalid">{usernameError}</Form.Control.Feedback>
          </Form.Group>
          <Form.Group controlId="formEmail" className="mb-3">
            <Form.Control
              className="form-input"
              type="email"
              placeholder="Email address"
              name="email"
              value={formData.email}
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
              isInvalid={passwordError !== ''}
            />
            <Form.Control.Feedback type="invalid">{passwordError}</Form.Control.Feedback>
          </Form.Group>
          <Button variant="primary" type="submit" className="w-100 form-button">
            Create an account
          </Button>
        </Form>
        <p>
          Already have an account?{' '}
          <Link to="/login" className="login-link">
            Login
          </Link>
        </p>
      </Container>
    </main>
  );
};
