import { useState } from 'react'
import { FaFacebook, FaInstagram } from 'react-icons/fa'
import './Footer.css';

const Footer = ({ }) => {
  window.addEventListener('scroll', function() {
    let footer = document.querySelector(".footer");
    let scrollHeight = document.documentElement.scrollHeight;
    let clientHeight = document.documentElement.clientHeight;
    let scrollTop = window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop;
  
    if (scrollHeight - clientHeight - scrollTop <= 50) {
      // footer.setAttribute("style", "display: block");
      footer.setAttribute("style", "visibility: visible;");
      // footer.setAttribute("style", "height: 110px;");
      // footer.setAttribute("style", "padding: 0.8em;");
    } else {
      footer.setAttribute("style", "display: none");
      // footer.setAttribute("style", "height: 0px;");
      // footer.setAttribute("style", "padding: 0em;");
    }
  });

  return (
    <footer className="footer" /*onScroll={() => aparecerFooter()}*/>
      <ul className="social_list">
        <li> <FaFacebook /> </li>
        <li> <FaInstagram /> </li>
      </ul>
      <p className="copy_right">
        <span> Task Complete </span> &copy; 2023
      </p>
    </footer>
  )
}

export default Footer
