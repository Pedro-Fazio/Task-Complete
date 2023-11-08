import Task from './Task'

const Tasks = ({ tasks, onDelete, onContador }) => {
  return (
    <>
      {tasks.map((task, index) => (
        <Task 
          key={index}
          task={task}
          onDelete={onDelete}
          onContador={onContador}
        />
      ))}
    </>
  )
}

export default Tasks
