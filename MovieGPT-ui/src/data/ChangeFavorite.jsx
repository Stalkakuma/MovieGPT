import axios from "axios";

// Function to update the "favorite" field
export const toggleFavorite = async (id, favorite) => {
  try {
    const response = await axios.patch(`https://jsonplaceholder.typicode.com/posts${id}`, {
      favorite,
    });
    return response.data;
  } catch (error) {
    console.error("Error updating favorite status:", error);
    throw error;
  }
};
