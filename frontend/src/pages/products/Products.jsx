import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import CustomerNavbar from "../../components/layout/CustomerNavbar";
import productService from "../../services/productService";

function Products() {

    const navigate = useNavigate();

    const [products, setProducts] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState("");


    const fetchProducts = async () => {

        try {

            setLoading(true);
            setError("");

            const response = await productService.getAll();

            /*
             * Backend response can be:
             * { data: [...] }
             * or directly [...]
             */
            const productData = Array.isArray(response)
                ? response
                : response?.data || [];

            /*
             * Customer should only see active products.
             */
            const activeProducts = productData.filter(
                (product) =>
                    product.active === true ||
                    product.status === "ACTIVE" ||
                    product.status === "Active"
            );

            setProducts(activeProducts);

        } catch (error) {

            console.error("Failed to load products:", error);

            setError(
                "Unable to load products. Please try again."
            );

        } finally {

            setLoading(false);

        }
    };

    useEffect(() => {
        fetchProducts();
    }, []);

    return (
        <div className="products-page">

            <CustomerNavbar />

            {/* =========================
                PRODUCTS HEADER
            ========================= */}

            <section className="products-header">

                <p>SHOP</p>

                <h1>
                    Our Products
                </h1>

                <span>
                    Discover products available in our store
                </span>

            </section>


            {/* =========================
                PRODUCTS SECTION
            ========================= */}

            <section className="products-section">

                {loading && (
                    <div className="category-loading">
                        Loading products...
                    </div>
                )}


                {error && (
                    <div className="error-message">
                        {error}
                    </div>
                )}


                {!loading && !error && products.length === 0 && (
                    <div className="category-empty">
                        No products available at the moment.
                    </div>
                )}


                {!loading && !error && products.length > 0 && (

                    <div className="products-grid">

                        {products.map((product) => (

                            <div
                                className="product-card"
                                key={product.id}
                            >

                                <div className="product-card-content">

                                    <p className="product-label">
                                        Product
                                    </p>

                                    <span className="product-category">
                                        {product.categoryName ||
                                            product.category?.name ||
                                            product.category ||
                                            "General"}
                                    </span>

                                    <h2>
                                        {product.name}
                                    </h2>

                                    <p className="product-description">
                                        {product.description ||
                                            "Discover this product from our store."}
                                    </p>

                                    <div className="product-bottom">

                                        <strong>
                                            ₹
                                            {Number(
                                                product.price || 0
                                            ).toLocaleString("en-IN", {
                                                minimumFractionDigits: 0,
                                                maximumFractionDigits: 2
                                            })}
                                        </strong>

                                        <button
                                            type="button"
                                            className="view-product-button"
                                            onClick={() => navigate(`/products/${product.id}`)}
                                        >
                                            View Product
                                        </button>

                                    </div>

                                </div>

                            </div>

                        ))}

                    </div>

                )}

            </section>

        </div>
    );
}

export default Products;