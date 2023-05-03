import React from "react";
import "./Header.css";
import { NavLink } from "react-router-dom";
import HeaderCartButton from "./HeaderCartButton";
import { isUserLoggedIn } from "../../utils/Utils";

export default function Header(props) {
  return (
    <header>
      <nav>
        <h1>SBNZ Bookstore</h1>
        <NavLink to="/">Home</NavLink>
        <NavLink to="/browse">Browse books</NavLink>
        {!isUserLoggedIn() && <NavLink to="/register">Create account</NavLink>}
        {!isUserLoggedIn() && <NavLink to="/login">Login</NavLink>}
        {isUserLoggedIn() && (
          <NavLink
            onClick={() => {
              localStorage.clear();
              window.location.replace("/");
            }}
          >
            Log out
          </NavLink>
        )}
        {isUserLoggedIn() && (
          <HeaderCartButton onClick={props.onShowCart}></HeaderCartButton>
        )}
      </nav>
    </header>
  );
}
