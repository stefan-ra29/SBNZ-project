import React, { useEffect, useState } from "react";
import "./styles/Registration.css";
import {
  transaction,
  transactionWithoutCheck,
} from "../services/TransactionsService";

const Transaction = () => {
  const [senderCardNumber, setSenderCardNumber] = useState();
  const [senderCvvNumber, setSenderCvvNumber] = useState();
  const [cardExpirationYear, setCardExpirationYear] = useState();
  const [cardExpirationMonth, setCardExpirationMonth] = useState();
  const [senderAccountNumber, setSenderAccountNumber] = useState();
  const [receiverAccountNumber, setReceiverAccountNumber] = useState();
  const [amount, setAmount] = useState();
  const [locationLatitude, setLocationLatitude] = useState();
  const [locationLongitude, setLocationLongitude] = useState();

  useEffect(() => {
    // Function to fetch the geolocation data
    const fetchGeolocation = () => {
      if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(
          (position) => {
            setLocationLatitude(position.coords.latitude);
            setLocationLongitude(position.coords.longitude);
          },
          (error) => {
            console.log("Error getting geolocation:", error);
          }
        );
      } else {
        console.log("Geolocation is not supported by this browser.");
      }
    };

    fetchGeolocation();
  }, []);

  async function handleTransaction() {
    let res = await transaction({
      senderCardNumber: senderCardNumber,
      senderCvvNumber: senderCvvNumber,
      cardExpirationYear: cardExpirationYear,
      cardExpirationMonth: cardExpirationMonth,
      senderAccountNumber: senderAccountNumber,
      receiverAccountNumber: receiverAccountNumber,
      amount: amount,
      locationLatitude: locationLatitude,
      locationLongitude: locationLongitude,
    });
    if (res != "Success") {
      if (window.confirm(res + ". Do you want to proceed anyway?") == true) {
        transactionWithoutCheck({
          senderCardNumber: senderCardNumber,
          senderCvvNumber: senderCvvNumber,
          cardExpirationYear: cardExpirationYear,
          cardExpirationMonth: cardExpirationMonth,
          senderAccountNumber: senderAccountNumber,
          receiverAccountNumber: receiverAccountNumber,
          amount: amount,
          locationLatitude: locationLatitude,
          locationLongitude: locationLongitude,
        });
      }
    }
  }

  return (
    <div>
      <div className="registration-form-container">
        <div className="registration-form-row">
          <label className="registration-form-label">Your card number: </label>
          <input
            className="registration-form-input"
            type="text"
            onChange={(e) => setSenderCardNumber(parseInt(e.target.value))}
          />
        </div>
        <div className="registration-form-row">
          <label className="registration-form-label">CVV number: </label>
          <input
            className="registration-form-input"
            type="text"
            onChange={(e) => setSenderCvvNumber(parseInt(e.target.value))}
          />
        </div>
        <div className="registration-form-row">
          <label className="registration-form-label">Expiration year: </label>
          <input
            className="registration-form-input"
            type="number"
            onChange={(e) => setCardExpirationYear(e.target.value)}
          />
        </div>
        <div className="registration-form-row">
          <label className="registration-form-label">Expiration month: </label>
          <input
            className="registration-form-input"
            type="number"
            onChange={(e) => setCardExpirationMonth(e.target.value)}
          />
        </div>

        <div className="registration-form-row">
          <label className="registration-form-label">
            Your account number:
          </label>
          <input
            className="registration-form-input"
            type="text"
            onChange={(e) => setSenderAccountNumber(parseInt(e.target.value))}
          />
        </div>

        <div className="registration-form-row">
          <label className="registration-form-label">
            Reciever account number:
          </label>
          <input
            className="registration-form-input"
            type="text"
            onChange={(e) => setReceiverAccountNumber(parseInt(e.target.value))}
          />
        </div>

        <div className="registration-form-row">
          <label className="registration-form-label">Amount: </label>
          <input
            className="registration-form-input"
            type="number"
            onChange={(e) => setAmount(e.target.value)}
          />
        </div>

        <div className="registration-form-button-wrapper">
          <button onClick={() => handleTransaction()}>Make transaction</button>
        </div>
      </div>
    </div>
  );
};

export default Transaction;
