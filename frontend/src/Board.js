import React from 'react';
import Square from './Square';

function Board({ grid }) {
    const numOfRows = 9;
    const numOfCols = 9;

    // Asumimos que grid es un arreglo 2D de 9x9

    return (
        <div className="board-container">
            <div className="vertical">
                <div
                    className="colClues"
                    style={{
                        gridTemplateRows: '60px',
                        gridTemplateColumns: `60px repeat(${numOfCols}, 40px)`
                    }}
                >
                </div>
                <div className="horizontal">
                    <div
                        className="rowClues"
                        style={{
                            gridTemplateRows: `repeat(${numOfRows}, 40px)`,
                            gridTemplateColumns: '60px'
                        }}
                    >
                    </div>
                    <div
                        className="board"
                        style={{
                            gridTemplateRows: `repeat(${numOfRows}, 40px)`,
                            gridTemplateColumns: `repeat(${numOfCols}, 40px)`
                        }}
                    >
                        {Array.from({ length: numOfRows }, (_, i) => (
                            <div key={i} className="row">
                                {Array.from({ length: numOfCols }, (_, j) => (
                                    <Square
                                        key={`${i}-${j}`}
                                        value={grid[i][j].getNumber()}
                                        isFixed={grid[i][j].isFixed()}
                                    />
                                ))}
                            </div>
                        ))}
                    </div>
                </div>
            </div>
        </div>
    );
}

export default Board;
