import React, {useState} from 'react';
import Dice from "./Dice";
import CountContainer from "./counter/CountContainer";
import KioskContainer from "./kiosk/KioskContainer";
import CountingContainer from "./CountPrac/CountingContainer";
import Middle from "./Middle";
import ContinuousSlider from "./materials/ContinuousSlider";

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
            <Middle></Middle>
            <ContinuousSlider></ContinuousSlider>


        </>
    );
};


export default HelloWorld;