import Header from '../components/Header';
import { useEffect, useState } from 'react';
import { Outlet } from 'react-router-dom';
import api from '../../api/apiClient';

function Layout() {

  const [categories, setCategories] = useState([]);

  useEffect(() => {
     api.get("/products/categories")
     .then(response => {
          setCategories(response.data)
      }).catch(error => {
          console.error("카테고리 불러오기 실패", error);
      })
  }, [])

  return (
    <div>
      <Header categories={categories} />
      <Outlet context={{categories}} />
    </div>
  );
}

export default Layout;