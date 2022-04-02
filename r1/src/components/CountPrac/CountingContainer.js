import React, {useState} from 'react';
import CountingDisplay from "./CountingDisplay";
import CountingButtons from "./CountingButtons";

const initialNum = 0;

const CountingContainer = (props) => {

    // const [num , setNum] = useState(initialNum);
    //
    // const change = (amount) => {
    //     setNum(num + amount)
    // }

    return (

        <div>
            <CountingDisplay num={props.num}></CountingDisplay>
            <CountingButtons fn={props.fn}></CountingButtons>
        </div>
    );
};

export default CountingContainer;