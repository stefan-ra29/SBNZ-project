import axios from 'axios'

const apiURL = "http://localhost:8080/api/book";


export function getAllBooks(setBooks) {
    axios.get(apiURL)
    .then((response) => {
        setBooks(response.data)
    })
    .catch((error) => {
        console.log(error);
    })
}