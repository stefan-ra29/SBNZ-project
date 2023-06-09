import React, { useEffect, useState } from "react";
import { createUser } from "../services/UserService";
import "./styles/Registration.css";
import { BookCategory } from "./BookCategory";
import { MDBSelect } from "mdb-react-ui-kit";

import {
  MDBBtn,
  MDBContainer,
  MDBCard,
  MDBCardBody,
  MDBCardImage,
  MDBRow,
  MDBCol,
  MDBInput,
} from "mdb-react-ui-kit";
import { isUserLoggedIn } from "../utils/Utils";

export default function Registration() {
  useEffect(() => {
    if (isUserLoggedIn()) {
      window.location.replace("/");
    }
  }, []);

  const [user, setUser] = useState({ name: "", username: "", password: "", genres: [] });

  const handleSubmit = () => {
    try {
      console.log(user);
      console.log(user.genres);
      createUser(user);
      window.confirm("User is successfully registered");
      window.location.reload(false);
    } catch (error) {
      console.log(error);
    }
  };

  return (
    <MDBContainer fluid>
      <MDBRow className="d-flex justify-content-center align-items-center">
        <MDBCol lg="8">
          <MDBCard className="my-5 rounded-3" style={{ maxWidth: "600px" }}>
            {/* <MDBCardImage src='https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-registration/img3.webp' className='w-100 rounded-top'  alt="Sample photo"/> */}

            <MDBCardBody className="px-5">
              <h3 className="mb-4 pb-2 pb-md-0 mb-md-5 px-md-2">
                Registration Info
              </h3>

              <MDBInput
                wrapperClass="mb-4"
                className="form__input"
                type="text"
                onChange={(e) =>
                  setUser((prevState) => ({
                    ...prevState,
                    name: e.target.value,
                  }))
                }
                id="name"
                placeholder="Name"
              />
              {/* <div className="username"> */}
              {/* <label className="form__label" for="name">Name </label> */}
              {/* <input className="form__input" type="text" onChange={(e) => setUser(prevState => ({ ...prevState, name: e.target.value }))} id="name" placeholder="Name"/> */}
              {/* </div> */}
              <MDBRow>
                <MDBCol md="6">
                  <MDBInput
                    wrapperClass="datepicker mb-4"
                    type="text"
                    id="username"
                    className="form__input"
                    onChange={(e) =>
                      setUser((prevState) => ({
                        ...prevState,
                        username: e.target.value,
                      }))
                    }
                    placeholder="Username"
                  />
                </MDBCol>
              </MDBRow>

              <MDBRow>
                <MDBCol md="6">
                  <MDBInput
                    className="form__input"
                    type="password"
                    id="password"
                    onChange={(e) =>
                      setUser((prevState) => ({
                        ...prevState,
                        password: e.target.value,
                      }))
                    }
                    placeholder="Password"
                  />
                </MDBCol>
              </MDBRow>

              <select
                  className="form__input"
                  onChange={(e) => {
                    const selectedOptions = Array.from(e.target.selectedOptions, (option) => Number(option.value));
                    setUser((prevState) => ({
                      ...prevState,
                      genres: selectedOptions,
                    }));
                  }}
                  id="genres"
                  placeholder="Genres"
                  multiple
                >
                    <option value={1}>
                    FICTION
                    </option>
                    <option  value={2}>
                    EDUCATION
                    </option>
                    <option value={3}>
                    HISTORY
                    </option>
                    <option  value={4}>
                    PHILOSOPHY
                    </option>
                    <option  value={5}>
                    CHILDREN
                    </option>

                </select>

              <MDBBtn
                onClick={() => handleSubmit()}
                type="submit"
                className="mb-4"
                size="lg"
              >
                Register
              </MDBBtn>
            </MDBCardBody>
          </MDBCard>
        </MDBCol>
      </MDBRow>
    </MDBContainer>
  );
}
