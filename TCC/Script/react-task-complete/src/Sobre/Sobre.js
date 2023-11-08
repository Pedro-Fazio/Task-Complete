import { Link } from 'react-router-dom'
import {FaFacebook, FaInstagram} from 'react-icons/fa'
import './Sobre.css';

const Sobre = ({ }) => {
  return (
    <div className="container-sobre">
      <div className="background-sobre">
          <h1 className="texto-background"> 
            Auxiliando sua rotina
          </h1>
          <img className='img-sobre' src="https://i.imgur.com/t4aUhGf.jpg" alt="relogio com calendario"></img>
      </div>
      <div className="info-sobre">
        <section className="container-proj-grad">
          <h1 className="titulo-proj-grad"> 
            Projeto de graduação
          </h1>
          <h2 className="texto-proj-grad"> 
            Esse projeto se baseia em uma solução em formato de jogo para apoiar
            pessoas com dificuldade intelectual na realização de atividades de rotina.
            Assim, fazendo uma gamificação dessa rotina com a finalidade de engajar,
            motivar e tornar essas tarefas mais prazerosas ao simplificar o seu aprendizado.
          </h2>
        </section>
        <section className="infos-extras">
            <div className="texto-infos-extras">
              <h1 className="prefixo-nome">
                Orientadora
              </h1>
              <h1 className="nome">
                Kamila Rios da Hora Rodrigues
              </h1>
            </div>
            <div className="texto-infos-extras">
              <h1 className="prefixo-nome">
                Aluno
              </h1>
              <h1 className="nome">
                Pedro Afonso Fazio Michalichem
              </h1>
            </div>
            <div className="logotipo-sobre">
                <img className="img-loja" src="https://i.imgur.com/D50NWnq.png" alt="logotipo do task complete"></img>
            </div>
        </section>
      </div>
    </div>
  )
}

export default Sobre
