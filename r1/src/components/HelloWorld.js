import React, {useState} from 'react';
import Dice from "./Dice";

const initState = {value : 1}

const HelloWorld = () => {

    const name = "Hong Gil Dong"
    const [num, setNum] = useState(initState)

    const change = () => {

        const newNum = {value : num.value + 1}

        setNum(newNum)
    }

    return (
        <>
            <Dice></Dice>
            <div>
                <h1>Hello World</h1>
            </div>
            <div>
                <h1>{num.value}</h1>

                <button onClick={() => change()}>Change</button>
            </div>
        </>
    );
};


export default HelloWorld;