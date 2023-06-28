import { toast } from "react-toastify";
import { getAxios } from "../utils/AxiosWrapper";

const apiURL = "http://localhost:8080/api/transaction";

export async function transaction(dto) {
  let res = "";
  await getAxios()
    .post(apiURL, dto)
    .then((response) => {
      res = response.data.response;
      if (res == "Success") {
        toast.success(response.data.response);
      }
      return response.data.response;
    })
    .catch((error) => {
      toast.error(error.response.message);
    });
  return res;
}

export function transactionWithoutCheck(dto) {
  getAxios()
    .post(apiURL + "/without-check", dto)
    .then((response) => {
      toast.success(response.data.response);
      return response.data.response;
    })
    .catch((error) => {
      toast.error(error.response.data.message);
    });
}
