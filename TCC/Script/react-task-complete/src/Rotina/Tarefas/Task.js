import { AiFillCheckCircle } from 'react-icons/ai'

const Task = ({ task, onDelete, onToggle }) => {
  return (
    <div
      className={`task ${task.reminder && 'reminder'}`}
      onDoubleClick={() => onToggle(task.id)}
    >
      <h3>
        {task.text}{' '}
        <AiFillCheckCircle className="check-circle-task"
          onClick={() => onDelete(task.id)}
          size={30}
        />
      </h3>
      <p>{task.day}</p>
    </div>
  )
}

export default Task
