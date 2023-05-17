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

