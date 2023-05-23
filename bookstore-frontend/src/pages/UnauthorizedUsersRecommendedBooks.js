import React, { useEffect, useState } from 'react'
import { getUnauthorizedUsersRecommendedBooks } from '../services/BookService';
import './styles/BrowseBooks.css'
import SingleBook from '../components/book/SingleBook';

export default function UnauthorizedUsersRecommendedBooks() {

    const [books, setBooks] = useState([]);

    useEffect(() => {
        getUnauthorizedUsersRecommendedBooks(setBooks);
    }, [])

  return (
    <div>
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
