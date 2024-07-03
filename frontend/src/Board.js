import React from 'react';
import './Board.css'; // Asegúrate de tener estilos para el tablero y las celdas

function Board({ grid, onClick }) {
    return (
        <div className="board">
            {grid.map((row, i) => (
                <div key={i} className="board-row">
                    {row.map((cell, j) => (
                        <div
                            key={j}
                            className={`board-cell ${cell.isFixed ? 'fixed' : ''}`}
                            onClick={() => !cell.isFixed && onClick(i, j)}
                        >
                            {cell.value !== 0 ? cell.value : ''}
                        </div>
                    ))}
                </div>
            ))}
        </div>
    );
}

export default Board;
