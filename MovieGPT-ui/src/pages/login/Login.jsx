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

  const [formErrors, setFormErrors] = useState({
    usernameErr: '',
    passwordErr: '',
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormErrors({
      usernameErr: '',
      passwordErr: '',
    });
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
    const submitFormErrors = {};
    formData.username.length < 2 ? (submitFormErrors.usernameErr = 'Username should contain at least 2 letters') : 'as';
    formData.password.length <= 0 ? (submitFormErrors.passwordErr = 'Should include password') : '';

    if (Object.keys(submitFormErrors).length > 0) {
      setFormErrors({ ...formErrors, ...submitFormErrors });
    } else {
      try {
        const authenticatedUser = await authenticate(formData.username, formData.password);
        if (authenticatedUser != 'error') {
          Auth.loginUser(authenticatedUser);
        }
      } catch (err) {
        setError('Invalid username or password.');
      }
    }
  };

  return (
    <main className="main">
      <img src={svg} alt="movie svg" />
      {isAuthenticated && (
        <Container className="form-container" style={{ maxWidth: '400px' }}>
          <Alert variant="success" className="text-center mt-3">
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
          {error && (
            <Alert variant="danger" className="alert">
              {error}
            </Alert>
          )}
          <Form onSubmit={handleSubmit}>
            <Form.Group controlId="formUsername" className="mb-3">
              <Form.Control
                className="form-input"
                type="text"
                placeholder="Username"
                name="username"
                value={formData.username}
                onChange={handleChange}
              />
              {formErrors.usernameErr && (
                <Alert className="p-2 my-2" variant="danger">
                  {formErrors.usernameErr}
                </Alert>
              )}
            </Form.Group>
            <Form.Group controlId="formPassword" className="mb-3">
              <Form.Control
                className="form-input"
                type="password"
                placeholder="Password"
                name="password"
                value={formData.password}
                onChange={handleChange}
              />
              {formErrors.passwordErr && (
                <Alert className="p-2 my-2" variant="danger">
                  {formErrors.passwordErr}
                </Alert>
              )}
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
