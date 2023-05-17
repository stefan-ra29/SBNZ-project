import React from "react";
import { useContext } from "react";
import CartContext from "../../store/cart-context";
import "./SingleBook.css";
import BookForm from "./BookForm";
import { isUserLoggedIn } from "../../utils/Utils";

export default function SingleBook(props) {
  const cartCtx = useContext(CartContext);

  function addToCartHandler(amount) {
    cartCtx.addItem({
      id: props.id,
      author: props.author,
      name: props.name,
      amount: amount,
      price: props.price,
      category: props.category,
    });
  }

  return (
    <div className="single-book-wrapper">
      <img
        className="cover-photo"
        src={require("./generic-books-image.webp")}
        alt="book cover"
      />
      <p className="name">{props.name}</p>
      <p className="author">{props.author}</p>
      <p className="price">{props.price} RSD</p>
      <p className="category">{props.category}</p>
      {isUserLoggedIn() && (
        <div className="form-wrapper">
          <BookForm onAddToCart={addToCartHandler} id={props.id}></BookForm>
        </div>
      )}
    </div>
  );
}
