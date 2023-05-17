import './BookForm.css'
import { useState, useRef } from 'react';
import Input from '../ui/Input';
import { rateBook } from '../../services/BookService';

export default function BookForm(props) {

    const [amountisValid, setAmountIsValid] = useState(true);
    const [rateIsValid, setRateIsValid] = useState(true);

    const amountInputRef = useRef();
    const rateInputRef = useRef();

    function submitHandler(event) {
        event.preventDefault();

        const enteredAmount = amountInputRef.current.value;
        const enteredAmountNumber = +enteredAmount

        if(enteredAmount.trim().length === 0 || enteredAmountNumber < 1 || enteredAmountNumber > 5) {
            setAmountIsValid(false);
            return;
        }

        props.onAddToCart(enteredAmountNumber);
    }

    function submitRateHandler(event) {
        event.preventDefault();

        const enteredRate = rateInputRef.current.value;
        const enteredRateNumber = +enteredRate

        if(enteredRate.trim().length === 0 || enteredRateNumber < 1 || enteredRateNumber > 5) {
            setRateIsValid(false);
            return;
        }
        rateBook(enteredRate, props.id);
    }

    return (
        <>
        <form className='form' onSubmit={submitHandler}>
            <Input 
            ref={amountInputRef}
            label="Amount" 
            input={{
                id: 'amount_' + props.id, 
                type: 'number',
                min: '1',
                max: '5',
                step: '1',
                defaultValue: '1'}}
            />

            <button>+ Add</button>

            {!amountisValid && <p>Please enter a valid amount (1-5)</p>}
        </form>
        <form className='form' onSubmit={submitRateHandler}>
            <Input 
            ref={rateInputRef}
            label="Rate" 
            input={{
                id: 'rate_' + props.id, 
                type: 'number',
                min: '1',
                max: '5',
                step: '1',
                defaultValue: '1'}}
            />
            
            <button>Rate</button>

            {!rateIsValid && <p>Please enter a valid rate (1-5)</p>}
        </form>
        </>
    );
};