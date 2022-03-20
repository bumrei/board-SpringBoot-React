import React from 'react';

const OddEven = ({num}) => {
    return (
        <div>
            <h1>{num % 2 == 0 ? 'EVEN' : 'ODD'}</h1>
        </div>
    );
};

export default OddEven;