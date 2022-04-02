import React from 'react';

const CountingDisplay = (props) => {
    return (
        <div>
            <h1>디스플레이</h1>
            <h2>{props.num}</h2>
        </div>
    );
};

export default CountingDisplay;