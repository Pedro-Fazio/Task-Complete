import { useState, useEffect } from 'react'
import { BrowserRouter, Route, Routes } from 'react-router-dom'
import RotinaHeader from './RotinaHeader'
import Tasks from './Tasks'
import AddTask from './AddTask'

const Habito = ({ completarTarefa }) => {
    const [showAddHabito, setShowAddHabito] = useState(false)
    const [habitos, setHabitos] = useState([])

    /*** Requisições ao backend ***/

    useEffect(() => {
        const getHabitos = async () => {
            const habitosFromServer = await fetchHabitos()
            setHabitos(habitosFromServer)
        }

        getHabitos()
    }, [])

    const fetchHabitos = async () => {
        const res = await fetch('http://localhost:5000/habitos')
        const data = await res.json()

        return data
    }

    const fetchHabito = async (id) => {
        const res = await fetch(`http://localhost:5000/habitos/${id}`)
        const data = await res.json()

        return data
    }

    const addHabito = async (habito) => {
        const res = await fetch('http://localhost:5000/habitos', {
            method: 'POST',
            headers: {
                'Content-type': 'application/json',
            },
            body: JSON.stringify(habito),
        })

        const data = await res.json()

        setHabitos([...habitos, data])
    }

    // Delete Habito
    const deleteHabito = async (id) => {
        const res = await fetch(`http://localhost:5000/habitos/${id}`, {
            method: 'DELETE',
        })

        res.status === 200
            ? setHabitos(habitos.filter((habito) => habito.id !== id))
            : alert('Erro ao deletar esse hábito')
    }

    // const contaHabito = async (habito) => {
    //     const res = await fetch(`http://localhost:5000/habitos/${habito.id}`, {
    //         method: 'PUT',
    //         headers: {
    //             'Content-type': 'application/json',
    //         },
    //         body: JSON.stringify(habito),
    //     })

    //     const data = await res.json()

    //     setHabitos([...habitos, data])
    // }

    const contaHabito = async (id) => {
        const habitoToCount = await fetchHabito(id)
        const updHabito = { ...habitoToCount, contador: habitoToCount.contador + 1}
        let fetchFlag = 0;

        //console.log(JSON.stringify(updHabito))

        const res = await fetch(`http://localhost:5000/habitos/${id}`, {
            method: 'PUT',
            headers: {
                'Content-type': 'application/json',
            },
            body: JSON.stringify(updHabito),
        })

        const data = await res.json()

        setHabitos(
            habitos.map((habito) =>
                habito.id === id ? { ...habito, contador: data.contador } : habito
            )
        )

        completarTarefa();

        setTimeout(function() {
            window.location.reload();
        }, 500);
    }

    return (
        <div className="Rotina">
            <div className="header-rotina-area">
                <RotinaHeader onAdd={() => setShowAddHabito(!showAddHabito)}
                    showAdd={showAddHabito} />
            </div>
            {showAddHabito && <AddTask onAdd={addHabito} />}
            {habitos.length > 0 ?
                <div className="tasks-area">
                    <p className="tarefas-rotina"> Hábitos </p>
                    <Tasks tasks={habitos} onDelete={deleteHabito} onContador={contaHabito} />
                </div>
                : 'Sem hábitos pendentes'}
        </div>
    )
}

export default Habito
