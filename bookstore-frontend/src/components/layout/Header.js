import React from 'react'
import "./Header.css";
import { NavLink } from 'react-router-dom'
import HeaderCartButton from './HeaderCartButton';

export default function Header(props) {
  return (
    <header>
        <nav>
            <h1>SBNZ Bookstore</h1>
            <NavLink to="/">Home</NavLink>
            <NavLink to="/browse">Browse books</NavLink>
            <NavLink to="/register">Create account</NavLink>

            <HeaderCartButton onClick={props.onShowCart}></HeaderCartButton>
        </nav>
    </header>
  )
}
