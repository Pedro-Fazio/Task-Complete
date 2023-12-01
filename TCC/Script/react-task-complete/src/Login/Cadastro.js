import React, { useState } from 'react';
import './Login.css';

const Cadastro = (props) => {
  const [usuarioNome, setUsuarioNome] = useState('')
  const [usuarioSenha, setUsuarioSenha] = useState('')
  const [confirmarSenha, setConfirmarSenha] = useState('')

  let usuario

  const cadastro = () => {
    if(!usuarioNome || !usuarioSenha || !confirmarSenha || usuarioSenha !== confirmarSenha) {
      alert('Informações incorretas')
    } else {
      let senha = usuarioSenha
      let nome = usuarioNome
      usuario = {nome, senha}
      addUsuario(usuario)
      alert('Usuário cadastrado com sucesso!')
    }

    setUsuarioNome('')
    setUsuarioSenha('')
    setConfirmarSenha('')
  }

    const addUsuario = async (usuario) => {
      let nome = usuarioNome.split("@")[0];
      const updUsuario = { ...usuario, nome: nome, email: usuarioNome, dinheiro: "0", nivel: 1, xp: 0 }

      const res = await fetch('http://localhost:8080/usuarios', {
        method: 'POST',
        headers: {
          'Content-type': 'application/json'
        },
        body: JSON.stringify(updUsuario)
      })
    }


  return(
    <div className="login-wrapper">
      <h1 className="login-titulo"> Faça o cadastro </h1>
      <form>
        <label>
          {/* <p className="login-nome"> Nome </p> */}
          <p className="login-nome"> Email </p>
          <input type="text" className="login-nome-caixa"
          value={usuarioNome} onChange={e => setUsuarioNome(e.target.value)}/>
        </label>
        <label>
          <p className="login-senha"> Senha </p>
          <input type="password" className="login-senha-caixa"
          value={usuarioSenha} onChange={e => setUsuarioSenha(e.target.value)}/>
        </label>
        <label>
          <p> Confirmar senha </p>
          <input type="password" className="login-senha-caixa"
          value={confirmarSenha} onChange={e => setConfirmarSenha(e.target.value)}/>
        </label>
        <div className="button-login">
          <button className="login-entrar" type="button" onClick={cadastro}> Realizar Cadastro </button>
          <button className="login-entrar" type="button" onClick={(e) =>
          props.cadastro(1)}> Voltar para tela Login </button>
        </div>
      </form>
    </div>
  )
}

export default Cadastro