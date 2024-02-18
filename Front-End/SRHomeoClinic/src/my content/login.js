import React from 'react'
import { Link } from 'react-router-dom'

const login = () => {
  return (
    <div className='login '>
      <h1>Login</h1>
      <form >
        <input type="text" placeholder='username' />
        <input type="password" placeholder='password'/>
        <button>Login</button>
        <span>Don't you have an account? <Link  to='/register'>Register</Link></span>
      </form>
    </div>
  )
}

export default login
