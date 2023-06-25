import React from "react";
import "./Header.css";
import { NavLink } from "react-router-dom";
import { isUserLoggedIn } from "../../utils/Utils";

export default function Header(props) {
  return (
    <header>
      <nav>
        <h1>SBNZ Bank</h1>
        <NavLink to="/">Home</NavLink>
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
        {isUserLoggedIn() && <NavLink to="/transaction">Transaction</NavLink>}
      </nav>
    </header>
  );
}
