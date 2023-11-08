import React from 'react';
import './Login.css';
import { Link } from 'react-router-dom'

const Logado = (props) => {

  return(
    <div id='logado'>
        Bem vindo, {props.nomeUsuario.replace(/"/g, "")}
    </div>
      // <> 
      //   <Header usuariosInfo={usuariosInfo}/>
      //   <Rotina completarTarefa={completarTarefa}/>
      //   <Footer />
      // </>
  )
}

export default Logado