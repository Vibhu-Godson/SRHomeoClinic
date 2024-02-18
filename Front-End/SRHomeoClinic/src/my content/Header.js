import React from 'react'
import {Link} from 'react-router-dom';

export default function Header() {
  return (
<>
<nav className="navbar navbar-expand-lg navbar-light bg-light ">
  <Link to='/'>
  <a  href="#"><img  id="logo" src="mainLogo.png" alt="" /></a>
  </Link>
  <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span className="navbar-toggler-icon"></span>
  </button>

  <div className="collapse navbar-collapse" id="navbarSupportedContent">
    <form className="form-inline my-2 my-lg-0">
      <input className="form-control mr-sm-2" id="search" type="search" placeholder="Search" aria-label="Search"/>
      <button className="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
    </form>
    <ul className="navbar-nav mr-auto ">
      <li className="nav-item active">
        <Link className="nav-link" to="/">Home <span className="sr-only">(current)</span></Link>
      </li>
      <li className="nav-item">
        <Link className="nav-link" to="/about">About</Link>
      </li>
      <li className="nav-item ">
        <Link className="nav-link " to="/bookings" >Bookings</Link>
      </li>
      <li className="nav-item">
        <a className="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Disabled</a>
      </li>
      <li className='nav-item'>
        <Link to="/login"><button className='login-btn' >Log In</button></Link>
      </li>
      <li className='nav-item'>
        <Link to="/write"><button className='login-btn' >Write</button></Link>
      </li>
    </ul>
  </div>
</nav>
  </>
  )
}
