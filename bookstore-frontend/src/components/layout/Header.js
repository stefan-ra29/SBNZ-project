import React from 'react'
import "./Header.css";
import { NavLink } from 'react-router-dom'

export default function Header() {
  return (
    <header>
        <nav>
            <h1>SBNZ Bookstore</h1>
            <NavLink to="/">Home</NavLink>
            <NavLink to="/browse">Browse books</NavLink>
        </nav>
    </header>
  )
}
