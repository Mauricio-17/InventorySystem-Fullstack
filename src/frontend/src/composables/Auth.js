import fetch from "unfetch";

const URL = "api/login";

const checkStatus = (response) => {
  if (response.ok) {
    return response;
  }
  // convert non-2xx HTTP responses into errors:
  const error = new Error(response.statusText);
  error.response = response;
  return Promise.reject(error);
};

export const login = (employee) =>
  fetch(URL, {
    headers: {
      "Content-Type": "application/json",
    },
    method: "POST",
    body: JSON.stringify(employee),
  }).then(checkStatus);

export const logOut = () => {
  
}
