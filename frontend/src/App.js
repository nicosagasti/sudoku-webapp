import React, { useState, useEffect } from 'react';

function App() {
  const [board, setBoard] = useState([]);

  useEffect(() => {
    fetchBoard();
  }, []);

  const fetchBoard = async () => {
    try {
      const response = await fetch('/getBoard');
      const data = await response.json();
      console.log(data);
      setBoard(data);

    } catch (error) {
      console.error('Error fetching the board:', error);
    }
  };

  return (
    <div className="App">
      <h1>Sudoku Board</h1>
      <div className="board">
        {board.map((row, rowIndex) => (
          <div key={rowIndex} className="row">
            {row.map((cell, colIndex) => (
              <div key={colIndex} className="cell">
                {cell.number}
                {cell.fixed && <span className="fixed">*</span>}
              </div>
            ))}
          </div>
        ))}
      </div>
    </div>
  );
}

export default App;
