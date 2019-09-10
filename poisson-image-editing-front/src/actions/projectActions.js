import axios from "axios";
import { GET_ERRORS } from "./types";

export const fileUploader = (
  background,
  insertimage,
  mask,
  history
) => async dispatch => {
  try {
    const res = await axios.post(
      "http://localhost:8080/upload",
      background,
      insertimage,
      mask
    );
    history.push("/sucessful");
  } catch (err) {
    dispatch({
      type: GET_ERRORS,
      payload: err.response.data
    });
  }
};
