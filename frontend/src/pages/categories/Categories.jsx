import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";

import publicCategoryService from "../../services/publicCategoryService";
import publicProductService from "../../services/publicProductService";


function Categories() {

    const navigate = useNavigate();

    const { id } = useParams();


    // ==========================================
    // STATE
    // ==========================================

    const [categories, setCategories] = useState([]);

    const [products, setProducts] = useState([]);

    const [loading, setLoading] = useState(true);

    const [productLoading, setProductLoading] =
        useState(false);

    const [categoryError, setCategoryError] =
        useState("");

    const [productError, setProductError] =
        useState("");


    // ==========================================
    // FETCH ALL CATEGORIES
    // ==========================================

    const fetchCategories = async () => {

        try {

            setLoading(true);

            setCategoryError("");


            const response =
                await publicCategoryService.getAll();


            let categoryData = [];


            /*
             * Backend may return:
             *
             * []
             *
             * or
             *
             * {
             *     data: []
             * }
             *
             * or
             *
             * {
             *     categories: []
             * }
             *
             * or
             *
             * {
             *     content: []
             * }
             */


            if (Array.isArray(response)) {

                categoryData = response;

            } else if (
                Array.isArray(response?.data)
            ) {

                categoryData = response.data;

            } else if (
                Array.isArray(response?.categories)
            ) {

                categoryData = response.categories;

            } else if (
                Array.isArray(response?.content)
            ) {

                categoryData = response.content;

            }


            setCategories(categoryData);

        } catch (error) {

            console.error(
                "Failed to load categories:",
                error
            );


            setCategories([]);


            setCategoryError(
                error.response?.data?.message ||
                "Unable to load categories. Please try again."
            );

        } finally {

            setLoading(false);
        }
    };


    // ==========================================
    // FETCH PRODUCTS
    // ==========================================

    const fetchProducts = async () => {

        try {

            setProductLoading(true);

            setProductError("");


            const response =
                await publicProductService.getAll();


            let productData = [];


            /*
             * Handle different possible
             * ApiResponse structures.
             */

            if (Array.isArray(response)) {

                productData = response;

            } else if (
                Array.isArray(response?.data)
            ) {

                productData = response.data;

            } else if (
                Array.isArray(response?.products)
            ) {

                productData = response.products;

            } else if (
                Array.isArray(response?.content)
            ) {

                productData = response.content;

            }


            /*
             * If category ID exists,
             * show products belonging
             * to that category only.
             */

            if (id) {

                productData =
                    productData.filter(
                        (product) =>
                            String(product.categoryId) ===
                            String(id)
                    );
            }


            setProducts(productData);

        } catch (error) {

            console.error(
                "Failed to load products:",
                error
            );


            setProducts([]);


            setProductError(
                error.response?.data?.message ||
                "Unable to load products. Please try again."
            );

        } finally {

            setProductLoading(false);
        }
    };


    // ==========================================
    // LOAD CATEGORIES
    // ==========================================

    useEffect(() => {

        fetchCategories();

    }, []);


    // ==========================================
    // LOAD PRODUCTS WHEN CATEGORY CHANGES
    // ==========================================

    useEffect(() => {

        if (id) {

            fetchProducts();

        } else {

            setProducts([]);

            setProductError("");

        }

    }, [id]);


    // ==========================================
    // CATEGORY CLICK
    // ==========================================

    const handleCategoryClick = (categoryId) => {

        navigate(
            `/categories/${categoryId}`
        );
    };


    // ==========================================
    // PRODUCT CLICK
    // ==========================================

    const handleProductClick = (productId) => {

        navigate(
            `/products/${productId}`
        );
    };


    // ==========================================
    // LOADING CATEGORIES
    // ==========================================

    if (loading) {

        return (

            <div className="categories-page">

                <div className="categories-header">

                    <span>
                        EXPLORE
                    </span>

                    <h1>
                        Shop by Category
                    </h1>

                    <p>
                        Discover products from our
                        categories
                    </p>

                </div>

                <p>
                    Loading categories...
                </p>

            </div>
        );
    }


    // ==========================================
    // CATEGORY ERROR
    // ==========================================

    if (categoryError) {

        return (

            <div className="categories-page">

                <div className="categories-header">

                    <span>
                        EXPLORE
                    </span>

                    <h1>
                        Shop by Category
                    </h1>

                    <p>
                        Discover products from our
                        categories
                    </p>

                </div>


                <div className="category-error">

                    {categoryError}

                </div>

            </div>
        );
    }


    // ==========================================
    // RENDER
    // ==========================================

    return (

        <div className="categories-page">


            {/* ==================================
                HEADER
            ================================== */}

            <div className="categories-header">

                <span>
                    EXPLORE
                </span>

                <h1>
                    Shop by Category
                </h1>

                <p>
                    Discover products from our
                    categories
                </p>

            </div>


            {/* ==================================
                CATEGORY GRID
            ================================== */}

            <div className="categories-grid">

                {categories.length === 0 ? (

                    <div className="category-empty">

                        No categories available.

                    </div>

                ) : (

                    categories.map((category) => (

                        <div
                            key={category.id}
                            className="category-card"
                            onClick={() =>
                                handleCategoryClick(
                                    category.id
                                )
                            }
                        >


                            {/* ==========================
                                CATEGORY IMAGE
                            ========================== */}

                            {category.imageUrl ? (

                                <img
                                    src={category.imageUrl}
                                    alt={category.name}
                                    className="category-image"
                                />

                            ) : (

                                <div className="category-image-placeholder">

                                    {category.name
                                        ?.charAt(0)
                                        ?.toUpperCase()}

                                </div>

                            )}


                            {/* ==========================
                                CATEGORY CONTENT
                            ========================== */}

                            <div className="category-content">

                                <h2>
                                    {category.name}
                                </h2>


                                {category.description && (

                                    <p>
                                        {
                                            category.description
                                        }
                                    </p>

                                )}


                                <span>
                                    Shop Now →
                                </span>

                            </div>

                        </div>

                    ))

                )}

            </div>


            {/* ==================================
                PRODUCTS FOR SELECTED CATEGORY
            ================================== */}

            {id && (

                <section className="category-products">


                    {/* ==========================
                        SELECTED CATEGORY TITLE
                    ========================== */}

                    <h2>
                        Products in this Category
                    </h2>


                    {/* ==========================
                        PRODUCT ERROR
                    ========================== */}

                    {productError ? (

                        <div className="category-error">

                            {productError}

                        </div>

                    ) : productLoading ? (

                        /* ==========================
                           PRODUCT LOADING
                        ========================== */

                        <p>
                            Loading products...
                        </p>

                    ) : products.length === 0 ? (

                        /* ==========================
                           NO PRODUCTS
                        ========================== */

                        <p>
                            No products available
                            in this category.
                        </p>

                    ) : (

                        /* ==========================
                           PRODUCTS GRID
                        ========================== */

                        <div className="products-grid">

                            {products.map((product) => (

                                <div
                                    key={product.id}
                                    className="product-card"
                                    onClick={() =>
                                        handleProductClick(
                                            product.id
                                        )
                                    }
                                >

                                    <div className="product-card-content">


                                        {/* PRODUCT NAME */}

                                        <h3>
                                            {product.name}
                                        </h3>


                                        {/* PRODUCT DESCRIPTION */}

                                        {product.description && (

                                            <p>
                                                {
                                                    product.description
                                                }
                                            </p>

                                        )}


                                        {/* PRODUCT PRICE */}

                                        <strong>
                                            ₹
                                            {product.price}
                                        </strong>


                                    </div>

                                </div>

                            ))}

                        </div>

                    )}

                </section>

            )}

        </div>
    );
}


export default Categories;