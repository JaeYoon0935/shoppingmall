import api from "../api/apiClient";
import { createContext, useReducer, useEffect, useState, useContext } from "react";
import { AuthContext } from "./AuthContext";

export const CartContext = createContext();

const initialState = {
  cartItems: []
};

const cartReducer = (state, action) => {
  switch (action.type) {

    case "SET_CART":
      return { ...state, cartItems: action.payload };

    case "ADD_ITEM":
      const exist = state.cartItems.find(item => item.id === action.payload.id);
      if (exist) {
        return { // 이미 카트에 존재하는 상품은 수량만 update
          ...state,
          cartItems: state.cartItems.map(item =>
            item.id === action.payload.id
              ? { ...item, quantity: item.quantity + action.payload.quantity }
              : item
          )
        };
      } else {
        return { // 카트에 없으면 추가
          ...state,
          cartItems: [...state.cartItems, action.payload]
        };
      }

    case "UPDATE_ITEM": // 장바구니에서 직접 수량 변경 시 사용 (이미 존재하는 상품 수량 update)
      return {
        ...state,
        cartItems: state.cartItems.map(item =>
          item.id === action.payload.id
            ? { ...item, quantity: action.payload.quantity }
            : item
        )
      };

    case "REMOVE_ITEM":
      return {
        ...state,
        cartItems: state.cartItems.filter(item => item.id !== action.payload.id)
      };

    case "CLEAR_CART":
      return { ...state, cartItems: [] };

    default:
      return state;
  }
};

export const CartProvider = ({ children }) => {
  const [cartState, dispatch] = useReducer(cartReducer, initialState);
  const { userInfo } = useContext(AuthContext);
  const [initialized, setInitialized] = useState(false);

  // 최초 렌더링 시 localStorage에서 불러오기 (비회원)
  useEffect(() => {
    if (!userInfo.token) {
      const localItems = JSON.parse(localStorage.getItem("cartItems")) || [];
      dispatch({ type: "SET_CART", payload: localItems });
    }
    setInitialized(true);
  }, [userInfo.token]);

  // 비회원일 때는 localStorage에 저장
  useEffect(() => {
    if(!userInfo.token && initialized) {
      localStorage.setItem("cartItems", JSON.stringify(cartState.cartItems));
    }
  }, [cartState.cartItems, userInfo.token, initialized]);

  // 로그인 시 장바구니 병합
  useEffect(() => {
    const mergeCart = async () => {
      try {
        const localItems = JSON.parse(localStorage.getItem("cartItems")) || [];

        // 병합할 로컬 장바구니가 없으면 병합 로직 패스
        if (localItems.length === 0) {
          localStorage.setItem("cartMerged", "true");
          return;
        }

        // 서버 전송용 payload 생성
        const requestItems = localItems.map(item => ({
          productId: item.id,
          quantity: item.quantity
        }));

        // 병합된 장바구니 서버에 저장
        await api.post('/cart', requestItems);

        // 병합 후 서버에서 최신 장바구니 다시 조회
        const updated = await api.get('/cart');
        const payload = updated.data.map(item => ({
          id : item.productId,
          quantity: item.quantity
        }));
        dispatch({ type: "SET_CART", payload: payload });

        // 병합 완료 후 로컬 데이터 정리 및 병합 플래그 저장
        localStorage.removeItem("cartItems");
        localStorage.setItem("cartMerged", "true");
      } catch (error) {
        console.error("장바구니 병합 실패:", error);
      }
    };

    const needCartMerge = userInfo.token && localStorage.getItem("cartItems") && localStorage.getItem("cartMerged") !== "true";

    if (needCartMerge) {
      mergeCart();
    }
  }, [userInfo.token]);

  // 로그인 상태에서 장바구니 조회 (병합이 이미 끝났을 경우)
  useEffect(() => {
    const fetchCart = async () => {
      
      try {
        const response = await api.get('/cart');
        const payload = response.data.map(item => ({
          id : item.productId,
          quantity: item.quantity
        }));
        dispatch({ type: "SET_CART", payload: payload });
      } catch (error) {
        console.error("장바구니 조회 실패:", error);
      }
    };

    const isLoggedIn = userInfo.token;
    const checkMerged = localStorage.getItem("cartMerged") === "true";

    if (isLoggedIn && checkMerged) {
      fetchCart();
    }
  }, [userInfo.token]);

  //로그아웃 혹은 토큰 만료 시 병합여부 제거
  useEffect(() => {
    if (!userInfo.token) {
      localStorage.removeItem("cartMerged");
    }
  }, [userInfo.token]);

  return (
    <CartContext.Provider value={{ cartState, dispatch }}>
      {children}
    </CartContext.Provider>
  );
};
