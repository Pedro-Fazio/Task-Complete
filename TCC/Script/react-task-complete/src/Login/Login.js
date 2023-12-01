import { useState, useEffect } from 'react'
import './Login.css';

const Login = (props) => {
  const [usuarioNome, setUsuarioNome] = useState('')
  const [usuarioSenha, setUsuarioSenha] = useState('')
  const [usuariosInfo, setUsuariosInfo] = useState([])

  let loginVerificacao = 2

  useEffect(() => {
    const getPerfilInfo = async () => {
      const perfilInfoFromServer = await fetchPerfisInfo()
      setUsuariosInfo(perfilInfoFromServer)
    }

    getPerfilInfo()
  }, [])

  const fetchPerfisInfo = async () => {
    const res = await fetch('http://localhost:8080/usuarios')
    const data = await res.json()

    return data
  }

  const fetchPerfilInfo = async (id) => {
    const res = await fetch(`http://localhost:8080/usuarios/${id}`)
    const data = await res.json()

    return data
}

  const entrar = async () => {
    let nomeVerificar = usuariosInfo.filter(usuario => usuario?.email.includes(usuarioNome));

    let senhaVerificar = usuariosInfo.filter(usuario => usuario?.senha.includes(usuarioSenha));
    // console.log("CONSOLE: ", JSON.stringify(usuarioLog))

    let id = nomeVerificar[0].id;
    const usuario = await fetchPerfilInfo(id)
    let nome = usuario.nome

    console.log("USUARIO: ", JSON.stringify(usuario))
    console.log("USUARIO: ", id)

    if (!usuarioNome || !usuarioSenha) {
      alert('Por favor preencha todos os campos')
    } else if (nomeVerificar[0] && senhaVerificar[0]) {
      loginVerificacao = 2
      // props.login(loginVerificacao, { nome, usuarioSenha })
      props.login(loginVerificacao, nome)
    } else {
      alert('Informações incorretas')
    }
  }
  // const entrar = () => {
  //   if (!usuarioNome || !usuarioSenha) {
  //     alert('Por favor preencha todos os campos')
  //   } else if (JSON.stringify(props.usuarios).replace(/"/g, "").includes(`${usuarioNome}`)
  //     &&
  //     JSON.stringify(props.usuarios).replace(/"/g, "").includes(`${usuarioSenha}`)) {
  //     loginVerificacao = 2
  //     props.login(loginVerificacao, { usuarioNome, usuarioSenha })
  //   } else {
  //     alert('Informações incorretas')
  //   }

  //   setUsuarioNome('')
  //   setUsuarioSenha('')
  // }


  const criarCadastro = () => {
    loginVerificacao = 0
    props.login(loginVerificacao)
  }

  return (
    <div className="login-wrapper">
      <h1 className="login-titulo" style={{color:'black'}}> Faça o Login </h1>
      <form>
        <label>
          <p className="login-nome" style={{fontWeight: 'bold'}}> Email </p>
          <input className="login-nome-caixa" type="text"
            value={usuarioNome} onChange={e => setUsuarioNome(e.target.value)} />
        </label>
        <label>
          <p className="login-senha" style={{fontWeight: 'bold'}}> Senha </p>
          <input className="login-senha-caixa" type="password"
            value={usuarioSenha} onChange={e => setUsuarioSenha(e.target.value)} />
        </label>
        <div>
          <button className="login-entrar" type="button" onClick={entrar}> Entrar </button>
        </div>
        <div>
          <button className="login-cadastro" type="button" onClick={criarCadastro}> Criar cadastro </button>
        </div>
      </form>
    </div>
  )
}

export default Login