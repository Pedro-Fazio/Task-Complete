import { useState } from 'react'

const AddTask = ({ onAdd }) => {
  const [texto, setText] = useState('')
  const [dia, setDay] = useState('')
  const [reminder, setReminder] = useState(false)

  const onSubmit = (e) => {
    e.preventDefault()

    if (!texto) {
      alert('Por favor adicione uma tarefa')
      return
    }

    onAdd({ texto, dia, reminder })

    setText('')
    setDay('')
    setReminder(false)
  }

  return (
    <form className='add-form' onSubmit={onSubmit}>
      <div className='form-control'>
        <label style={{ fontWeight: 'bold' }} > Nome da Tarefa </label>
        <input
          type='text'
          placeholder='Nome da Tarefa'
          value={texto}
          onChange={(e) => setText(e.target.value)}
        />
      </div>
      <div className='form-control'>
        <label style={{ fontWeight: 'bold' }} > Data </label>
        <input
          type='text'
          placeholder='Data'
          value={dia}
          onChange={(e) => setDay(e.target.value)}
        />
      </div>
      <div className='form-control form-control-check'>
        <label> Importante </label>
        <input
          type='checkbox'
          checked={reminder}
          value={reminder}
          onChange={(e) => setReminder(e.currentTarget.checked)}
        />
      </div>

      <input type='submit' value='Salvar Tarefa' className='btn btn-block' />
    </form>
  )
}

export default AddTask
