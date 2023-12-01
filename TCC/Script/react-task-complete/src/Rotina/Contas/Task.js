import { AiFillCheckCircle } from 'react-icons/ai'

const Task = ({ task, onDelete, onToggle }) => {
  return (
    <div
      className={`task ${task.reminder && 'reminder'}`}
      onDoubleClick={() => onToggle(task.id)}
    >
      <h3 style={{fontSize: '25px'}}>
        {task.texto}{' '}
        <AiFillCheckCircle className="check-circle-task"
          onClick={() => onDelete(task.id)}
          size={30}
        />
      </h3>
      <p style={{fontSize: '25px'}}>Valor: {task.valor}</p>
      <p style={{fontSize: '25px'}}>Dia de vencimento: {task.dia}</p>
    </div>
  )
}

export default Task
