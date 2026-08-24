import { useEffect, useState } from "react";
import productService from "../../../services/productService";
import categoryService from "../../../services/categoryService";

import AddProductModal from "./AddProductModal";
import ProductUpdateModal from "./ProductUpdateModal";

function ProductPage() {

    // ==============================
    // STATE
    // ==============================

    const [products, setProducts] = useState([]);
    const [categories, setCategories] = useState([]);

    const [loading, setLoading] = useState(true);
    const [addLoading, setAddLoading] = useState(false);
    const [updateLoading, setUpdateLoading] = useState(false);

    const [showAddModal, setShowAddModal] = useState(false);
    const [showUpdateModal, setShowUpdateModal] = useState(false);

    const [selectedProduct, setSelectedProduct] = useState(null);

    const [error, setError] = useState("");


    // ==============================
    // LOAD PRODUCTS
    // ==============================

    const loadProducts = async () => {

        try {

            setLoading(true);
            setError("");

            const response = await productService.getAll();

            /*
             * productService.getAll()
             * already returns response.data.
             *
             * So DO NOT use response.data here.
             */

            let productData = response;

            // Handle different possible backend response formats
            if (Array.isArray(response)) {

                productData = response;

            } else if (Array.isArray(response?.data)) {

                productData = response.data;

            } else if (Array.isArray(response?.products)) {

                productData = response.products;

            } else if (Array.isArray(response?.content)) {

                productData = response.content;

            } else {

                productData = [];

            }

            setProducts(productData);

        } catch (error) {

            console.error(
                "Failed to load products:",
                error
            );

            setProducts([]);

            setError(
                error.response?.data?.message ||
                "Failed to load products."
            );

        } finally {

            setLoading(false);

        }
    };


    // ==============================
    // LOAD PRODUCTS ON PAGE LOAD
    // ==============================

    useEffect(() => {

        loadProducts();

    }, []);


    // ==============================
    // OPEN ADD PRODUCT MODAL
    // ==============================

    const handleOpenAddProduct = async () => {

        try {

            setError("");

            const response =
                await categoryService.getAll();

            let categoryData = response;

            if (Array.isArray(response)) {

                categoryData = response;

            } else if (Array.isArray(response?.data)) {

                categoryData = response.data;

            } else if (Array.isArray(response?.categories)) {

                categoryData = response.categories;

            } else {

                categoryData = [];

            }

            const activeCategories =
                categoryData.filter(
                    (category) =>
                        category.active === true
                );

            setCategories(activeCategories);

            setShowAddModal(true);

        } catch (error) {

            console.error(
                "Failed to load categories:",
                error
            );

            setError(
                error.response?.data?.message ||
                "Failed to load categories."
            );
        }
    };


    // ==============================
    // ADD PRODUCT
    // ==============================

    const handleAddProduct = async (product) => {

        try {

            setAddLoading(true);
            setError("");

            await productService.create(product);

            setShowAddModal(false);

            await loadProducts();

        } catch (error) {

            console.error(
                "Failed to create product:",
                error
            );

            const message =
                error.response?.data?.message ||
                "Failed to create product.";

            setError(message);

        } finally {

            setAddLoading(false);

        }
    };


    // ==============================
    // OPEN EDIT PRODUCT MODAL
    // ==============================

    const handleOpenEditProduct = async (product) => {

        try {

            setError("");

            const response =
                await categoryService.getAll();

            let categoryData = response;

            if (Array.isArray(response)) {

                categoryData = response;

            } else if (Array.isArray(response?.data)) {

                categoryData = response.data;

            } else if (Array.isArray(response?.categories)) {

                categoryData = response.categories;

            } else {

                categoryData = [];

            }

            const activeCategories =
                categoryData.filter(
                    (category) =>
                        category.active === true
                );

            setCategories(activeCategories);

            setSelectedProduct(product);

            setShowUpdateModal(true);

        } catch (error) {

            console.error(
                "Failed to load categories:",
                error
            );

            setError(
                error.response?.data?.message ||
                "Failed to load categories."
            );
        }
    };


    // ==============================
    // UPDATE PRODUCT
    // ==============================

    const handleUpdateProduct = async (product) => {

        try {

            setUpdateLoading(true);
            setError("");

            if (!selectedProduct) {
                setError("No product selected.");
                return;
            }

            await productService.update(
                selectedProduct.id,
                product
            );

            setShowUpdateModal(false);
            setSelectedProduct(null);

            await loadProducts();

        } catch (error) {

            console.error(
                "Failed to update product:",
                error
            );

            const message =
                error.response?.data?.message ||
                "Failed to update product.";

            setError(message);

        } finally {

            setUpdateLoading(false);

        }
    };


    // ==============================
    // DELETE PRODUCT
    // ==============================

    const handleDeleteProduct = async (product) => {

        const confirmed =
            window.confirm(
                `Are you sure you want to delete "${product.name}"?`
            );

        if (!confirmed) {
            return;
        }

        try {

            setError("");

            await productService.delete(
                product.id
            );

            await loadProducts();

        } catch (error) {

            console.error(
                "Failed to delete product:",
                error
            );

            const message =
                error.response?.data?.message ||
                "Failed to delete product.";

            setError(message);
        }
    };


    // ==============================
    // CLOSE UPDATE MODAL
    // ==============================

    const handleCloseUpdateModal = () => {

        setShowUpdateModal(false);
        setSelectedProduct(null);

    };


    // ==============================
    // RENDER
    // ==============================

    return (
        <div className="admin-page">

            {/* ==============================
                PAGE HEADER
            ============================== */}

            <div className="page-header">

                <div>

                    <h1>
                        Products
                    </h1>

                    <p>
                        Manage your products
                    </p>

                </div>

                <button
                    className="primary-button"
                    onClick={handleOpenAddProduct}
                >
                    + Add Product
                </button>

            </div>


            {/* ==============================
                ERROR MESSAGE
            ============================== */}

            {error && (

                <div className="error-message">
                    {error}
                </div>

            )}


            {/* ==============================
                LOADING
            ============================== */}

            {loading ? (

                <p>
                    Loading products...
                </p>

            ) : products.length === 0 ? (

                <p>
                    No products available.
                </p>

            ) : (

                /* ==============================
                   PRODUCTS TABLE
                ============================== */

                <div className="table-container">

                    <table>

                        <thead>

                        <tr>

                            <th>
                                ID
                            </th>

                            <th>
                                Name
                            </th>

                            <th>
                                Category
                            </th>

                            <th>
                                Price
                            </th>

                            <th>
                                SKU
                            </th>

                            <th>
                                Status
                            </th>

                            <th>
                                Actions
                            </th>

                        </tr>

                        </thead>


                        <tbody>

                        {products.map((product) => (

                            <tr
                                key={product.id}
                            >

                                <td>
                                    #{product.id}
                                </td>


                                <td>
                                    {product.name}
                                </td>


                                <td>
                                    {product.categoryName ||
                                        product.category?.name ||
                                        "-"}
                                </td>


                                <td>
                                    ₹{product.price}
                                </td>


                                <td>
                                    {product.sku}
                                </td>


                                <td>

                                    {product.active
                                        ? "Active"
                                        : "Inactive"}

                                </td>


                                <td>

                                    <button
                                        onClick={() =>
                                            handleOpenEditProduct(
                                                product
                                            )
                                        }
                                    >
                                        Edit
                                    </button>


                                    <button
                                        onClick={() =>
                                            handleDeleteProduct(
                                                product
                                            )
                                        }
                                    >
                                        Delete
                                    </button>

                                </td>

                            </tr>

                        ))}

                        </tbody>

                    </table>

                </div>

            )}


            {/* ==============================
                ADD PRODUCT MODAL
            ============================== */}

            {showAddModal && (

                <AddProductModal

                    categories={categories}

                    onClose={() =>
                        setShowAddModal(false)
                    }

                    onSubmit={handleAddProduct}

                    loading={addLoading}

                />

            )}


            {/* ==============================
                UPDATE PRODUCT MODAL
            ============================== */}

            {showUpdateModal &&
                selectedProduct && (

                    <ProductUpdateModal

                        product={selectedProduct}

                        categories={categories}

                        onClose={
                            handleCloseUpdateModal
                        }

                        onSubmit={
                            handleUpdateProduct
                        }

                        loading={updateLoading}

                    />

                )}

        </div>
    );
}

export default ProductPage;