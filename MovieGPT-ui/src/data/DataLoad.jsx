import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { filmai } from '../MockData/MockData';  // duomenu mockinimui

export const DataLoad = ({ setData }) => {
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    axios
      .get('https://jsonplaceholder.typicode.com/posts') // Reiks pakeisti su tikru API kai bus padarytas
      .then((response) => {
        setData(filmai); // siuo metu duomenys yra mockinami. leidzian projketa grazinti 'response.data'
        setIsLoading(false);
      })
      .catch((error) => console.log(error));
  }, [setData]);

  if (isLoading) {
    return <div>Loading movies...</div>;
  }

  return null;
};
