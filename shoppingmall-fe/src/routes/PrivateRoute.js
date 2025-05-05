import React, { useContext } from "react";
import { AuthContext } from "../context/AuthContext";
import { Navigate, Outlet } from "react-router-dom";

const PrivateRoute = () => {
  const { state } = useContext(AuthContext);
  return state.token ? <Outlet /> : <Navigate to="/login" />;
}

export default PrivateRoute;