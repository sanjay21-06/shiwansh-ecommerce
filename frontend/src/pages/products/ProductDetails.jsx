import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import CustomerNavbar from "../../components/layout/CustomerNavbar";
import productService from "../../services/productService";

function ProductDetails() {

    const { id } = useParams();
    const navigate = useNavigate();

    const [product, setProduct] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState("");

    const fetchProduct = async () => {

        try {

            setLoading(true);
            setError("");

            const response =
                await productService.getActiveProduct(id);

            const productData =
                response?.data || response;

            setProduct(productData);

        } catch (error) {

            console.error(
                "Failed to load product:",
                error
            );

            setError(
                "Product not found or unavailable."
            );

        } finally {

            setLoading(false);

        }
    };

    useEffect(() => {
        fetchProduct();
    }, [id]);

    return (
        <div className="product-details-page">

            <CustomerNavbar />

            <section className="product-details-section">

                {loading && (
                    <div className="category-loading">
                        Loading product...
                    </div>
                )}

                {error && (
                    <div className="error-message">
                        {error}
                    </div>
                )}

                {!loading &&
                    !error &&
                    product && (

                        <div className="product-details-card">

                            <div className="product-details-content">

                                <p className="product-label">
                                    PRODUCT
                                </p>

                                <span className="product-category">
                                    {product.categoryName ||
                                        product.category?.name ||
                                        product.category ||
                                        "General"}
                                </span>

                                <h1>
                                    {product.name}
                                </h1>

                                <p className="product-details-description">
                                    {product.description ||
                                        "Discover this product from our store."}
                                </p>

                                <strong className="product-details-price">
                                    ₹
                                    {Number(
                                        product.price || 0
                                    ).toLocaleString(
                                        "en-IN",
                                        {
                                            minimumFractionDigits: 0,
                                            maximumFractionDigits: 2
                                        }
                                    )}
                                </strong>

                                <div className="product-details-actions">

                                    <button
                                        type="button"
                                        className="back-products-button"
                                        onClick={() =>
                                            navigate("/products")
                                        }
                                    >
                                        ← Back to Products
                                    </button>

                                    <button
                                        type="button"
                                        className="add-cart-button"
                                        onClick={() =>
                                            console.log(
                                                "Add to cart:",
                                                product
                                            )
                                        }
                                    >
                                        Add to Cart
                                    </button>

                                </div>

                            </div>

                        </div>
                    )}

            </section>

        </div>
    );
}

export default ProductDetails;