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


export function placeOrder(books) {
    console.log(books);
    axios.post(apiURL + '/order', books)
    .then((response) => {
        console.log(response)
        alert('Your price with discount is' + ' ' + response.data)
    })
    .catch((error) => {
        console.log(error)
    })
}