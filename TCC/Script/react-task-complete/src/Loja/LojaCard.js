import { AiFillCheckCircle } from 'react-icons/ai'
import './Loja.css';

const LojaCard = ({ item, adicionarCarrinho }) => {
    return (
        <div className='card'>
            <img className="img-loja" src={item.imagem} alt="imagem do item da loja (podendo ser uma camisa, calça ou chapéu)"></img>
            <p className='card-title' style={{fontSize: '30px'}}>{item.nome}</p>
            <p className='card-title' style={{fontSize: '30px'}}>{item.preco}</p>
            <a className='comprar' /*href=''*/ style={{fontSize: '30px'}}
            onClick={() => adicionarCarrinho(item.id)}>Comprar</a>
        </div >
  )
}

export default LojaCard
