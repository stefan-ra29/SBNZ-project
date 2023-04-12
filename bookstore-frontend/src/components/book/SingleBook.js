import React from 'react'
import './SingleBook.css'

export default function SingleBook({name, author, price, category}) {
  return (
    <div className='single-book-wrapper'>
        <img className='cover-photo' src={require("./generic-books-image.webp")} alt='book cover'/>
        <p className='name'>{name}</p>
        <p className='author'>{author}</p>
        <p className='price'>{price} RSD</p>
        <p className='category'>{category}</p>
    </div>
  )
}
