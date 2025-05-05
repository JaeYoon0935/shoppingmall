import React, { createContext, useReducer, useEffect } from "react";

const initialState = {
  name: localStorage.getItem("name") || null,
  email: localStorage.getItem("email") || null,
  token: localStorage.getItem("token") || null,
  roles: localStorage.getItem("roles") || null,
};

const authReducer = (userInfo, action) => {
  switch (action.type) {
    case "LOGIN":
      return { ...userInfo, name: action.payload.name, email: action.payload.email, token: action.payload.token, roles: action.payload.roles };
    case "LOGOUT":
      return { ...userInfo, name: null, email: null, token: null, roles: null };
    default:
      return userInfo;
  }
};

export const AuthContext = createContext();

export const AuthProvider = ({ children }) => {

  const [userInfo, dispatch] = useReducer(authReducer, initialState);

  useEffect(() => {
    if (userInfo.token) {
      localStorage.setItem("name", userInfo.name);
      localStorage.setItem("email", userInfo.email);
      localStorage.setItem("token", userInfo.token);
      localStorage.setItem("roles", userInfo.roles);
    } else {
      localStorage.removeItem("name");
      localStorage.removeItem("email");
      localStorage.removeItem("token");
      localStorage.removeItem("roles");
    }
  }, [userInfo.name, userInfo.email, userInfo.token, userInfo.roles]);

  // context api를 통해 하위 컴포넌트로 state와 dispatch 전달
  return (
    <AuthContext.Provider value={{ userInfo, dispatch }}>
      {children}
    </AuthContext.Provider>
  );

};