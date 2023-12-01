import { useState, useEffect } from 'react'
import { Link } from 'react-router-dom'
import { FiCheckCircle } from 'react-icons/fi'
import { BiCoin } from 'react-icons/bi'
import { FaCoins } from 'react-icons/fa'
import { ImCoinDollar } from 'react-icons/im'
import './Header.css';

const Header = ({ usuariosInfo, usuarioLogado }) => {
    const [usuario, setUsuario] = useState('')

    useEffect(() => {
        const getPerfilInfo = async () => {
        const perfilLogadoFromServer = await fetchPerfilLogado()
        setUsuario(perfilLogadoFromServer)
        }
    
        getPerfilInfo()
    }, [])

    const fetchPerfilLogado = async (id) => {
        const res = await fetch('http://localhost:8080/usuarioLogado')
        const data = await res.json()
    
        return data
    }

    const informacoesUsuario = () => {
        let usuarioFetch = fetchPerfilLogado()
        setUsuario(usuarioFetch)
    }

    return (
        <div id="header_body">
            <div className="header" id="header">
                {/* <button onClick="toggleSidebar()" className="btn_icon_header">
            <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" className="bi bi-list" viewBox="0 0 16 16">
                <path fill-rule="evenodd" d="M2.5 12a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5z"/>
            </svg>
          </button> */}
                <div className="logo_header">
                    <Link to="/rotina">
                        <img className="img-header" src="https://i.imgur.com/D50NWnq.png" alt="logotipo do task complete"></img>
                    </Link>
                </div>
                <div className="navigation_header" id="navigation_header">
                    {/* <button onClick="toggleSidebar()" className="btn_icon_header">
                        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" className="bi bi-x" viewBox="0 0 16 16">
                            <path d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z" />
                        </svg>
                    </button> */}
                    <img className='simbolo-daltonico' src="https://i.imgur.com/IFwOkXz.png" alt="simbolo daltonico chamado colorADD"/>
                    <div className='nome-header' onLoad={informacoesUsuario}> <span> Olá </span> <span> {usuario[0]?.nome} </span> </div>
                    <Link to="/rotina"> <a href="#" className="link-header">Rotina</a> </Link>
                    <Link to="/perfil"> <a href="#" className="link-header">Perfil</a> </Link>
                    <Link to="/loja"> <a href="#" className="link-header">Loja</a> </Link>
                    <Link to="/sobre"> <a href="#" className="link-header">Sobre</a> </Link>
                    <div className='dinheiro'>
                        {/* <ImCoinDollar className='dinheiro-icone' size={30}/> */}
                        <img className="dinheiro-icone" src="https://i.imgur.com/vi1Apef.png" alt="icone do dinheiro" />
                        {/* <p className='dinheiro-qnt'> {usuariosInfo[0]?.dinheiro} </p> */}
                        <p className='dinheiro-qnt'> {usuario[0]?.dinheiro} </p>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default Header
