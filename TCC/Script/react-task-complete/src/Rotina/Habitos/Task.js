import { AiFillCheckCircle } from 'react-icons/ai'
import { FaTimes } from 'react-icons/fa'
import { TiArrowLoop } from 'react-icons/ti'
import { Link } from 'react-router-dom'

const Task = ({ task, onDelete, onContador }) => {
  return (
    <div className={`task ${task.reminder && 'reminder'}`}>
      <p className='contador-habito'> {task.contador} </p>
      <h3>
        <TiArrowLoop className="check-habito-task"
          onClick={() => onContador(task.id)}
          size={35}
          color='blue'
          // href='#'
        /> 
        <p className='habito-texto'> {task.text}{' '} </p>
        <FaTimes className="check-delete-task"
          onClick={() => onDelete(task.id)}
          size={35}
          color='red'
        />
      </h3>
      <p>{task.day}</p>
    </div>
  )
}

export default Task
