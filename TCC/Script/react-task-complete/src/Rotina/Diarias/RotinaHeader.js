import PropTypes from 'prop-types'
import Button from './Button'

const RotinaHeader = ({ title, onAdd, showAdd }) => {

  return (
    <header className='rotina-header'>
      <h1>{title}</h1>
      <Button
        color={showAdd ? 'red' : 'green'}
        text={showAdd ? 'Fechar' : 'Adicionar'}
        onClick={onAdd}
      />

    </header>
  )
}

RotinaHeader.defaultProps = {
  title: 'Adicione uma Diária',
}

RotinaHeader.propTypes = {
  title: PropTypes.string.isRequired,
}

export default RotinaHeader
