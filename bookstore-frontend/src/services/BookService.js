import { getAxios } from "../utils/AxiosWrapper";
import jwt_decode from 'jwt-decode';
import { toast } from "react-toastify";

const apiURL = "http://localhost:8080/api/book";

export function getAllBooks(setBooks) {
  getAxios()
    .get(apiURL)
    .then((response) => {
      setBooks(response.data);
    })
    .catch((error) => {
      console.log(error);
    });
}

export function placeOrder(books) {
  console.log(books);
  const token = localStorage.getItem("token");
  var decode = jwt_decode(token)
  console.log(decode.id)

  const orderData = {
    books: books,
    userId: decode.id
  };

  console.log(orderData)
  console.log(orderData.userId)

  getAxios()
    .post(apiURL + "/order", orderData)
    .then((response) => {
      console.log(response);
      toast.success("Your price with discount is" + " " + response.data);
    })
    .catch((error) => {
      console.log(error);
    });
}

export function rateBook(enteredRate, bookId) {
  const token = localStorage.getItem("token");
  var decode = jwt_decode(token)
  console.log(decode.id)

  const rateBook = {
    rate: Number(enteredRate),
    bookId: bookId,
    userId: decode.id
  };

  getAxios()
    .post("http://localhost:8080/api/rate/rateBook", rateBook)
    .then((response) => {
      console.log(response);
    })
    .catch((error) => {
      console.log(error.response.data);
      toast.error(error.response.data + " " + "with" + " " + rateBook.rate);

    });

  console.log(rateBook)
}

