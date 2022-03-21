import React, {useState} from 'react';


const initState = {
    count : 0,
    value : 0
}

const Dice = () => {

    const [now, setNow] = useState(initState)

    const roll = () => {
        // 전개 연산자자
       const newNow = {...now}
        newNow.count = now.count + 1
        newNow.value = parseInt((Math.random() * 6) + 1)
        setNow(newNow)
    }

    const reset = () => {
        setNow(initState)
    }

    return (
        <div>
            <h1>Count : {now.count}</h1>
            <h1>VALUE : {now.value}</h1>
            <button onClick={() => roll()}>ROLL</button>
            <button onClick={() => reset()}>RESET</button>
        </div>
    );
};

export default Dice;