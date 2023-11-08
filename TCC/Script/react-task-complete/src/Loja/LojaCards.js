import { AiFillCheckCircle } from 'react-icons/ai'
import LojaCard from './LojaCard'
import './Loja.css';

const LojaCards = ({ itens, adicionarCarrinho }) => {
    return (
        <>
            {itens.map((item, index) => (
                <LojaCard
                    key={index}
                    item={item}
                    adicionarCarrinho={adicionarCarrinho}
                />
            ))}
        </>
    )
}

export default LojaCards
