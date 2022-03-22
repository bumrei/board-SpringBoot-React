import React, {useState} from 'react';
import CountDisplay from "./CountDisplay";
import CountButtons from "./CountButtons";

const CountContainer = () => {

    const [num, setNum] = useState(0)

    const change = (amount) => {
        setNum(num + amount)
    }

    return (
        <div>
            <CountDisplay num={num}></CountDisplay>
            <CountButtons fn={change}></CountButtons>
        </div>
    );
};

export default CountContainer;