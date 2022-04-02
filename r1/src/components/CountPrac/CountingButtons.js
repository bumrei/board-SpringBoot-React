import React from 'react';

const CountingButtons = (props) => {
    return (
        <div>
            <h1>버튼</h1>
            <button onClick={() => props.fn(1)}>올림</button>
            <button onClick={() => props.fn(-1)}>내림</button>
        </div>
    );
};

export default CountingButtons;