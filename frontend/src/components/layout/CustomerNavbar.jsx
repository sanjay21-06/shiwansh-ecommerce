import { Link } from "react-router-dom";

function CustomerNavbar() {
    return (
        <nav className="customer-navbar">

            <div className="customer-logo">
                <Link to="/">
                    SHIWANSH
                </Link>
            </div>

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
                    Login
                </Link>

            </div>

        </nav>
    );
}

export default CustomerNavbar;