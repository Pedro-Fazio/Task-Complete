import React, { useEffect } from 'react';
import { Link } from 'react-router-dom'
import './Jogar.css';

const Jogar = ({ }) => {

    const mudarBackground = (cor) => {
        let body = document.querySelector("body");
        body.setAttribute("style", `background-color: ${cor}`);
    }

  return (
    <div className="jogar-container" onLoad={() => mudarBackground("#1DA7CF")}>
        <p className="titulo-task-complete"> Task Complete </p>
        <img className="img-jogar" src="https://i.imgur.com/D50NWnq.png" alt="botão escrito jogar"></img>
        {/* <Link to="/rotina"> */}
        <Link to="/login">
            <button className="botao-jogar-agora" onClick={() => mudarBackground("#FEF5F5")}>
                Jogue Agora
            </button>
        </Link>
    </div>
  )
}

export default Jogar
