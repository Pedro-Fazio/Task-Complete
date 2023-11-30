import { AiFillCheckCircle } from 'react-icons/ai'
import { FaTimes } from 'react-icons/fa'
import { ImCheckboxUnchecked, ImCheckboxChecked } from 'react-icons/im'

const Task = ({ task, onDelete, onComplete }) => {
  return (
    <div
      className={`task ${task.complete && 'complete'}`}
      //onDoubleClick={() => onComplete(task.id)}
    >
      <h3>
        {task.complete ? 
        <ImCheckboxChecked className="check-diaria-task"
          // onClick={() => onComplete(task.id)}
          size={35}
          color='green'
          //cursor={"auto"}
        />
        :
        <ImCheckboxUnchecked className="check-diaria-task"
          onClick={() => onComplete(task.id)}
          size={35}
          color='grey'
        />}
      
        {task.texto}{' '}
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
