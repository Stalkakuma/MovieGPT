import React, { useState } from "react";
import { Form, Button, Container, Alert } from "react-bootstrap";
import axios from "axios";
import "../../scss/register.scss";
import svg from "../../assets/Movie.svg" 
import { Link } from "react-router-dom";

export const Register = () => {
  const [formData, setFormData] = useState({
    username: "",
    email: "",
    password: "",
  });
  const [success, setSuccess] = useState(false);
  const [error, setError] = useState("");
  const [passwordError, setPasswordError] = useState("");

  // Regex for password validation
  const passwordRegex = /^(?=.*[A-Z])(?=.*[!@#$%^&*])[A-Za-z\d!@#$%^&*]{6,}$/;

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });

    // Validate password as user types
    if (name === "password") {
      if (!passwordRegex.test(value)) {
        setPasswordError(
          "Password must be at least 6 characters, include one uppercase letter, and one special symbol."
        );
      } else {
        setPasswordError("");
      }
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");
    setSuccess(false);

    // Check password validation
    if (!passwordRegex.test(formData.password)) {
      setPasswordError(
        "Password must be at least 6 characters, include one uppercase letter, and one special symbol."
      );
      return;
    }

    try {
      const response = await axios.post(
        "http://localhost:8080/auth/signup",
        formData
      );
      if (response.status === 200 || response.status === 201) {
        setSuccess(true);
        setFormData({
          username: "",
          email: "",
          password: "",
        });
        console.log(response);
      }
    } catch (err) {
      setError(
        err.response?.data?.message || "An error occurred during signup."
      );
    }
  };

  return (
    <main className="register-main">
      <img src={svg} alt="movie svg"/>
      <Container className="form-container" style={{ maxWidth: "400px" }}>
        <h3 className="text-right">Sign Up</h3>
        {success && (
          <Alert variant="success">
            Sign up successful! You can now log in.
          </Alert>
        )}
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
              isInvalid={passwordError !== ""}
            />
            <Form.Control.Feedback type="invalid">
              {passwordError}
            </Form.Control.Feedback>
          </Form.Group>
          <Button variant="primary" type="submit" className="w-100 form-button">
            Create an account
          </Button>
        </Form>
        <p>Already have an account? <Link to="/login" className="login-link">Login</Link></p>
      </Container>
    </main>
  );
};
