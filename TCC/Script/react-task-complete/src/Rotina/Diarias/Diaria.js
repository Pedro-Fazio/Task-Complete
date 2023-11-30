import { useState, useEffect } from 'react'
import { BrowserRouter, Route, Routes } from 'react-router-dom'
import RotinaHeader from './RotinaHeader'
import Tasks from './Tasks'
import AddTask from './AddTask'

const Diaria = ({ completarTarefa }) => {
    const [showAddDiaria, setShowAddDiaria] = useState(false)
    const [diarias, setDiarias] = useState([])
    const moment = require('moment');

    /*** Requisições ao backend ***/

    useEffect(() => {
        const getDiarias = async () => {
            const diariasFromServer = await fetchDiarias()
            setDiarias(diariasFromServer)
        }

        getDiarias()
    }, [])

    const fetchDiarias = async () => {
        const res = await fetch('http://localhost:8080/diarias')
        const data = await res.json()

        return data
    }

    const fetchDiaria = async (id) => {
        const res = await fetch(`http://localhost:8080/diarias/${id}`)
        const data = await res.json()

        return data
    }

    const addDiaria = async (diaria) => {
        const res = await fetch('http://localhost:8080/diarias', {
            method: 'POST',
            headers: {
                'Content-type': 'application/json',
            },
            body: JSON.stringify(diaria),
        })

        const data = await res.json()

        setDiarias([...diarias, data])

        // const id = Math.floor(Math.random() * 10000) + 1
        // const newDiaria = { id, ...diaria }
        // setDiarias([...diarias, newDiaria])
    }

    // Delete Diaria
    const deleteDiaria = async (id) => {
        const res = await fetch(`http://localhost:8080/diarias/${id}`, {
            method: 'DELETE',
        })

        // We should control the response status to decide if we will change the state or not.
        res.status === 200
            ? setDiarias(diarias.filter((diaria) => diaria.id !== id))
            : alert('Erro ao deletar essa diária')
    }

    // Completar diária
    const completarDiaria = async (id) => {
        const diariaToComplete = await fetchDiaria(id)
        const updDiaria = { ...diariaToComplete, complete: !diariaToComplete.complete }

        const res = await fetch(`http://localhost:8080/diarias/${id}`, {
            method: 'PUT',
            headers: {
                'Content-type': 'application/json',
            },
            body: JSON.stringify(updDiaria),
        })

        const data = await res.json()

        setDiarias(
            diarias.map((diaria) =>
                diaria.id === id ? { ...diaria, complete: data.complete } : diaria
            )
        )

        completarTarefa();

        setTimeout(function() {
            window.location.reload();
        }, 500);
    }

    //*** Outra funcionalidades ***///

    function triggerFunction() {
        //console.log('Trigger acionado às 00:00!');
        // Codigo que sera executado todos os dias às 00:00
      }
      
      // Função para calcular o tempo restante até às 00:00
      function tempoAteMeiaNoite() {
        const now = moment();
        
        //const horaAtual = moment().hour();
        const midnight = moment().endOf('day');

        //const duration = moment.duration(horaAtual.diff(now));
        const duration = moment.duration(midnight.diff(now));
        return duration.asMilliseconds();
      }
      
      // Executar a função pela primeira vez
      triggerFunction();
      
      // Definir o intervalo para executar a função todos os dias às 00:00
      setInterval(() => {
        triggerFunction();
      }, tempoAteMeiaNoite());

    return (
        <div className="Rotina">
            <div className="header-rotina-area">
                <RotinaHeader onAdd={() => setShowAddDiaria(!showAddDiaria)}
                    showAdd={showAddDiaria} />
            </div>
            {showAddDiaria && <AddTask onAdd={addDiaria} />}
            {diarias.length > 0 ?
                <div className="tasks-area">
                    <p className="tarefas-rotina"> Diárias </p>
                    <Tasks tasks={diarias} onDelete={deleteDiaria}
                        onComplete={completarDiaria} />
                </div>
                : 'Sem diárias pendentes'}
        </div>
    )
}

export default Diaria
