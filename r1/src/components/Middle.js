import React, {useState} from 'react';
import CountingContainer from "./CountPrac/CountingContainer";


const initialNum = 0

const Middle = () => {

    const [num, setNum] = useState(initialNum);

    const change = (amount) => {
        setNum(num + amount)
    }


    return (
        <div>
            <CountingContainer num={num} fn={change}></CountingContainer>

        </div>
    );
};

export default Middle;