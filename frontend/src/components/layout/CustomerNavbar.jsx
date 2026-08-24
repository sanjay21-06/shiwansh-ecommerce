import { Link } from "react-router-dom";

function CustomerNavbar() {

    return (
        <nav className="customer-navbar">

            {/* LOGO */}

            <div className="customer-logo">
                <Link to="/">
                    SHIWANSH
                </Link>
            </div>


            {/* NAVIGATION */}

            <div className="customer-nav-links">

                <Link to="/">
                    Home
                </Link>

                <Link to="/products">
                    Products
                </Link>

                <Link to="/categories">
                    Categories
                </Link>

                <Link to="/login">
                    Customer Login
                </Link>

                <Link to="/register">
                    Register
                </Link>

                <Link to="/admin/login">
                    Admin Login
                </Link>

            </div>

        </nav>
    );
}

export default CustomerNavbar;