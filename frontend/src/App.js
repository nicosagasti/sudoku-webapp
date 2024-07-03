import React, { useState, useEffect } from 'react';
import Board from './Board'; // Ajusta la ruta de importación según sea necesario

function App() {
  const [board, setBoard] = useState([]);
  const [selectedNumber, setSelectedNumber] = useState(null);
  const [message, setMessage] = useState('');

  useEffect(() => {
    fetchBoard();
  }, []);

  function fetchBoard() {
    fetch('/getBoard')
      .then(response => response.json())
      .then(data => {
        console.log(data);
        setBoard(data);
      });
  }

  function handleClick(i, j) {
    console.log(`Clicked on cell (${i}, ${j})`);
    if (selectedNumber !== null) {
      fetch(`/number/${i}/${j}?number=${selectedNumber}`, {
        method: 'POST',
      })
        .then(response => response.json())
        .then(isWon => {
          if (isWon) {
            setMessage('Congratulations! You won!');
          } else {
            setMessage('');
          }
          // Actualizar el tablero localmente
          const newBoard = [...board];
          newBoard[i][j] = selectedNumber;
          setBoard(newBoard);
        })
        .catch(error => {
          console.error('Error setting number:', error);
        });
    }
  }

  function reloadBoard() {
    fetch('/reloadBoard')
      .then(response => response.json())
      .then(data => {
        console.log('Board reloaded:', data);
        setBoard(data);
        setMessage(''); // Reset message on board reload
      });
  }

  function handleNumberClick(number) {
    setSelectedNumber(number);
    console.log(`Selected number: ${number}`);
  }

  return (
    <div className="App">
      <h1>Sudoku Board</h1>
      {message && <div className="message">{message}</div>}
      <div className="board-container">
        <Board
          grid={board}
          onClick={(i, j) => handleClick(i, j)}
        />
      </div>
      <div className="buttons">
        <button className="reloadBoard" onClick={reloadBoard}>Reload Board</button>
      </div>
      <div className="number-buttons">
        {[1, 2, 3, 4, 5, 6, 7, 8, 9].map(number => (
          <button key={number} onClick={() => handleNumberClick(number)}>
            {number}
          </button>
        ))}
      </div>
    </div>
  );
}

export default App;
