import React from 'react'

export default function User({name, username, password}) {
  return (
    <div className='user'>
        <p className='name'>{name}</p>
        <p className='author'>{username}</p>
        <p className='price'>{password}</p>
    </div>
  )
}