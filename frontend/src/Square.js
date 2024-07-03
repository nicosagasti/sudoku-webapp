import React from 'react';
import './Square.css'; 

function Square({ value, isFixed, onClick }) {
    return (
        <div
            className={`square ${isFixed ? 'fixed' : ''}`}
            onClick={onClick}
        >
            {value !== 0 ? value : ''}
        </div>
    );
}

export default Square;
