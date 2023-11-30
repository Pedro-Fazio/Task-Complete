import { useState } from 'react'

const AddTask = ({ onAdd }) => {
  const [texto, setText] = useState('')
  const [complete, setComplete] = useState(false)

  const onSubmit = (e) => {
    e.preventDefault()

    if (!texto) {
      alert('Por favor adicione uma diária')
      return
    }

    onAdd({ texto, complete })

    setText('')
    setComplete(false)
  }

  return (
    <form className='add-form' onSubmit={onSubmit}>
      <div className='form-control'>
        <label> Nome da Diária </label>
        <input
          type='text'
          placeholder='Nome da Diária'
          value={texto}
          onChange={(e) => setText(e.target.value)}
        />
      </div>
      {/* <div className='form-control form-control-check'>
        <label> Importante </label>
        <input
          type='checkbox'
          checked={reminder}
          value={reminder}
          onChange={(e) => setReminder(e.currentTarget.checked)}
        />
      </div> */}

      <input type='submit' value='Salvar Diária' className='btn btn-block' />
    </form>
  )
}

export default AddTask
