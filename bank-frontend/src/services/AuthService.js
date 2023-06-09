import { toast } from "react-toastify";
import { getAxios } from "../utils/AxiosWrapper";

const apiURL = "http://localhost:8080/api/auth";

export function login(dto) {
  getAxios()
    .post(apiURL, dto)
    .then((response) => {
      localStorage.setItem("token", response.data);
      window.location.replace("http://localhost:3000/");
      toast.success("Successfully logged in!", {
        position: "top-right",
      });
    })
    .catch((error) => {
      toast.error(error.response.data.message);
    });
}
