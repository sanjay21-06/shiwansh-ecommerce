import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import CustomerNavbar from "../../components/layout/CustomerNavbar";
import categoryService from "../../services/categoryService";

function Home() {
    const [categories, setCategories] = useState([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        let cancelled = false;

        const fetchCategories = async () => {
            try {
                const response = await categoryService.getAll();

                const activeCategories = response.data.filter(
                    (category) => category.active === true
                );

                if (!cancelled) {
                    setCategories(activeCategories);
                }
            } catch (error) {
                console.error("Failed to load categories:", error);
            } finally {
                if (!cancelled) {
                    setLoading(false);
                }
            }
        };

        fetchCategories();

        return () => {
            cancelled = true;
        };
    }, []);

    return (
        <div className="home-page">

            <CustomerNavbar />

            {/* =========================
                HERO SECTION
            ========================= */}
            <section className="hero-section">

                <div className="hero-content">

                    <p className="hero-subtitle">
                        WELCOME TO SHIWANSH SHOP
                    </p>

                    <h1>
                        Everything You Need,
                        <br />
                        All in One Place.
                    </h1>

                    <p className="hero-description">
                        Discover quality products at great prices.
                        Shop easily and enjoy a simple shopping experience.
                    </p>

                    <div className="hero-actions">

                        <Link to="/products">
                            <button className="shop-now-button">
                                Shop Now
                            </button>
                        </Link>

                        <Link to="/login">
                            <button className="user-login-button">
                                User Login
                            </button>
                        </Link>

                    </div>

                </div>

            </section>


            {/* =========================
                LOGIN OPTIONS
            ========================= */}
            <section className="login-options-section">

                <div className="section-heading">

                    <p>GET STARTED</p>

                    <h2>
                        Choose Your Login
                    </h2>

                    <span>
                        Continue as a customer or manage the store as an administrator.
                    </span>

                </div>

                <div className="login-options">

                    {/* USER LOGIN */}
                    <div className="login-card user-login-card">

                        <div className="login-card-icon">
                            🛍️
                        </div>

                        <h3>
                            Customer Login
                        </h3>

                        <p>
                            Sign in to your account to browse products,
                            manage your cart and place orders.
                        </p>

                        <Link to="/login">
                            <button className="login-card-button">
                                Login as User
                            </button>
                        </Link>

                    </div>


                    {/* ADMIN LOGIN */}
                    <div className="login-card admin-login-card">

                        <div className="login-card-icon">
                            🔐
                        </div>

                        <h3>
                            Admin Login
                        </h3>

                        <p>
                            Access the administration dashboard to manage
                            products, categories and store operations.
                        </p>

                        <Link to="/admin/login">
                            <button className="login-card-button">
                                Login as Admin
                            </button>
                        </Link>

                    </div>

                </div>

            </section>


            {/* =========================
                CATEGORIES
            ========================= */}
            <section className="categories-section">

                <div className="section-heading">

                    <p>EXPLORE</p>

                    <h2>
                        Shop by Category
                    </h2>

                </div>

                {loading ? (

                    <p className="category-loading">
                        Loading categories...
                    </p>

                ) : categories.length === 0 ? (

                    <p className="category-empty">
                        No categories available.
                    </p>

                ) : (

                    <div className="category-cards">

                        {categories.map((category) => (

                            <div
                                className="category-card"
                                key={category.id}
                            >

                                <h3>
                                    {category.name}
                                </h3>

                                <p>
                                    {category.description}
                                </p>

                                <Link
                                    to={`/categories/${category.id}`}
                                >
                                    Explore
                                </Link>

                            </div>

                        ))}

                    </div>

                )}

            </section>

        </div>
    );
}

export default Home;