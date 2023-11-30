import { useState } from 'react'

const AddTask = ({ onAdd }) => {
  const [texto, setText] = useState('')
  const [contador, setContador] = useState(0)

  const onSubmit = (e) => {
    e.preventDefault()

    if (!texto) {
      alert('Por favor adicione um hábito')
      return
    }

    onAdd({ texto, contador })

    setText('')
    setContador(0)
  }

  return (
    <form className='add-form' onSubmit={onSubmit}>
      <div className='form-control'>
        <label> Nome do Hábito </label>
        <input
          type='text'
          placeholder='Nome do Hábito'
          value={texto}
          onChange={(e) => setText(e.target.value)}
        />
      </div>
      <input type='submit' value='Salvar Hábito' className='btn btn-block' />
    </form>
  )
}

export default AddTask
