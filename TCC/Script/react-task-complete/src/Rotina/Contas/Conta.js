import { useState, useEffect } from 'react'
import { BrowserRouter, Route, Routes } from 'react-router-dom'
import RotinaHeader from './RotinaHeader'
import Tasks from './Tasks'
import AddTask from './AddTask'

const Conta = ({ completarTarefa }) => {
    const [showAddConta, setShowAddConta] = useState(false)
    const [contas, setContas] = useState([])

    /*** Requisições ao backend ***/

    useEffect(() => {
        const getContas = async () => {
            const contasFromServer = await fetchContas()
            setContas(contasFromServer)
        }

        getContas()
    }, [])

    const fetchContas = async () => {
        const res = await fetch('http://localhost:8080/contas')
        const data = await res.json()

        return data
    }

    const fetchConta = async (id) => {
        const res = await fetch(`http://localhost:8080/contas/${id}`)
        const data = await res.json()

        return data
    }

    const addConta = async (conta) => {
        const res = await fetch('http://localhost:8080/contas', {
            method: 'POST',
            headers: {
                'Content-type': 'application/json',
            },
            body: JSON.stringify(conta),
        })

        const data = await res.json()

        setContas([...contas, data])

        // const id = Math.floor(Math.random() * 10000) + 1
        // const newConta = { id, ...conta }
        // setContas([...contas, newConta])
    }

    // Delete Conta
    const deleteConta = async (id) => {
        const res = await fetch(`http://localhost:8080/contas/${id}`, {
            method: 'DELETE',
        })

        
        // We should control the response status to decide if we will change the state or not.
        res.status === 200
        ? setContas(contas.filter((conta) => conta.id !== id))
        : alert('Erro ao deletar essa conta')
        
        completarTarefa();

        setTimeout(function() {
            window.location.reload();
        }, 500);
    }

    // Toggle Reminder
    const toggleReminder = async (id) => {
        const contaToToggle = await fetchConta(id)
        const updConta = { ...contaToToggle, reminder: !contaToToggle.reminder }

        const res = await fetch(`http://localhost:8080/contas/${id}`, {
            method: 'PUT',
            headers: {
                'Content-type': 'application/json',
            },
            body: JSON.stringify(updConta),
        })

        const data = await res.json()

        setContas(
            contas.map((conta) =>
                conta.id === id ? { ...conta, reminder: data.reminder } : conta
            )
        )
    }

    return (
        <div className="Rotina">
            <div className="header-rotina-area">
                <RotinaHeader onAdd={() => setShowAddConta(!showAddConta)}
                    showAdd={showAddConta} />
            </div>
            {showAddConta && <AddTask onAdd={addConta} />}
            {contas.length > 0 ?
                <div className="tasks-area">
                    <p className="tarefas-rotina"> Contas </p>
                    <Tasks tasks={contas} onDelete={deleteConta}
                        onToggle={toggleReminder} />
                </div>
                : 'Sem Contas pendentes'}
        </div>
    )
}

export default Conta
