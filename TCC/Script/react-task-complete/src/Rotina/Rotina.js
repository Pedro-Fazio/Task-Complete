import { useState, useEffect } from 'react'
import { BrowserRouter, Route, Routes, Link } from 'react-router-dom'
import Tarefa from './Tarefas/Tarefa'
import Diaria from './Diarias/Diaria'
import Habito from './Habitos/Habito'
import Conta from './Contas/Conta'
import './Rotina.css';

const Rotina = ({ completarTarefa }) => {
    // let escolhaRotina = "tarefas";
    const [escolhaRotina, setEscolhaRotina] = useState("tarefas")

    const mudarEscolhaRotina = (escolha) => {
        // escolhaRotina = escolha;
        setEscolhaRotina(escolha);
        estadoRotina();
    }

    const estadoRotina = () => {
        if (escolhaRotina === "tarefas") {
            // return <Link to="/rotina/tarefas"> <Tarefa completarTarefa={completarTarefa}/> </Link>
            return <Tarefa completarTarefa={completarTarefa}/>
        } else if (escolhaRotina === "diarias") {
            // return <Link to="/rotina/diarias"> <Diaria completarTarefa={completarTarefa}/> </Link>
            return <Diaria completarTarefa={completarTarefa}/>
        } else if (escolhaRotina === "habitos") {
            // return <Link to="/rotina/habitos"> <Habito completarTarefa={completarTarefa}/> </Link>
            return <Habito completarTarefa={completarTarefa}/>
        } else if (escolhaRotina === "contas") {
            // return <Link to="/rotina/contas"> <Conta completarTarefa={completarTarefa}/> </Link>
            return <Conta completarTarefa={completarTarefa}/>
        }
    }

    return (
        <div className="Rotina">
            {/* <Link to="/rotina/tarefas"> <button className='btn-rotina'> Tarefas </button> </Link>
            <Link to="/rotina/diarias"> <button className='btn-rotina'> Diarias </button> </Link>
            <Link to="/rotina/habitos"> <button className='btn-rotina'> Habitos </button> </Link>
            <Link to="/rotina/contas"> <button className='btn-rotina'> Contas </button> </Link> */}

            <div className='opcoes-rotina'>
                <button className='btn-rotina' onClick={() => mudarEscolhaRotina("tarefas")}> Tarefas </button>
                <button className='btn-rotina' onClick={() => mudarEscolhaRotina("diarias")}> Diarias </button>
                <button className='btn-rotina' onClick={() => mudarEscolhaRotina("habitos")}> Habitos </button>
                <button className='btn-rotina' onClick={() => mudarEscolhaRotina("contas")}> Contas </button>
            </div>
            {estadoRotina()}
        </div>
    )
}

export default Rotina
