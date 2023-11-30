import { AiFillCheckCircle } from 'react-icons/ai'

const Task = ({ task, onDelete, onToggle }) => {
  return (
    <div
      className={`task ${task.reminder && 'reminder'}`}
      onDoubleClick={() => onToggle(task.id)}
    >
      <h3>
        {task.texto}{' '}
        <AiFillCheckCircle className="check-circle-task"
          onClick={() => onDelete(task.id)}
          size={30}
        />
      </h3>
      <p>Valor: {task.valor}</p>
      <p>Dia de vencimento: {task.dia}</p>
    </div>
  )
}

export default Task
