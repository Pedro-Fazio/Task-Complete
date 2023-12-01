import { useState, useEffect } from 'react'
import { Link } from 'react-router-dom'
import { FaEdit } from 'react-icons/fa'
import './Inventario.css';
import InventarioItens from './InventarioItens'

const Inventario = ({ }) => {
  const [itens, setItens] = useState([])
  const [item, setItem] = useState([])
  const [usuarioLogado, setUsuarioLogado] = useState()

  /*** Requisições ao backend ***/
  useEffect(() => {
    const getItens = async () => {
      const itensFromServer = await fetchItens()
      setItens(itensFromServer)
    }

    const getPerfilInfo = async () => {
      setUsuarioLogado(await fetchPerfilLogado())
    }

    getItens()
    getPerfilInfo()
  }, [])

  useEffect(() => {
    // Este código será executado após a renderização
    // e sempre que `variavel` for alterado
    enviarParaBackend(item, item.id);
  }, [item]);

  // useEffect(() => {
  //   setItem({...updItem, equipado: !updItem.equipado})
  // }, [updItem]);


  const fetchPerfilLogado = async () => {
    const res = await fetch('http://localhost:8080/usuarioLogado')
    const data = await res.json()

    return data
}

  const fetchItens = async () => {
    const res = await fetch('http://localhost:8080/inventario')
    const data = await res.json()

    return data
  }

  const fetchItem = async (id) => {
    const res = await fetch(`http://localhost:8080/inventario/${id}`)
    const data = await res.json()

    return data
}

  const equiparItem = async (id) => {
    let updItem = await fetchItem(id);
    setItem({...updItem, equipado: !updItem.equipado})
    //mudarStatusEquipado()

    setTimeout(function() {
      window.location.reload();
    }, 500);
  }

  const enviarParaBackend = async (item, id) => {
    const res = await fetch(`http://localhost:8080/inventario/${id}`, {
      method: 'PUT',
      headers: {
          'Content-type': 'application/json',
      },
      body: JSON.stringify(item),
    })

    const data = await res.json()
  }

  // const mudarStatusEquipado = () => {
  //   let equipar = document.querySelector(".equipar");
  //   let equipado = document.querySelector(".equipado");

  //   equipar.className = "equipado";
  //   equipado.className = "equipar";
  // }

  return (
    <div className="inventario-container">
      <section className='itens-adquiridos'>
        <div className="itens-box">
          <p className="titulo-itens-adquiridos"> Itens adquiridos </p>
          <InventarioItens className="item-inventario" equiparItem={equiparItem} itens={itens}></InventarioItens>
        </div>
      </section>
      <section className="personagem">
        <img className="img-inventario" src="https://i.imgur.com/SDC34Cp.png" alt="logotipo do task complete"></img>
        <p className="aplicar-mudancas"> Aplicar Mudanças </p>
      </section>
    </div>
  )
}

export default Inventario
