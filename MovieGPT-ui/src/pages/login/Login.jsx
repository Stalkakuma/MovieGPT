import { useState, useMemo } from "react";
import { Form, Button, Container, Alert } from "react-bootstrap";
import axios from "axios";
import { useNavigate, useLocation } from "react-router-dom";
import "../../scss/register.scss";
import svg from "../../assets/Movie.svg";
import { Link } from "react-router-dom";

export const Login = () => {
  const [formData, setFormData] = useState({
    username: "",
    password: "",
  });
  const [error, setError] = useState("");
  const navigate = useNavigate();
  const location = useLocation();

  // Determine the redirect path based on where the user came from
  const redirectPath = useMemo(() => {
    if (location.state?.from === "/register") {
      return "/"; // Redirect to homepage if coming from signup
    }
    return location.state?.from || "/"; // Otherwise fallback to the previous page or homepage
  }, [location.state]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");

    try {
      const response = await axios.post("http://localhost:8080/auth/login", formData);
      if (response.status === 200) {
        // Save the JWT token to localStorage
        const token = response.data.token;
        localStorage.setItem("jwtToken", token);
        console.log("Login successful:", response.data);
        // Redirect to the desired path
        navigate(redirectPath);
      }
    } catch (err) {
      setError(err.response?.data?.error || "Invalid username or password.");
    }
  };

  return (
    <main className="main">
      <img src={svg} alt="movie svg" />
      <Container className="form-container" style={{ maxWidth: "400px" }}>
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
        <p>Don't have an account? <Link to="/register" className="register-link">Sign Up</Link></p>
      </Container>
    </main>
  );
};


