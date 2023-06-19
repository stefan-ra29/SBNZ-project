import axios from 'axios'

const apiURL = "http://localhost:8080/api/user";


export function createUser(setUser) {
    axios.post(apiURL, setUser)
    .then((response) => {
        // setUser(response.data)
    })
    .catch((error) => {
        console.log(error);
    })    
}