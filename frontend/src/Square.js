import React from 'react';

function Square({ value, isFixed }) {
    const squareStyle = {
        backgroundColor: isFixed ? '#15b58e' : 'lightgrey',
    };

    let displayValue = value === 0 ? null : value; 

    return (
        // <button className="square" style={squareStyle} onClick={onClick}>
        <button className= "square" style = {squareStyle}>
            {displayValue}
        </button>
    );
}

export default Square;
