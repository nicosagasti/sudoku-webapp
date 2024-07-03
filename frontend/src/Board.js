import React from 'react';
import Square from './Square';
import './Board.css'; // Asegúrate de tener estilos para el tablero

function Board({ grid, onClick }) {
    return (
        <div className="board">
            {grid.map((row, i) => (
                <div key={i} className="board-row">
                    {row.map((cell, j) => (
                        <Square
                            key={j}
                            value={cell.value}
                            isFixed={cell.isFixed}
                            onClick={() => !cell.isFixed && onClick(i, j)}
                        />
                    ))}
                </div>
            ))}
        </div>
    );
}

export default Board;
