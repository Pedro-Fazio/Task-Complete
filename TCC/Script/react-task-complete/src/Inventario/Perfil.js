import { Link } from 'react-router-dom'
import { useState, useEffect } from 'react'
import { FaEdit } from 'react-icons/fa'
import { AiFillCheckCircle } from 'react-icons/ai'
import './Perfil.css';

const Perfil = ({ darkModeVerify }) => {
  const [perfilInfo, setPerfilInfo] = useState([])
  const [usuarioLogado, setUsuarioLogado] = useState()
  const [isDarkMode, setIsDarkMode] = useState(false)


  /*** Requisições ao backend ***/

  useEffect(() => {
    const getPerfilInfo = async () => {
      const perfilInfoFromServer = await fetchPerfisInfo()
      const perfilLogadoFromServer = await fetchPerfilLogado()
      setUsuarioLogado(perfilLogadoFromServer)
      setPerfilInfo(perfilInfoFromServer)

      carregaInfos(perfilLogadoFromServer)
    }

    const carregaInfos = (infoLogado) => {
      //let inputNome = document.querySelector(".nome-form input");
      //let inputEmail = document.querySelector(".email-form input");

      if (infoLogado !== undefined) {
        //inputNome.value = infoLogado[0]?.nome
        //inputEmail.value = infoLogado[0]?.email
      }

      barraProgresso()
    }

    getPerfilInfo()
  }, [])

  useEffect(() => {
    const iniciaDarkMode = () => {
      if (usuarioLogado !== undefined) {
        setIsDarkMode(usuarioLogado[0].isDarkMode);
      }
    }

  iniciaDarkMode()

}, [usuarioLogado]);

  useEffect(() => {
  const handleToggleDarkMode = () => {
    
    let nomeForm = document.querySelector('.nome-form-label');
    let emailForm = document.querySelector('.email-form-label');
    let perfilTitulo = document.querySelector('.perfil-titulo');


    if(!isDarkMode) {
      // nomeForm.style.color = '#fff';
      // emailForm.style.color = '#fff';
      // perfilTitulo.style.color = '#fff';
      //nomeForm.style.setProperty('-webkit-text-stroke', '1px #fff', 'important');
    } else {
      // nomeForm.style.color = '#111';
      // emailForm.style.color = '#111';
      // perfilTitulo.style.color = '#111';
    }
  };

  handleToggleDarkMode()

  }, [isDarkMode, darkModeVerify]);

  const fetchPerfilLogado = async (id) => {
    const res = await fetch('http://localhost:8080/usuarioLogado')
    const data = await res.json()

    return data
}

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


  /*** Funcionalidades do Perfil ***/

  const onEdit = (opcao) => {
    let faEditNome = document.querySelector(".fa-edit-nome");
    let concluirEdicaoNome = document.querySelector(".concluir-edicao-nome");
    let inputNome = document.querySelector(".nome-form input");

    let faEditEmail = document.querySelector(".fa-edit-email");
    let concluirEdicaoEmail = document.querySelector(".concluir-edicao-email");
    let inputEmail = document.querySelector(".email-form input");

    if (opcao == "nome") {
      faEditNome.setAttribute("style", "display: none");
      concluirEdicaoNome.setAttribute("style", "display: block");
      inputNome.disabled = false;
    } else if (opcao == "email") {
      faEditEmail.setAttribute("style", "display: none");
      concluirEdicaoEmail.setAttribute("style", "display: block");
      inputEmail.disabled = false;
    }

    // let AiFillCheckCircle = document.createElement("AiFillCheckCircle");
    // concluirEdicao.appendChild(AiFillCheckCircle);
    // let FaFacebook = document.createElement("FaFacebook");

  }

  const onConcluir = (opcao) => {
    let faEditNome = document.querySelector(".fa-edit-nome");
    let concluirEdicaoNome = document.querySelector(".concluir-edicao-nome");
    let inputNome = document.querySelector(".nome-form input");

    let faEditEmail = document.querySelector(".fa-edit-email");
    let concluirEdicaoEmail = document.querySelector(".concluir-edicao-email");
    let inputEmail = document.querySelector(".email-form input");

    if (opcao == "nome") {
      faEditNome.setAttribute("style", "display: block");
      concluirEdicaoNome.setAttribute("style", "display: none");
      inputNome.disabled = true;
      adicionarNoServidor(inputNome.value, "nome")

    } else if (opcao == "email") {
      faEditEmail.setAttribute("style", "display: block");
      concluirEdicaoEmail.setAttribute("style", "display: none");
      inputEmail.disabled = true;
      adicionarNoServidor(inputEmail.value, "email")
    }
  }

  const adicionarNoServidor = async (infos, opcao) => {
    let opcaoEscolhida = opcao;
    let inputNome = document.querySelector(".nome-form input");

    let usuarioLog = perfilInfo.filter(usuario => usuario?.email.includes(usuarioLogado[0].email));
    usuarioLog = usuarioLog[0]
    let id = usuarioLog.id;
    const usuario = await fetchPerfilInfo(id)
    let updUsuario = usuario

    if(opcaoEscolhida == "email") {
      updUsuario = { ...usuario, email: infos }
    } else if(opcaoEscolhida == "nome") {
      updUsuario = { ...usuario, nome: infos }
    }
    // console.log("CONSOLE: ", JSON.stringify(usuarioLog))

    const res = await fetch(`http://localhost:8080/usuarios/${usuarioLog.id}`, {
        method: 'PUT',
        headers: {
            'Content-type': 'application/json',
        },
        body: JSON.stringify(updUsuario),
    })

    const data = await res.json()

    // setPerfilInfo([...perfilInfo, dados])
    setPerfilInfo(perfilInfo)
  }

  const barraProgresso = () => {
    let progresso = document.querySelector(".barra-nivel div");
    if(usuarioLogado && usuarioLogado[0]) {
      progresso?.setAttribute("style", `width: ${usuarioLogado[0]?.xp}%`);
    } else {
      progresso?.setAttribute("style", `width: 0%`);
    }
    // progresso?.setAttribute("style", `width: ${perfilInfo[0]?.xp}%`);
    // progresso.setAttribute("style", `width: 60%`);
  }

  return (
    <div className="container-perfil">
      <section className="perfil-info">
        <p className="perfil-titulo"> Informações </p>
        <form className="form-info">
          <span className='nome-form'>
            <label for="nome" className='nome-form-label'>Nome:</label>
            <input type="text" id="nome" name="nome" 
            disabled required />
            <FaEdit className="fa-edit-nome"
              size={30}
              // onClick={() => barraProgresso()}
              // onClick={() => onEditNome()}
              onClick={() => onEdit("nome")}
            />
            <AiFillCheckCircle className="concluir-edicao-nome"
              size={30}
              onClick={() => onConcluir("nome")}
            />
          </span>
          <span className='email-form'>
            <label for="email" className='email-form-label'>Email:</label>
            <input type="email" id="email" name="email" 
            /*value={perfilInfo[0].email}*/ disabled required />
            <FaEdit className="fa-edit-email"
              size={30}
              onClick={() => onEdit("email")}
            />
            <AiFillCheckCircle className="concluir-edicao-email"
              size={30}
              onClick={() => onConcluir("email")}
            />
          </span>
        </form>
      </section>

      <section className="personagem">
        <img className="img-perfil" src="https://i.imgur.com/SDC34Cp.png" alt="visual do personagem"></img>
        {/* <p className="texto-personagem"> Personalizar Personagem </p> */}
        {/* <Link to="/inventario"> <a className="texto-personagem" href="#" className="active">Personalizar Personagem </a> </Link> */}
        <Link to="/inventario"> <a className="texto-personagem" href="#">Personalizar Personagem </a> </Link>
      </section>
      <section className="nivel-perfil">
        {usuarioLogado && usuarioLogado[0] && <p className='nivel-numero'>{usuarioLogado[0].nivel}</p>}
        {/* <p> Nível {usuarioLogado[0]?.nivel} </p> */}
        {/* <p> Nível {perfilInfo[0]?.nivel} </p> */}
        <div className="barra-nivel" onLoad={barraProgresso()}>
          <div></div>
        </div>
      </section>
    </div>
  )
}

export default Perfil
