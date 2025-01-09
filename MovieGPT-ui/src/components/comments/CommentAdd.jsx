import React, { useState } from 'react';
import { useAuth } from '../context/AuthContext';
import { Card, Form, Button, InputGroup, Alert } from 'react-bootstrap';
import { postComments } from '../api/apiComments';

export const CommentAdd = ({ mediaId }) => {
  const { getUser } = useAuth();
  const [newComment, setNewComment] = useState('');
  const [error, setError] = useState(null);

  const handleSubmit = async (e) => {
    e.preventDefault();

    const user = getUser();

    if (!newComment.trim()) {
      setError('Comment cannot be empty.');
      return;
    }

    if (newComment.length > 300) {
      setError('Comment cannot be more than 255 characters.');
      return;
    }

    const commentData = {
      userComment: newComment,
      mediaId: mediaId,
      userId: user.data.id,
    };

    try {
      const token = user.accessToken;
      await postComments(commentData, token);
      setNewComment('');
      setError(null);
      window.location.reload();
    } catch (error) {
      console.log(error);
      setError('Failed to post comment. Please try again.');
    }
  };

  const handleResize = (e) => {
    const textarea = e.target;
    textarea.style.height = 'auto';
    textarea.style.height = `${textarea.scrollHeight}px`;
  };

  return (
    <Card className="mb-3 py-3 bg-dark text-white sticky-top">
      {getUser() == null ? (
        <h2>
          Please <a href="/login">login</a> to leave a comment
        </h2>
      ) : (
        <Card.Body>
          <Form onSubmit={handleSubmit}>
            <Form.Label>Add a Comment</Form.Label>
            {error && <Alert variant="danger">{error}</Alert>}
            <InputGroup>
              <Form.Control
                as="textarea"
                rows={2}
                type="text"
                value={newComment}
                onChange={(e) => setNewComment(e.target.value)}
                onInput={handleResize}
                className="bg-dark text-white"
              />
              <Button type="submit" variant="danger">
                Submit
              </Button>
            </InputGroup>
          </Form>
        </Card.Body>
      )}
    </Card>
  );
};
