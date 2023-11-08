import { useState, useEffect } from 'react'
import { BrowserRouter, Route, Routes } from 'react-router-dom'
import RotinaHeader from './RotinaHeader'
import Tasks from './Tasks'
import AddTask from './AddTask'

const Tarefa = ({ completarTarefa }) => {
    const [showAddTask, setShowAddTask] = useState(false)
    const [tasks, setTasks] = useState([])

    /*** Requisições ao backend ***/

    useEffect(() => {
        const getTasks = async () => {
            const tasksFromServer = await fetchTasks()
            setTasks(tasksFromServer)
        }

        getTasks()
    }, [])

    const fetchTasks = async () => {
        const res = await fetch('http://localhost:5000/tarefas')
        const data = await res.json()

        return data
    }

    const fetchTask = async (id) => {
        const res = await fetch(`http://localhost:5000/tarefas/${id}`)
        const data = await res.json()

        return data
    }

    const addTask = async (task) => {
        const res = await fetch('http://localhost:5000/tarefas', {
            method: 'POST',
            headers: {
                'Content-type': 'application/json',
            },
            body: JSON.stringify(task),
        })

        const data = await res.json()

        setTasks([...tasks, data])

        // const id = Math.floor(Math.random() * 10000) + 1
        // const newTask = { id, ...task }
        // setTasks([...tasks, newTask])
    }

    // Delete Task
    const deleteTask = async (id) => {
        const res = await fetch(`http://localhost:5000/tarefas/${id}`, {
            method: 'DELETE',
        })

        // We should control the response status to decide if we will change the state or not.
        res.status === 200
            ? setTasks(tasks.filter((task) => task.id !== id))
            : alert('Error Deleting This Task')

        completarTarefa();

        setTimeout(function() {
            window.location.reload();
        }, 500);
        
    }

    // Toggle Reminder
    const toggleReminder = async (id) => {
        const taskToToggle = await fetchTask(id)
        const updTask = { ...taskToToggle, reminder: !taskToToggle.reminder }

        const res = await fetch(`http://localhost:5000/tarefas/${id}`, {
            method: 'PUT',
            headers: {
                'Content-type': 'application/json',
            },
            body: JSON.stringify(updTask),
        })

        const data = await res.json()

        setTasks(
            tasks.map((task) =>
                task.id === id ? { ...task, reminder: data.reminder } : task
            )
        )
    }

    return (
        <div className="tarefa">
            <div className="header-rotina-area">
                <RotinaHeader onAdd={() => setShowAddTask(!showAddTask)}
                    showAdd={showAddTask} />
            </div>
            {showAddTask && <AddTask onAdd={addTask} />}
            {tasks.length > 0 ?
                <div className="tasks-area">
                    <p className="tarefas-rotina"> Tarefas </p>
                    <Tasks tasks={tasks} onDelete={deleteTask}
                        onToggle={toggleReminder} />
                </div>
                : 'Sem tarefas pendentes'}
        </div>
    )
}

export default Tarefa
