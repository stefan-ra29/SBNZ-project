import { toast } from "react-toastify";
import { getAxios } from "../utils/AxiosWrapper";

const apiURL = "http://localhost:8080/api/transaction";

export function transaction(dto) {
  getAxios()
    .post(apiURL, dto)
    .then((response) => {
      toast.success(response.data, {
        position: "top-right",
      });
    })
    .catch((error) => {
      toast.error(error.response.data.message);
    });
}
