import { useState, useEffect } from 'react'
import { Link } from 'react-router-dom'
import { FaEdit } from 'react-icons/fa'
import './Inventario.css';
import InventarioItens from './InventarioItens'

const Inventario = ({ }) => {
  const [itens, setItens] = useState([])

  /*** Requisições ao backend ***/
  useEffect(() => {
    const getItens = async () => {
      const itensFromServer = await fetchItens()
      setItens(itensFromServer)
    }

    getItens()
  }, [])

  const fetchItens = async () => {
    const res = await fetch('http://localhost:5000/inventario')
    const data = await res.json()

    //console.log("data:::: ", JSON.stringify(data))

    return data
  }

  const fetchItem = async (id) => {
    const res = await fetch(`http://localhost:5000/inventario/${id}`)
    const data = await res.json()

    return data
}

  const equiparItem = async (id) => {
    const updItem = await fetchItem(id);
    //mudarStatusEquipado()

    //console.log("updItem: ", updItem);

    updItem.equipado = !updItem.equipado;

    const res = await fetch(`http://localhost:5000/inventario/${id}`, {
      method: 'PUT',
      headers: {
          'Content-type': 'application/json',
      },
      body: JSON.stringify(updItem),
    })

    const data = await res.json()

    setTimeout(function() {
      window.location.reload();
    }, 500);
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
          {/*<div className="item-inventario">
            <p className="nome-item"> Cabelo 1 </p>
            <p className="equipado" onClick={() => equiparItem()}> Equipado </p>
          </div>
          <div className="item-inventario">
            <p className="nome-item"> Cabelo 2 </p>
            <p className="equipar" onClick={() => equiparItem()}> Equipar </p>
          </div>
          <div className="item-inventario">
            <p className="nome-item"> Calça 1 </p>
            <p className="equipar" onClick={() => equiparItem()}> Equipar </p>
          </div>
          <div className="item-inventario">
            <p className="nome-item"> Calça 2 </p>
            <p className="equipado" onClick={() => equiparItem()}> Equipado </p>
          </div>
          <div className="item-inventario">
            <p className="nome-item"> Camisa 1 </p>
            <p className="equipado" onClick={() => equiparItem()}> Equipado </p>
          </div>
          <div className="item-inventario">
            <p className="nome-item"> Camisa 2 </p>
            <p className="equipar" onClick={() => equiparItem()}> Equipar </p>
          </div> */}
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
