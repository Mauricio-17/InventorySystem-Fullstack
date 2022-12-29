import fetch from "unfetch";

const URL = "api/area";

const getHeaders = () => {
  return {
    Accept: "application/json",
    "Content-Type": "application/json",
    Authorization: localStorage.getItem("token"),
  };
};

const checkStatus = (response) => {
  if (response.ok) {
    return response;
  }
  // convert non-2xx HTTP responses into errors:
  const error = new Error(response.statusText);
  error.response = response;
  return Promise.reject(error);
};

export const getAllAreas = () =>
  fetch(`${URL}`, {
    headers: getHeaders(),
    method: "GET",
  }).then(checkStatus);

export const addNewArea = (area) =>
  fetch(URL, {
    headers: getHeaders(),
    method: "POST",
    body: JSON.stringify(area),
  }).then(checkStatus);

export const updateArea = (area, id) =>
  fetch(`${URL}/${id}`, {
    headers: getHeaders(),
    method: "PUT",
    body: JSON.stringify(area),
  }).then(checkStatus);

export const removeArea = (areaId) =>
  fetch(`${URL}/${areaId}`, {
    headers: getHeaders(),
    method: "DELETE",
  }).then(checkStatus);


