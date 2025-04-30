import Header from '../components/Header';
import Sidebar from '../components/Sidebar';
import { Outlet } from 'react-router-dom';
import { useState } from 'react';

function AdminLayout() {
  const [isSidebarOpen, setIsSidebarOpen] = useState(true);

  return (
    <div className="flex min-h-screen">
      {isSidebarOpen && <Sidebar />}
      <div className="flex-1 flex flex-col">
        <Header toggleSidebar={() => setIsSidebarOpen(prev => !prev)} />
        <main className="p-6 bg-gray-50 flex-1">
          <Outlet />
        </main>
      </div>
    </div>
  );
}

export default AdminLayout;