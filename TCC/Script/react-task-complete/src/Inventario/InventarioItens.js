//import LojaCards from './LojaCards'
import { useState, useEffect } from 'react'
import InventarioItem from './InventarioItem'

const InventarioItens = ({ equiparItem, itens}) => {
    return (
        <>
            {itens.map((item, index) => (
                <InventarioItem
                key={index}
                item={item}
                equiparItem={equiparItem}
                />
            ))}
        </>
    )
}

export default InventarioItens