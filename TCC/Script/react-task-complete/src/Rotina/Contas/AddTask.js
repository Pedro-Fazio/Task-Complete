import { useState } from 'react'

const AddTask = ({ onAdd }) => {
  const [texto, setText] = useState('')
  const [dia, setDia] = useState('')
  const [reminder, setReminder] = useState(false)
  const [valor, setValor] = useState(0)

  const onSubmit = (e) => {
    e.preventDefault()

    if (!texto) {
      alert('Por favor adicione uma tarefa')
      return
    }

    onAdd({ texto, dia, valor, reminder })

    setText('')
    setDia('')
    setReminder(false)
    setValor(0)
  }

  return (
    <form className='add-form' onSubmit={onSubmit}>
      <div className='form-control'>
        <label> Nome da Conta </label>
        <input
          type='text'
          placeholder='Nome da Conta'
          value={texto}
          onChange={(e) => setText(e.target.value)}
        />
      </div>
      <div className='form-control'>
        <label> Dia de vencimento </label>
        <input
          type='text'
          placeholder='DD/MM/AAAA'
          value={dia}
          onChange={(e) => setDia(e.target.value)}
        />
      </div>
      <div className='form-control'>
        <label> Valor da conta </label>
        <input
          type='text'
          placeholder='Valor da conta'
          value={valor}
          onChange={(e) => setValor(e.target.value)}
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

      <input type='submit' value='Salvar Conta' className='btn btn-block' />
    </form>
  )
}

export default AddTask
