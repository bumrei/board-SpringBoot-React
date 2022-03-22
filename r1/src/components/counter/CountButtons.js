import React from 'react';

const CountButtons = ({fn}) => {
    return (
        <div>
            <button onClick={() => fn(1)}>INC</button>
            <button onClick={() => fn(-1)}>DEC</button>
        </div>
    );
};

export default CountButtons;