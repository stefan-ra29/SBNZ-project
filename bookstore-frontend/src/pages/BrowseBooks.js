import React, { useEffect, useState } from 'react'
import { getAllBooks } from '../services/BookService';
import './styles/BrowseBooks.css'
import SingleBook from '../components/book/SingleBook';

export default function BrowseBooks() {

    const [books, setBooks] = useState([]);

    useEffect(() => {
        getAllBooks(setBooks);
    }, [])

  return (
    <div>
        <h2>This is where you browse books and add them to your cart</h2>

        <div className='books-wrapper'>
            {books.map((book) => {
                return (
                    <SingleBook 
                        key={book.id}
                        id={book.id}
                        name={book.name}
                        author={book.author}
                        price={book.price}
                        category={book.category}
                    />
                );
            })}
        </div>

    </div>
  )
}
