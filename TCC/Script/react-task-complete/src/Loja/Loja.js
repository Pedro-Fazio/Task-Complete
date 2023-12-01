import { Link } from 'react-router-dom'
import { FaTimes } from 'react-icons/fa'
import { useState, useEffect } from 'react'
import LojaCards from './LojaCards'
import './Loja.css';
import axios from 'axios';

const Loja = ({ }) => {
    const [itens, setItens] = useState([])
    const [usuariosInfo, setUsuariosInfo] = useState([])
    const [usuarioLogado, setUsuarioLogado] = useState()

    /*** Requisições ao backend ***/
    useEffect(() => {
        const getItens = async () => {
            const itensFromServer = await fetchItens()
            setItens(itensFromServer)
        }

        const getPerfilInfo = async () => {      
            setUsuarioLogado(await fetchPerfilLogado())
            setUsuariosInfo(await fetchPerfisInfo())
        }

        getItens()
        getPerfilInfo()
    }, [])


    const fetchItens = async () => {
        const res = await fetch('http://localhost:8080/loja')
        const data = await res.json()

        return data
    }

    const fetchItem = async (id) => {
        const res = await fetch(`http://localhost:8080/loja/${id}`)
        const data = await res.json()

        return data
    }

    const fetchPerfilLogado = async (id) => {
        const res = await fetch('http://localhost:8080/usuarioLogado')
        const data = await res.json()
    
        return data
    }

    const fetchPerfilInfo = async (id) => {
        const res = await fetch(`http://localhost:8080/usuarios/${id}`)
        const data = await res.json()
    
        return data
    }

    const fetchPerfisInfo = async () => {
        const res = await fetch('http://localhost:8080/usuarios')
        const data = await res.json()
    
        return data
    }

    const adicionarInventario = async (id) => {
        const item = await fetchItem(id);

        console.log("é multiplayer antes", item)

        //const { id, ...restoDoItem } = item;
        delete item.id;
        const updItem = { ...item, equipado: false }

        //console.log("é multiplayer", updItem)

        const res = await fetch('http://localhost:8080/inventario', {
            method: 'POST',
            headers: {
                'Content-type': 'application/json',
            },
            body: JSON.stringify(updItem),
        })

        const data = await res.json()
    }

    const diminuirDinheiro = async (id) => {
        let usuarioLog = usuariosInfo.filter(usuario => usuario?.email.includes(usuarioLogado[0].email));
        usuarioLog = usuarioLog[0]
        let idPerfil = usuarioLog.id;

        const usuario = await fetchPerfilInfo(idPerfil)
        const item = await fetchItem(id);
        let dinheiroAtualizado = parseFloat(usuario.dinheiro) - parseFloat(item.preco)

        dinheiroAtualizado = dinheiroAtualizado.toString()
        const updUsuario = { ...usuario, dinheiro: dinheiroAtualizado }
        // dinheiroAtualizado = "" + dinheiroAtualizado

        // console.log("DINHEIRO: ", dinheiroAtualizado)
        // console.log("DINHEIRO: ", JSON.stringify(updUsuario))

        const res = await fetch(`http://localhost:8080/usuarios/${idPerfil}`, {
            method: 'PUT',
            headers: {
                'Content-type': 'application/json',
            },
            body: JSON.stringify(updUsuario),
        })

        const data = await res.json()

        //console.log("DINHEIRO: ", JSON.stringify(data))
        //removerItemLoja(id);
    }

    const removerItemLoja = async (id) => {
            const res = await fetch(`http://localhost:8080/loja/${id}`, {
                method: 'DELETE',
            })
    
            // We should control the response status to decide if we will change the state or not.
            res.status === 200
                ? setItens(itens.filter((item) => item.id !== id))
                : alert('Erro ao deletar essa conta')
    }

    /*** Funcionalidades da loja ***/

    const adicionarCarrinho = (id) => {
        let carrinhoPopUp = document.querySelector(".adicionado-carrinho-pop-up");
        let tempoParaInvisivel = 3000

        carrinhoPopUp.setAttribute("style", "display: flex");

        setTimeout(tornarInvisivel, tempoParaInvisivel);

        adicionarInventario(id);
        diminuirDinheiro(id);
        removerItemLoja(id);

        setTimeout(function () {
            window.location.reload();
        }, 500);

        return;
    }

    function tornarInvisivel() {
        let carrinhoPopUp = document.querySelector(".adicionado-carrinho-pop-up");

        carrinhoPopUp.style.display = "none";
        console.log("entrou");
    }

    return (
        <div className='container-loja'>
            <p className="title-loja" style={{fontSize: '60px'}}> Loja </p>
            {/* <p className="adicionado-carrinho-pop-up"> Item adicionado ao Carrinho </p> */}
            <p className="adicionado-carrinho-pop-up"> Item comprado com sucesso </p>
            {itens.length > 0 ?
                <div className="cards">
                    <LojaCards /*className="cards"*/ itens={itens} adicionarCarrinho={adicionarCarrinho} />
                </div>
                : 'Não há itens restantes na loja'}
        </div>
    )
}

export default Loja
