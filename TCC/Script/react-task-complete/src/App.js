import { useState, useEffect } from 'react'
import { BrowserRouter, Route, Routes } from 'react-router-dom'
import Rotina from './Rotina/Rotina'
import Habitos from './Rotina/Rotina'
import Sobre from './Sobre/Sobre'
import Inventario from './Inventario/Inventario'
import Perfil from './Inventario/Perfil'
import Loja from './Loja/Loja'
import Header from './Header/Header'
import Footer from './Footer/Footer'
import Jogar from './Jogar/Jogar'
import Tarefa from './Rotina/Tarefas/Tarefa'
import Diaria from './Rotina/Diarias/Diaria'
import Habito from './Rotina/Habitos/Habito'
import Conta from './Rotina/Contas/Conta'
import Login from './Login/Login'
import Cadastro from './Login/Cadastro'
import Logado from './Login/Logado'

function App() {
  const [usuariosInfo, setUsuariosInfo] = useState([])
  const [nomeUsuario, setNomeUsuario] = useState()
  const [estadoLogin, setEstadoLogin] = useState(1)
  const [usuarioLogado, setUsuarioLogado] = useState()
  const [darkModeVerify, setDarkModeVerify] = useState()
  //var usuarioLogado

  /*** Requisições ao backend ***/

  useEffect(() => {
    const getPerfilInfo = async () => {
      setUsuarioLogado(await fetchPerfilLogado())
      setUsuariosInfo(await fetchPerfisInfo())
    }

    getPerfilInfo()
  }, [])

    /*** Fetchs ***/
  
  const fetchPerfilLogado = async () => {
    const res = await fetch('http://localhost:8080/usuarioLogado')
    const data = await res.json()

    return data
}

  const fetchPerfisInfo = async () => {
    const res = await fetch('http://localhost:8080/usuarios')
    const data = await res.json()

    //alert("usuariosInfo: ", res)

    return data
  }

  const fetchPerfilInfo = async (id) => {
    const res = await fetch(`http://localhost:8080/usuarios/${id}`)
    const data = await res.json()

    //alert("perfilLogado: ", data)

    return data
}

  /*** Outras funcionalidades ***/

  const aumentarNivel = async (id) => {
    const usuario = await fetchPerfilInfo(id)
    let xp = usuario.xp
    let nivel = usuario.nivel
    
    xp = parseFloat(xp) + 5
    if(xp >= 100) {
      nivel = parseFloat(nivel) + 1
      xp = 0
    }

    const updUsuario = { ...usuario, xp: xp, nivel: nivel }

    const res = await fetch(`http://localhost:8080/${id}`, {
      method: 'PUT',
      headers: {
          'Content-type': 'application/json',
      },
      body: JSON.stringify(updUsuario),
    })

    const data = await res.json()
  }

  const completarTarefa = async () => {
    //Soma a recompensa da tarefa no dinheiro atual
    // let dinheiroAtualizado = parseFloat(usuariosInfo[0].dinheiro) + 5
    let dinheiroAtualizado = parseFloat(usuarioLogado[0].dinheiro) + 5
    // let id = "1";

    //Transforma em string
    dinheiroAtualizado = "" + dinheiroAtualizado
    let usuarioLog = usuariosInfo.filter(usuario => usuario?.email.includes(usuarioLogado[0].email));
    usuarioLog = usuarioLog[0]
    // console.log("CONSOLE: ", JSON.stringify(usuarioLog))

    let id = usuarioLog.id;
    const usuario = await fetchPerfilInfo(id)
    const updUsuario = { ...usuario, dinheiro: dinheiroAtualizado }



    const res = await fetch(`http://localhost:8080/usuarios/${usuarioLog.id}`, {
        method: 'PUT',
        headers: {
            'Content-type': 'application/json',
        },
        body: JSON.stringify(updUsuario),
    })

    const data = await res.json()

    aumentarNivel(id)

    setUsuariosInfo(
      usuariosInfo.map((usuarioInfo) =>
        usuarioInfo.id === id ? { ...usuarioInfo, dinheiro: data.dinheiro } : usuarioInfo
      )
    )
  }

  //*** Informações de Login ***/


  const logarUsuario = async (usuario) => {
    const usuarioLogadoFromServer = await fetchPerfilLogado()
    //let id = usuarioLogadoFromServer.id
    //let id = usuarioLogadoFromServer[0].id

    const updUsuario = { ...usuario, id: usuarioLogadoFromServer.id }
    // console.log(JSON.stringify(usuarioLogadoFromServer))

    console.log("usuarioLogadoId: ", usuarioLogadoFromServer)
    //alert("usuarioLogadoId: ", usuario)

    const res = await fetch(`http://localhost:8080/usuarioLogado/1`, {
      method: 'PUT',
      headers: {
          'Content-type': 'application/json',
      },
      body: JSON.stringify(updUsuario),
      // body: JSON.stringify(usuario),
    })
  }

  const estadoLoginVerificacao = () => {
    if(estadoLogin === 0) {
      return <Cadastro cadastro={cadastro}/>
    } else if(estadoLogin === 1) {
      return <Login usuarios={usuariosInfo} login={login}/>
    } else if(estadoLogin === 2) {
      // return <Logado nomeUsuario={nomeUsuario}/>

      //alert("usuariosInfo: ", usuariosInfo)

      let usuarioLog = usuariosInfo.filter(usuario => usuario?.nome.includes(nomeUsuario));
      
      //alert("usuarioLogadoId: ", usuarioLog)
      
      usuarioLog = usuarioLog[0]
      logarUsuario(usuarioLog)

      //setUsuarioLogado(usuariosInfo.filter(usuario => usuario.nome.includes(nomeUsuario)));
      //console.log(JSON.stringify(usuarioLogado[0]))
      //console.log(usuarioLogado[0].id)
      //setUsuarioLogado(usuarioLog[0])

      setTimeout(function() {
        window.location.href = '/rotina';
        //window.location.reload();
      }, 500);
    }
  }

  const cadastro = (cadastroVerificacao) => {
    if(cadastroVerificacao === 1) {
      setEstadoLogin(1)
    }
  }

  const login = (loginVerificacao, infoLogin) => {
    if(loginVerificacao === 0) {
      setEstadoLogin(0)
    } else if(loginVerificacao === 2) {
      // setNomeUsuario(infoLogin.usuarioNome)
      setNomeUsuario(infoLogin)
      setEstadoLogin(2)
    }
  }

  return (
    <BrowserRouter>
      <Routes>
        <Route path='/' element={
          <div>
            <Jogar />
            <Footer />
          </div>
        } />
        <Route path='/login' element={
          <div>
            {/* <Header usuariosInfo={usuariosInfo}/> */}
            {/* <Login /> */}
            {estadoLoginVerificacao()}
            <Footer />
          </div>
        } />
        <Route path='/rotina' element={
          <div>
            <Header usuariosInfo={usuariosInfo} usuarioLogado={usuarioLogado}/>
            <Rotina completarTarefa={completarTarefa}/>
            <Footer />
          </div>
        } />
        <Route path='/rotina/tarefas' element={
          <div>
            <Header usuariosInfo={usuariosInfo}/>
            <Rotina completarTarefa={completarTarefa}/>
            <Tarefa completarTarefa={completarTarefa}/>
            <Footer />
          </div>
        } />
        <Route path='/rotina/diarias' element={
          <div>
            <Header usuariosInfo={usuariosInfo}/>
            <Rotina completarTarefa={completarTarefa}/>
            <Diaria completarTarefa={completarTarefa}/>
            <Footer />
          </div>
        } />
        <Route path='/rotina/habitos' element={
          <div>
            <Header usuariosInfo={usuariosInfo}/>
            <Rotina completarTarefa={completarTarefa}/>
            <Habito completarTarefa={completarTarefa}/>
            <Footer />
          </div>
        } />
        <Route path='/rotina/contas' element={
          <div>
            <Header usuariosInfo={usuariosInfo}/>
            <Rotina completarTarefa={completarTarefa}/>
            <Conta completarTarefa={completarTarefa}/>
            <Footer />
          </div>
        } />
        <Route path='/sobre' element={
          <div>
            <Header usuariosInfo={usuariosInfo}/>
            <Sobre />
            <Footer />
          </div>
        } />
        <Route path='/perfil' element={
          <div>
            <Header usuariosInfo={usuariosInfo}/>
            <Perfil darkModeVerify={darkModeVerify} />
            <Footer />
          </div>
        } />
        <Route path='/login' element={
          <div>
            <Header usuariosInfo={usuariosInfo}/>
            <Sobre />
            <Footer />
          </div>
        } />
        <Route path='/loja' element={
          <div>
            <Header usuariosInfo={usuariosInfo}/>
            <Loja />
            <Footer />
          </div>
        } />
        <Route path='/inventario' element={
          <div>
            <Header usuariosInfo={usuariosInfo}/>
            <Inventario />
            <Footer />
          </div>
        } />
      </Routes>
    </BrowserRouter>
  );
}


export default App;
