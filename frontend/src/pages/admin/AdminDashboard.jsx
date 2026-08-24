import AdminSidebar from "../../components/layout/AdminSidebar";
import AdminNavbar from "../../components/layout/AdminNavbar";

function AdminDashboard() {
    return (
        <div className="admin-layout">

            <AdminSidebar />

            <div className="admin-main">

                <AdminNavbar />

                <main className="dashboard-content">

                    <h1>Dashboard</h1>
                    <p className="dashboard-subtitle">
                        Welcome to the Shiwansh E-Commerce Admin Panel
                    </p>

                    <div className="dashboard-cards">

                        <div className="dashboard-card">
                            <h3>Total Products</h3>
                            <p>0</p>
                        </div>

                        <div className="dashboard-card">
                            <h3>Total Categories</h3>
                            <p>0</p>
                        </div>

                        <div className="dashboard-card">
                            <h3>Total Orders</h3>
                            <p>0</p>
                        </div>

                        <div className="dashboard-card">
                            <h3>Total Users</h3>
                            <p>0</p>
                        </div>

                    </div>

                </main>

            </div>

        </div>
    );
}

export default AdminDashboard;