import { useState, useEffect } from 'react'
import { Link } from 'react-router-dom'
import { FiCheckCircle } from 'react-icons/fi'
import { BiCoin } from 'react-icons/bi'
import { FaCoins } from 'react-icons/fa'
import { ImCoinDollar } from 'react-icons/im'
import DarkModeToggle from 'react-dark-mode-toggle';
import './Header.css';

const Header = ({ atualizaDarkMode }) => {
    const [usuarioLogado, setUsuarioLogado] = useState([])
    const [isDarkMode, setIsDarkMode] = useState(false)
    const [isBigSize, setIsBigSize] = useState(false)

    

    useEffect(() => {
        const getPerfilInfo = async () => {
        const perfil = await fetchPerfilLogado();
        setUsuarioLogado(perfil[0]);
    }
    
    getPerfilInfo();
}, []);

useEffect(() => {
    const saveIsDarkMode = async () => {
        try {
        let tempUsuarioLogado = usuarioLogado
        tempUsuarioLogado.isDarkMode = isDarkMode

        const res = await fetch(`http://localhost:8080/usuarioLogado/1`, {
            method: 'PUT',
            headers: {
            'Content-type': 'application/json',
            },
            body: JSON.stringify(tempUsuarioLogado),
        });
    
        // Trata a resposta do fetch, se necessário
        } catch (error) {
        console.error("Erro ao salvar o modo escuro:", error);
        }
    }

    if (usuarioLogado.id) {
        saveIsDarkMode();
        //atualizaDarkMode(isDarkMode);
    }
}, [usuarioLogado, isDarkMode]);

useEffect(() => {
    const saveIsBigSize = async () => {
        try {
        let tempUsuarioLogado = usuarioLogado
        tempUsuarioLogado.isBigSize = isBigSize

        const res = await fetch(`http://localhost:8080/usuarioLogado/1`, {
            method: 'PUT',
            headers: {
            'Content-type': 'application/json',
            },
            body: JSON.stringify(tempUsuarioLogado),
        });
    
        // Trata a resposta do fetch, se necessário
        } catch (error) {
        console.error("Erro ao salvar o modo escuro:", error);
        }
    }

    if (usuarioLogado.id) {
        saveIsBigSize();
    }
}, [usuarioLogado, isBigSize]);

    const fetchPerfilLogado = async () => {
        const res = await fetch('http://localhost:8080/usuarioLogado')
        const data = await res.json()
    
        return data
    }

    const handleToggleDarkMode = () => {
        setIsDarkMode(!isDarkMode);
        
        /*** QuerySelector ***/
        let body = document.querySelector('body');
        let header = document.querySelector('.header');
        let inventarioContainer = document.querySelector('.inventario-container');
        let nomeForm = document.querySelector('.nome-form-label');
        let emailForm = document.querySelector('.email-form-label');
        let perfilTitulo = document.querySelector('.perfil-titulo');
        let titleLoja = document.querySelector('.title-loja');
        let textoPersonagem = document.querySelector('.texto-personagem');
        let nivelNumero = document.querySelector('.nivel-numero');
        let faEditNome = document.querySelector('.fa-edit-nome');
        let faEditEmail = document.querySelector('.fa-edit-email');
        let rotinaHeaderTitle = document.querySelector('.rotina-header h1');
        let tarefasRotina = document.querySelector('.tarefas-rotina');
        let btnRotina = document.querySelector('.btn-rotina');

        if(!isDarkMode) {
            /*** Header ***/
            header.style.borderBottom = '1px solid #fff';
            body.style.backgroundColor = '#111';
            header.style.backgroundColor = '#111';

            /*** Inventario ***/
            const bgColorInventarioContaienr = inventarioContainer ? '#111' : null;
            if (inventarioContainer) {
                inventarioContainer.style.backgroundColor = bgColorInventarioContaienr;
            }

            /*** Perfil ***/
            const colorNomeForm = nomeForm ? '#fff' : null;
            if (nomeForm) {
                nomeForm.style.color = colorNomeForm;
            }

            const colorEmailForm = nomeForm ? '#fff' : null;
            if (emailForm) {
                emailForm.style.color = colorEmailForm;
            }

            const colorPerfilTitulo = nomeForm ? '#fff' : null;
            if (perfilTitulo) {
                perfilTitulo.style.color = colorPerfilTitulo;
            }

            const colorTextoPersonagem = textoPersonagem ? '#fff' : null;
            if (textoPersonagem) {
                textoPersonagem.style.color = colorTextoPersonagem;
            }

            const colorNivelNumero = nivelNumero ? '#fff' : null;
            if (nivelNumero) {
                nivelNumero.style.color = colorNivelNumero;
            }

            const colorFaEditNome = faEditNome ? '#fff' : null;
            if (faEditNome) {
                faEditNome.style.color = colorFaEditNome;
            }

            const colorFaEditEmail = faEditEmail ? '#fff' : null;
            if (faEditEmail) {
                faEditEmail.style.color = colorFaEditEmail;
            }

        /*** Rotina ***/
        const colorRotinaHeaderTitle = rotinaHeaderTitle ? '#fff' : null;
        if (rotinaHeaderTitle) {
            rotinaHeaderTitle.style.color = colorRotinaHeaderTitle;
        }

        // const colorBtnRotina = btnRotina ? '#111' : null;
        // const bgColorBtnRotina = btnRotina ? '#fff' : null;
        // if (btnRotina) {
        //     btnRotina.style.color = colorBtnRotina;
        //     btnRotina.style.backgroundColor = bgColorBtnRotina;
        // }

        const colorRotinaTextTitle = tarefasRotina ? '#fff' : null;
        if (tarefasRotina) {
            tarefasRotina.style.color = colorRotinaTextTitle;
        }

        /*** Loja ***/
        const colorTitleLoja = titleLoja ? '#fff' : null;
        if (titleLoja) {
            titleLoja.style.color = colorTitleLoja;
        }
        } else {
            /*** Header ***/
            header.style.border = '';
            body.style.backgroundColor = '#FEF5F5';
            header.style.backgroundColor = '#005A75';

            /*** Inventario ***/
            const backgroundColor = inventarioContainer ? '#fff' : null;
            if (inventarioContainer) {
                inventarioContainer.style.backgroundColor = backgroundColor;
            }

            /*** Perfil ***/
            const colorNomeForm = nomeForm ? '#111' : null;
            if (nomeForm) {
                nomeForm.style.color = colorNomeForm;
            }

            const colorEmailForm = nomeForm ? '#111' : null;
            if (emailForm) {
                emailForm.style.color = colorEmailForm;
            }

            const colorPerfilTitulo = nomeForm ? '#111' : null;
            if (perfilTitulo) {
                perfilTitulo.style.color = colorPerfilTitulo;
            }

            const colorTextoPersonagem = textoPersonagem ? '#111' : null;
            if (textoPersonagem) {
                textoPersonagem.style.color = colorTextoPersonagem;
            }

            const colorNivelNumero = nivelNumero ? '#111' : null;
            if (nivelNumero) {
                nivelNumero.style.color = colorNivelNumero;
            }

            const colorFaEditNome = faEditNome ? '#111' : null;
            if (faEditNome) {
                faEditNome.style.color = colorFaEditNome;
            }

            const colorFaEditEmail = faEditEmail ? '#111' : null;
            if (faEditEmail) {
                faEditEmail.style.color = colorFaEditEmail;
            }

            /*** Rotina ***/
            const colorRotinaHeaderTitle = rotinaHeaderTitle ? '#000' : null;
            if (rotinaHeaderTitle) {
                rotinaHeaderTitle.style.color = colorRotinaHeaderTitle;
            }

            // const colorBtnRotina = btnRotina ? '#005A75' : null;
            // const bgColorBtnRotina = btnRotina ? '#fff' : null;
            // if (btnRotina) {
            //     btnRotina.style.color = colorBtnRotina;
            //     btnRotina.style.backgroundColor = bgColorBtnRotina;
            // }

            const colorRotinaTextTitle = tarefasRotina ? '#000' : null;
            if (tarefasRotina) {
                tarefasRotina.style.color = colorRotinaTextTitle;
            }

            /*** Loja ***/
            const colorTitleLoja = titleLoja ? '#111' : null;
            if (titleLoja) {
                titleLoja.style.color = colorTitleLoja;
            }  
        }
    };

    const handleToggleBigSize = () => {
        setIsBigSize(!isBigSize);
        
        /*** QuerySelector ***/

        /*** Header ***/
        let body = document.querySelector('body');
        let linkHeaderRotina = document.querySelector('#link-header-rotina');
        let linkHeaderPerfil = document.querySelector('#link-header-perfil');
        let linkHeaderLoja = document.querySelector('#link-header-loja');
        let linkHeaderSobre= document.querySelector('#link-header-sobre');
        /*** Inventario ***/
        let tituloItensAdquiridos = document.querySelector('.titulo-itens-adquiridos');
        /*** Perfil ***/
        let nomeForm = document.querySelector('.nome-form-label');
        let emailForm = document.querySelector('.email-form-label');
        let perfilTitulo = document.querySelector('.perfil-titulo');
        let textoPersonagem = document.querySelector('.texto-personagem');
        let nivelNumero = document.querySelector('.nivel-numero');
        let faEditNome = document.querySelector('.fa-edit-nome');
        let faEditEmail = document.querySelector('.fa-edit-email');
        /*** Rotina ***/
        let rotinaHeaderTitle = document.querySelector('.rotina-header h1');
        let btnRotinaTarefas = document.querySelector('.btn-rotina-tarefas');
        let btnRotinaDiarias = document.querySelector('.btn-rotina-diarias');
        let btnRotinaHabitos = document.querySelector('.btn-rotina-habitos');
        let btnRotinaContas = document.querySelector('.btn-rotina-contas');
        let tarefasRotina = document.querySelector('.tarefas-rotina');
        let taskIndividual = document.querySelector('.tasks-area div h3');
        let taskIndividualConta = document.querySelector('.tasks-area div h3 p');
        /*** Loja ***/
        let titleLoja = document.querySelector('.title-loja');
        let card = document.querySelector('.card p');


        if(!isBigSize) {
            /*** Header ***/
            const fsLinkHeaderRotina = linkHeaderRotina ? '35px' : null;
            if (linkHeaderRotina) {
                linkHeaderRotina.style.fontSize = fsLinkHeaderRotina;
            }

            const fsLinkHeaderPerfil = linkHeaderPerfil ? '35px' : null;
            if (linkHeaderPerfil) {
                linkHeaderPerfil.style.fontSize = fsLinkHeaderPerfil;
            }

            const fsLinkHeaderLoja = linkHeaderLoja ? '35px' : null;
            if (linkHeaderLoja) {
                linkHeaderLoja.style.fontSize = fsLinkHeaderLoja;
            }

            const fsLinkHeaderSobre = linkHeaderSobre ? '35px' : null;
            if (linkHeaderSobre) {
                linkHeaderSobre.style.fontSize = fsLinkHeaderSobre;
            }
            
            /*** Inventario ***/
            const fsTituloItensAdquiridos = tituloItensAdquiridos ? '40px' : null;
            if (tituloItensAdquiridos) {
                tituloItensAdquiridos.style.fontSize = fsTituloItensAdquiridos;
            }

            /*** Perfil ***/
            const fsNomeForm = nomeForm ? '35px' : null;
            if (nomeForm) {
                nomeForm.style.fontSize = fsNomeForm;
            }

            const fsEmailForm = nomeForm ? '35px' : null;
            if (emailForm) {
                emailForm.style.fontSize = fsEmailForm;
            }

            const fsPerfilTitulo = nomeForm ? '60px' : null;
            if (perfilTitulo) {
                perfilTitulo.style.fontSize = fsPerfilTitulo;
            }

            const fsTextoPersonagem = textoPersonagem ? '35px' : null;
            if (textoPersonagem) {
                textoPersonagem.style.fontSize = fsTextoPersonagem;
            }

            const fsNivelNumero = nivelNumero ? '35px' : null;
            if (nivelNumero) {
                nivelNumero.style.fontSize = fsNivelNumero;
            }

            const fsFaEditNome = faEditNome ? 70 : null;
            if (faEditNome) {
                faEditNome.style.size = fsFaEditNome;
            }

            const fsFaEditEmail = faEditEmail ? 70 : null;
            if (faEditEmail) {
                faEditEmail.style.size = fsFaEditEmail;
            }

            /*** Rotina ***/
            const fsRotinaHeaderTitle = rotinaHeaderTitle ? '60px' : null;
            if (rotinaHeaderTitle) {
                rotinaHeaderTitle.style.color = fsRotinaHeaderTitle;
            }

            const fsBtnRotina = btnRotinaTarefas ? '45px' : null;
            if (btnRotinaTarefas) {
                btnRotinaTarefas.style.fontSize = fsBtnRotina;
                btnRotinaDiarias.style.fontSize = fsBtnRotina;
                btnRotinaHabitos.style.fontSize = fsBtnRotina;
                btnRotinaContas.style.fontSize = fsBtnRotina;
            }

            const fsRotinaTextTitle = tarefasRotina ? '60px' : null;
            if (tarefasRotina) {
                tarefasRotina.style.fontSize = fsRotinaTextTitle;
            }

            const fsTaskIndividual = taskIndividual ? '35px' : null;
            if (taskIndividual) {
                taskIndividual.style.fontSize = fsTaskIndividual;
            }

            const fsTaskIndividualConta = taskIndividualConta ? '35px' : null;
            if (taskIndividualConta) {
                taskIndividualConta.style.fontSize = fsTaskIndividualConta;
            }

            /*** Loja ***/
            const fsTitleLoja = titleLoja ? '60px' : null;
            if (titleLoja) {
                titleLoja.style.fontSize = fsTitleLoja;
            }
        
        } else {
            /*** Header ***/
            const fsLinkHeaderRotina = linkHeaderRotina ? '20px' : null;
            if (linkHeaderRotina) {
                linkHeaderRotina.style.fontSize = fsLinkHeaderRotina;
            }

            const fsLinkHeaderPerfil = linkHeaderPerfil ? '20px' : null;
            if (linkHeaderPerfil) {
                linkHeaderPerfil.style.fontSize = fsLinkHeaderPerfil;
            }

            const fsLinkHeaderLoja = linkHeaderLoja ? '20px' : null;
            if (linkHeaderLoja) {
                linkHeaderLoja.style.fontSize = fsLinkHeaderLoja;
            }

            const fsLinkHeaderSobre = linkHeaderSobre ? '20px' : null;
            if (linkHeaderSobre) {
                linkHeaderSobre.style.fontSize = fsLinkHeaderSobre;
            }
            
            /*** Inventario ***/
            const fsTituloItensAdquiridos = tituloItensAdquiridos ? '20px' : null;
            if (tituloItensAdquiridos) {
                tituloItensAdquiridos.style.fontSize = fsTituloItensAdquiridos;
            }
            
            /*** Perfil ***/
            const fsNomeForm = nomeForm ? '25px' : null;
            if (nomeForm) {
                nomeForm.style.fontSize = fsNomeForm;
            }

            const fsEmailForm = nomeForm ? '25px' : null;
            if (emailForm) {
                emailForm.style.fontSize = fsEmailForm;
            }

            const fsPerfilTitulo = nomeForm ? '45px' : null;
            if (perfilTitulo) {
                perfilTitulo.style.fontSize = fsPerfilTitulo;
            }

            const fsTextoPersonagem = textoPersonagem ? '25px' : null;
            if (textoPersonagem) {
                textoPersonagem.style.fontSize = fsTextoPersonagem;
            }

            const fsNivelNumero = nivelNumero ? '40px' : null;
            if (nivelNumero) {
                nivelNumero.style.fontSize = fsNivelNumero;
            }

            const fsFaEditNome = faEditNome ? 30 : null;
            if (faEditNome) {
                faEditNome.style.size = fsFaEditNome;
            }

            const fsFaEditEmail = faEditEmail ? 30 : null;
            if (faEditEmail) {
                faEditEmail.style.size = fsFaEditEmail;
            }


            /*** Rotina ***/
            const fsRotinaHeaderTitle = rotinaHeaderTitle ? '45px' : null;
            if (rotinaHeaderTitle) {
                rotinaHeaderTitle.style.color = fsRotinaHeaderTitle;
            }

            const fsBtnRotina = btnRotinaTarefas ? '45px' : null;
            if (btnRotinaTarefas) {
                btnRotinaTarefas.style.fontSize = fsBtnRotina;
                btnRotinaDiarias.style.fontSize = fsBtnRotina;
                btnRotinaHabitos.style.fontSize = fsBtnRotina;
                btnRotinaContas.style.fontSize = fsBtnRotina;
            }

            const fsRotinaTextTitle = tarefasRotina ? '45px' : null;
            if (tarefasRotina) {
                tarefasRotina.style.fontSize = fsRotinaTextTitle;
            }

            const fsTaskIndividual = taskIndividual ? '25px' : null;
            if (taskIndividual) {
                taskIndividual.style.fontSize = fsTaskIndividual;
            }

            const fsTaskIndividualConta = taskIndividualConta ? '25px' : null;
            if (taskIndividualConta) {
                taskIndividualConta.style.fontSize = fsTaskIndividualConta;
            }


            /*** Loja ***/
            const fsTitleLoja = titleLoja ? '60px' : null;
            if (titleLoja) {
                titleLoja.style.fontSize = fsTitleLoja;
            }
        }
        
    }

    const informacoesUsuario = () => {
        let usuarioFetch = fetchPerfilLogado()
        setUsuarioLogado(usuarioFetch)
    }

    return (
        <div id="header_body">
            {/* <div className={isDarkMode ? ".dark-mode-header" : "header"} id="header"> */}
            <div className="header" id="header">
                <div className="logo_header">
                    <Link to="/rotina">
                        <img className="img-header" src="https://i.imgur.com/D50NWnq.png" alt="logotipo do task complete"></img>
                    </Link>
                </div>
                <div className="navigation_header" id="navigation_header">

                    <img className='fonte-maior' src="https://i.imgur.com/HmoWDL0.png" onClick={handleToggleBigSize}
                    alt="botão de aumentar a fonte"/>
                    
                    <DarkModeToggle
                    onChange={handleToggleDarkMode}
                    checked={isDarkMode}
                    size={80}
                    />

                    <div className='nome-header' onLoad={informacoesUsuario}> <span> Olá </span> <span> {usuarioLogado[0]?.nome} </span> </div>
                    <Link to="/rotina"> <a href="#" className="link-header" 
                    id='link-header-rotina'> Rotina </a> </Link>
                    <Link to="/perfil"> <a href="#" className="link-header"
                    id='link-header-perfil'> Perfil </a> </Link>
                    <Link to="/loja"> <a href="#" className="link-header"
                    id='link-header-loja'> Loja </a> </Link>
                    <Link to="/sobre"> <a href="#" className="link-header"
                    id='link-header-sobre'> Sobre </a> </Link>
                    <div className='dinheiro'>
                        {/* <ImCoinDollar className='dinheiro-icone' size={30}/> */}
                        <img className="dinheiro-icone" src="https://i.imgur.com/vi1Apef.png" alt="icone do dinheiro" />
                        {/* <p className='dinheiro-qnt'> {usuariosInfo[0]?.dinheiro} </p> */}
                        <p className='dinheiro-qnt'> {usuarioLogado[0]?.dinheiro} </p>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default Header
