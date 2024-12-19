import React, { useState, useEffect } from 'react';
import axios from 'axios';

export const DataLoad = ({ setData }) => {
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    axios
      .get('http://localhost:8080/v1/media')
      .then((response) => {
        setData(response.data); 
        setIsLoading(false);
      })
      .catch((error) => console.log(error));
  }, [setData]);

  if (isLoading) {
    return <div>Loading movies...</div>;
  }

  return null;
};
