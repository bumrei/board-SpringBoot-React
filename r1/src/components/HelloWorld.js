import React, {useState} from 'react';
import ShowName from "./ShowName";
import OddEven from "./OddEven";

const HelloWorld = () => {

    const name = "Hong Gil Dong"
    const [num, setNum] = useState(10)


    return (
        <>
            <div>
                <h1>Hello World</h1>
            </div>
            <div>
                <ShowName name={name} num={num}></ShowName>
                <OddEven num={num}></OddEven>
                <OddEven num={num}></OddEven>
                <OddEven num={num}></OddEven>
                <OddEven num={num}></OddEven>
                <OddEven num={num}></OddEven>
                <button onClick={() => setNum(num + 1)}>Change</button>
            </div>
        </>
    );
};


export default HelloWorld;