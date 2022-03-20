import React, {useState} from 'react';

const ShowName = ({name, num}) => {

    console.log("show name..................")

    return (
        <div>
            <h1>{name}</h1>
            <h1>{num}</h1>
        </div>
    );
};

export default ShowName;