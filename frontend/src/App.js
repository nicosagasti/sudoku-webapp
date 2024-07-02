import React, { useState, useEffect } from 'react';
import Board from './Board'; // Ajusta la ruta de importación según sea necesario

function App() {
  const [board, setBoard] = useState([]);
  const [number, setNumber] = useState(0);

  useEffect(() => {
    fetchBoard();
  }, []);

  function fetchBoard() {
    fetch('/getBoard')
      .then(response => response.json())
      .then(data => {
        console.log(data);
        setBoard(data);
      })
  }

  function handleClick(i, j) {
    console.log(`Clicked on cell (${i}, ${j})`);
  }

  function reloadBoard() {
    fetch('/reload')
      .then(response => response.json())
      .then(data => {
        console.log('Board reloaded:', data);
        setBoard(data);
      })
  }

  return (
    <div className="App">
      <h1>Sudoku Board</h1>
      <div className="board-container">
        <Board
          grid={board}
          onClick={(i, j) => handleClick(i, j)}
        />
      </div>
      <div className="buttons">
        <button className="reloadBoard" onClick={reloadBoard}> </button>
        <button className= "button9" onClick={setNumber(9)}></button>
      </div>
    </div>
  );
}

export default App;
