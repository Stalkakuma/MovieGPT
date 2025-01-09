import React from 'react';
import { Modal, Button } from 'react-bootstrap';

const DeletionModal = ({ show, onClose, onConfirm, movieTitle }) => {
  return (
    <Modal show={show} onHide={onClose} className="mt-5">
      <Modal.Header closeButton className="bg-dark pe-5 ps-5">
        <Modal.Title className="color-danger">Confirm Deletion</Modal.Title>
      </Modal.Header>
      <Modal.Body className="bg-dark pe-5 ps-5">
        <p>
          <strong>Are you sure you want to delete this media?</strong>
        </p>
        <p>
          <strong>{movieTitle}</strong>
        </p>
        <p className="text-danger"><strong>This action can not be undone.</strong></p>
      </Modal.Body>
      <Modal.Footer className="d-flex justify-content-between bg-dark">
        <Button variant="secondary" onClick={onClose}>
          Cancel
        </Button>
        <Button variant="danger" onClick={onConfirm}>
          Delete
        </Button>
      </Modal.Footer>
    </Modal>
  );
};

export default DeletionModal;
