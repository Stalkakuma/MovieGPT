import { useEffect, useState } from 'react';
import { CommentCard } from './CommentCard';
import { Spinner, Alert } from 'react-bootstrap';
import { getComments } from '../api/apiComments';
import { CommentAdd } from './CommentAdd';

export const CommentsComponent = ({ mediaId }) => {
  const [comments, setComments] = useState([]);
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState(false);

  useEffect(() => {
    const fetchComments = async () => {
      try {
        const commentsData = await getComments(mediaId);
        if (commentsData) {
          setComments(commentsData);
        }
      } catch (error) {
        console.error('Error fetching movie data:', error);
        setError(true);
      } finally {
        setIsLoading(false);
      }
    };
    fetchComments();
  }, [mediaId]);

  if (isLoading) {
    return (
      <div className="text-center my-5">
        <Spinner animation="border" role="status">
          <span className="visually-hidden">Loading...</span>
        </Spinner>
      </div>
    );
  }

  if (error) {
    return (
      <Alert variant="danger" className="text-center">
        Failed to load movie data.
      </Alert>
    );
  }

  return (
    <div>
      <h1>Comments</h1>
      {comments && comments.length > 0 ? (
        comments.map((comment) => <CommentCard key={comment.commentId} comment={comment} />)
      ) : (
        <p>No comments available.</p>
      )}
      <CommentAdd mediaId={mediaId} />
    </div>
  );
};
