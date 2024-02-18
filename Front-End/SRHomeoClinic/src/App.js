
import './App.css';
import './style.scss';
import Header from './my content/Header';
import Home from './my content/Home';
import About from './my content/about';
import Bookings from './my content/bookings';
import Register from './my content/register';
import Login from './my content/login';
import Footer from './my content/Footer';
import Single from './my content/single';
import Sider from './my content/sider';
import Write from './my content/write';
import {Routes,Route} from 'react-router-dom';

// import {
//   createBrowserRouter,
//   RouterProvider,
// } from "react-router-dom";
function App() {
  return (
    <>
      <Header />
      <Routes>
        <Route path="/" element={<Home/>} />
        <Route path="/about" element={<About/>} /> 
        <Route path="/bookings" element={<Bookings/>} /> 
        <Route path="/login" element={<Login/>} /> 
        <Route path="/register" element={<Register/>} /> 
        <Route path="/single" element={<Single/>} /> 
        <Route path="/Menu" element={<Sider/>} /> 
        <Route path="/write" element={<Write/>} /> 
      </Routes>
      <Footer />
    </>
  );
}

export default App;
