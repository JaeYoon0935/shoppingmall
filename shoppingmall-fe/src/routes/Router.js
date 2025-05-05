import { Routes, Route } from 'react-router-dom';
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
import Layout from '../shop/layouts/Layout';
import Login from '../auth/Login';
import SignUp from '../auth/SignUp';
import { AuthProvider } from "../context/AuthContext";

function Router() {

{/* 추후에 adminRoutes.js / shopRoutes.js 분리하고 해당 라우터에는 두 라우터 import 하는 것만 남기기.. */}

  return (
    <AuthProvider>
      <Routes>

        {/* 로그인 / 회원가입 페이지 (로그인 인증 없이 접근가능) */}
        <Route path="/login" element={<Login />} />
        <Route path="/signUp" element={<SignUp />} />

        {/* 쇼핑몰 페이지*/}
        <Route path="/" element={<Layout />} >
          <Route index element={<ShopMain />} />
          <Route path="category/:id" element={<Category />} />
          <Route path="productDetail/:id" element={<ProductDetail />} />
        </Route>

        {/* 관리자 페이지 */}
        <Route path="/admin" element={<AdminLayout />}>
          <Route index element={<AdminHome />} />
          <Route path="user-info" element={<UserInfo />} />
          <Route path="user-edit/:id" element={<UserEdit />}/>
          <Route path="order-history" element={<OrderHistory />} />
          <Route path="product-management" element={<ProductManagement />} />
          <Route path="product-registration" element={<ProductRegistration />} />
          <Route path="product-edit/:id" element={<ProductEdit /> }/>
        </Route>
        
      </Routes>
    </AuthProvider>
  );
}

export default Router;