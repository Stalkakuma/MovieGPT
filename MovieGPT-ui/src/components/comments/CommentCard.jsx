import { Card, Badge } from 'react-bootstrap';
import { AiTwotoneDelete } from 'react-icons/ai';

export const CommentCard = ({ comment }) => {
  const { userComment, dateCreated, user } = comment;
  const { username, roles } = user;

  return (
    <>
      <Card className="bg-dark text-white">
        <Card.Body>
          <Card.Title>
            {username}{' '}
            <Badge bg={roles[0] === 'ADMIN' ? 'danger' : 'secondary'} className="ms-2">
              {roles[0]}
            </Badge>
          </Card.Title>
          <Card.Text>{userComment}</Card.Text>
          <Card.Footer>Posted on: {dateCreated}</Card.Footer>
        </Card.Body>
      </Card>
      <hr style={{ borderTop: '3px solid #ffffff', margin: '0px' }} />
    </>
  );
};
