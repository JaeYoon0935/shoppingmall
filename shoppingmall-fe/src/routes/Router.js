import { Routes, Route } from 'react-router-dom';
import AdminRoute from '../routes/AdminRoute';
import AdminLayout from '../admin/layouts/AdminLayout';
import AdminHome from '../admin/pages/AdminHome';
import UserInfo from '../admin/pages/user/UserInfo';
import UserEdit from '../admin/pages/user/UserEdit';
import OrderHistory from '../admin/pages/orderHistory/OrderHistory';
import ProductManagement from '../admin/pages/product/ProductManagement';
import ProductRegistration from '../admin/pages/product/ProductRegistration';
import ProductEdit from '../admin/pages/product/ProductEdit';
import ShopMain from '../shop/pages/ShopMain';
import Category from '../shop/pages/Category';
import ProductDetail from '../shop/pages/ProductDetail';
import OrderComplete from '../shop/pages/OrderComplete';
import ProfileEdit from '../shop/pages/ProfileEdit';
import Checkout from '../shop/pages/Checkout';
import Orders from '../shop/pages/Orders';
import SearchResult from '../shop/pages/SearchResult';
import Layout from '../shop/layouts/Layout';
import Login from '../auth/Login';
import SignUp from '../auth/SignUp';
import { AuthProvider } from "../context/AuthContext";
import { CheckoutProvider } from "../context/CheckoutContext";
import Unauthorized from '../common/pages/Unauthorized';

function Router() {

{/* 추후에 adminRoutes.js / shopRoutes.js 분리하고 해당 라우터에는 두 라우터 import 하는 것만 남기기.. */}

  return (
    <AuthProvider>
      <CheckoutProvider>
        <Routes>

          {/* 로그인 / 회원가입 페이지 (로그인 인증 없이 접근가능) */}
          <Route path="/login" element={<Login />} />
          <Route path="/signUp" element={<SignUp />} />

          {/* 권한 없음 처리 페이지 */}
          <Route path="/unauthorized" element={<Unauthorized/>}/>

          {/* 쇼핑몰 페이지*/}
          <Route path="/" element={<Layout />} >
            <Route index element={<ShopMain />} />
            <Route path="category/:id" element={<Category />} />
            <Route path="product-detail/:id" element={<ProductDetail />} />
            <Route path="checkout" element={<Checkout />}/>
            <Route path="order-complete/:id" element={<OrderComplete />} />
            <Route path="profile-edit" element={<ProfileEdit />} />
            <Route path="orders" element={<Orders />} />
            <Route path="search" element={<SearchResult />} />
          </Route>

          {/* 관리자 페이지 */}
          <Route path="/admin" element={<AdminRoute />}>
            <Route element={<AdminLayout />}>
              <Route index element={<AdminHome />} />
              <Route path="user-info" element={<UserInfo />} />
              <Route path="user-edit/:id" element={<UserEdit />}/>
              <Route path="order-history" element={<OrderHistory />} />
              <Route path="product-management" element={<ProductManagement />} />
              <Route path="product-registration" element={<ProductRegistration />} />
              <Route path="product-edit/:id" element={<ProductEdit /> }/>
            </Route>
          </Route>
        </Routes>
      </CheckoutProvider>
    </AuthProvider>
  );
}

export default Router;