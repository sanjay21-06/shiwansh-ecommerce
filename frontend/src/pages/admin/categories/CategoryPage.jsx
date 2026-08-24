import { useEffect, useState } from "react";

import categoryService from "../../../services/categoryService";

import CategoryTable from "./components/CategoryTable";
import CategoryModal from "./components/CategoryModal";


function CategoryPage() {

    // ==============================
    // STATE
    // ==============================

    const [categories, setCategories] = useState([]);

    const [loading, setLoading] = useState(true);

    const [error, setError] = useState("");

    const [isModalOpen, setIsModalOpen] =
        useState(false);

    const [editingCategory, setEditingCategory] =
        useState(null);

    const [deleteLoading, setDeleteLoading] =
        useState(false);


    // ==============================
    // FETCH CATEGORIES
    // ==============================

    const fetchCategories = async () => {

        try {

            setLoading(true);

            setError("");

            const response =
                await categoryService.getAll();


            /*
             * categoryService.getAll()
             * already returns response.data.
             *
             * Therefore normally `response`
             * itself should be the category array.
             *
             * The additional checks make this
             * component safe for different
             * backend response structures.
             */

            let categoryData = response;


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

            } else {

                categoryData = [];

            }


            setCategories(categoryData);

        } catch (error) {

            console.error(
                "Failed to load categories:",
                error
            );

            setCategories([]);

            setError(
                error.response?.data?.message ||
                "Unable to load categories. Please try again."
            );

        } finally {

            setLoading(false);
        }
    };


    // ==============================
    // LOAD CATEGORIES ON PAGE LOAD
    // ==============================

    useEffect(() => {

        fetchCategories();

    }, []);


    // ==============================
    // OPEN ADD CATEGORY MODAL
    // ==============================

    const handleAddCategory = () => {

        setEditingCategory(null);

        setIsModalOpen(true);
    };


    // ==============================
    // OPEN EDIT CATEGORY MODAL
    // ==============================

    const handleEdit = (category) => {

        setEditingCategory(category);

        setIsModalOpen(true);
    };


    // ==============================
    // CREATE CATEGORY
    // ==============================

    const handleCreateCategory = async (
        category
    ) => {

        try {

            setError("");

            await categoryService.create(
                category
            );

            setIsModalOpen(false);

            setEditingCategory(null);

            await fetchCategories();

        } catch (error) {

            console.error(
                "Failed to create category:",
                error
            );

            const message =
                error.response?.data?.message ||
                "Failed to create category.";

            setError(message);
        }
    };


    // ==============================
    // UPDATE CATEGORY
    // ==============================

    const handleUpdateCategory = async (
        id,
        category
    ) => {

        try {

            setError("");

            await categoryService.update(
                id,
                category
            );

            setIsModalOpen(false);

            setEditingCategory(null);

            await fetchCategories();

        } catch (error) {

            console.error(
                "Failed to update category:",
                error
            );

            const message =
                error.response?.data?.message ||
                "Failed to update category.";

            setError(message);
        }
    };


    // ==============================
    // DELETE CATEGORY
    // ==============================

    const handleDelete = async (
        category
    ) => {

        const confirmed =
            window.confirm(
                `Are you sure you want to delete "${category.name}"?`
            );


        if (!confirmed) {

            return;
        }


        try {

            setDeleteLoading(true);

            setError("");


            await categoryService.delete(
                category.id
            );


            await fetchCategories();

        } catch (error) {

            console.error(
                "Failed to delete category:",
                error
            );


            const message =
                error.response?.data?.message ||
                "Failed to delete category.";


            setError(message);

        } finally {

            setDeleteLoading(false);
        }
    };


    // ==============================
    // CLOSE MODAL
    // ==============================

    const handleCloseModal = () => {

        setIsModalOpen(false);

        setEditingCategory(null);
    };


    // ==============================
    // RENDER
    // ==============================

    return (

        <div className="category-page">


            {/* ==============================
                HEADER
            ============================== */}

            <div className="category-header">

                <div>

                    <h1>
                        Categories
                    </h1>

                    <p>
                        Manage product categories
                    </p>

                </div>


                <button
                    className="add-category-button"
                    onClick={
                        handleAddCategory
                    }
                    disabled={deleteLoading}
                >
                    + Add Category
                </button>

            </div>


            {/* ==============================
                ERROR
            ============================== */}

            {error && (

                <div className="category-error">

                    {error}

                </div>

            )}


            {/* ==============================
                LOADING
            ============================== */}

            {loading ? (

                <div className="category-loading">

                    Loading categories...

                </div>

            ) : categories.length === 0 ? (

                <div className="category-loading">

                    No categories available.

                </div>

            ) : (

                <CategoryTable

                    categories={categories}

                    onEdit={handleEdit}

                    onDelete={handleDelete}

                />

            )}


            {/* ==============================
                CATEGORY MODAL
            ============================== */}

            <CategoryModal

                isOpen={isModalOpen}

                onClose={
                    handleCloseModal
                }

                onCreated={
                    handleCreateCategory
                }

                onUpdated={
                    handleUpdateCategory
                }

                editingCategory={
                    editingCategory
                }

            />

        </div>
    );
}


export default CategoryPage;