function AdminSidebar() {
    return (
        <aside className="admin-sidebar">

            <div className="sidebar-logo">
                <h2>Shiwansh</h2>
                <span>Admin Panel</span>
            </div>

            <nav className="sidebar-nav">

                <a href="/admin">Dashboard</a>

                <a href="/admin/categories">Categories</a>

                <a href="/admin/products">Products</a>

                <a href="#">Orders</a>

                <a href="#">Users</a>

                <a href="#">Payments</a>

            </nav>

            <div className="sidebar-bottom">
                <a href="/">Logout</a>
            </div>

        </aside>
    );
}

export default AdminSidebar;