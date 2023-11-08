//import LojaCards from './LojaCards'
import './Inventario.css';
import { useState, useEffect } from 'react'

const InventarioItem = ({ equiparItem, item }) => {
    return (
        <div className="item-inventario">
            <p className="nome-item"> {item.nome} </p>
            <p className={item.equipado == 1 ? 
            "equipado" : "equipar"} onClick={() => equiparItem(item.id)}> {item.equipado == 1 ? 
            "Equipado" : "Equipar"} </p>
            {/* <p className="equipado" onClick={() => equiparItem()}> </p> */}
        </div>
    )
}

export default InventarioItem