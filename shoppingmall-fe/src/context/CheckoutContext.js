import { createContext, useReducer, useEffect } from "react";

const initialState = {
  items: JSON.parse(localStorage.getItem("checkoutItems")) || []
};

const checkoutReducer = (state, action) => {
  switch (action.type) {
    case "SET_ITEMS":
      return { ...state, items: action.payload };
    case "CLEAR_ITEMS":
      return { ...state, items: [] };
    default:
      return state;
  }
};

export const CheckoutContext = createContext();

export const CheckoutProvider = ({ children }) => {
  const [checkoutState, dispatch] = useReducer(checkoutReducer, initialState);

  useEffect(() => {
    localStorage.setItem("checkoutItems", JSON.stringify(checkoutState.items));
  }, [checkoutState.items]);

  return (
    <CheckoutContext.Provider value={{ checkoutState, dispatch }}>
      {children}
    </CheckoutContext.Provider>
  );
};
