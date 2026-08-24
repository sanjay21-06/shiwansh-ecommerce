import { useEffect, useState } from "react";

function CategoryModal({
                           isOpen,
                           onClose,
                           onCreated,
                           onUpdated,
                           editingCategory
                       }) {
    const isEditMode = Boolean(editingCategory);

    const [name, setName] = useState("");
    const [description, setDescription] = useState("");
    const [active, setActive] = useState(true);

    const [error, setError] = useState("");
    const [loading, setLoading] = useState(false);

    useEffect(() => {
        if (editingCategory) {
            setName(editingCategory.name || "");
            setDescription(editingCategory.description || "");
            setActive(editingCategory.active);
        } else {
            setName("");
            setDescription("");
            setActive(true);
        }

        setError("");
    }, [editingCategory, isOpen]);

    if (!isOpen) {
        return null;
    }

    const handleSubmit = async (event) => {
        event.preventDefault();

        setError("");

        if (!name.trim()) {
            setError("Category name is required.");
            return;
        }

        if (name.trim().length > 100) {
            setError("Category name must not exceed 100 characters.");
            return;
        }

        if (description.length > 500) {
            setError("Description must not exceed 500 characters.");
            return;
        }

        const categoryData = {
            name: name.trim(),
            description: description.trim(),
            active
        };

        try {
            setLoading(true);

            if (isEditMode) {
                await onUpdated(
                    editingCategory.id,
                    categoryData
                );
            } else {
                await onCreated(categoryData);
            }

        } catch (error) {
            console.error(error);

            setError(
                error.response?.data?.message ||
                "Unable to save category."
            );
        } finally {
            setLoading(false);
        }
    };

    const handleClose = () => {
        if (loading) {
            return;
        }

        setName("");
        setDescription("");
        setActive(true);
        setError("");

        onClose();
    };

    return (
        <div
            className="category-modal-overlay"
            onClick={handleClose}
        >
            <div
                className="category-modal"
                onClick={(event) => event.stopPropagation()}
            >

                <div className="category-modal-header">

                    <div>
                        <h2>
                            {isEditMode
                                ? "Edit Category"
                                : "Add Category"}
                        </h2>

                        <p>
                            {isEditMode
                                ? "Update category information"
                                : "Create a new product category"}
                        </p>
                    </div>

                    <button
                        type="button"
                        className="modal-close-button"
                        onClick={handleClose}
                    >
                        ×
                    </button>

                </div>

                <form onSubmit={handleSubmit}>

                    {error && (
                        <div className="category-form-error">
                            {error}
                        </div>
                    )}

                    <div className="category-form-group">

                        <label htmlFor="category-name">
                            Category Name
                        </label>

                        <input
                            id="category-name"
                            type="text"
                            placeholder="Enter category name"
                            value={name}
                            onChange={(event) =>
                                setName(event.target.value)
                            }
                        />

                    </div>

                    <div className="category-form-group">

                        <label htmlFor="category-description">
                            Description
                        </label>

                        <textarea
                            id="category-description"
                            placeholder="Enter category description"
                            value={description}
                            onChange={(event) =>
                                setDescription(event.target.value)
                            }
                            rows="4"
                        />

                    </div>

                    <div className="category-active-field">

                        <input
                            id="category-active"
                            type="checkbox"
                            checked={active}
                            onChange={(event) =>
                                setActive(event.target.checked)
                            }
                        />

                        <label htmlFor="category-active">
                            Active category
                        </label>

                    </div>

                    <div className="category-modal-actions">

                        <button
                            type="button"
                            className="cancel-button"
                            onClick={handleClose}
                            disabled={loading}
                        >
                            Cancel
                        </button>

                        <button
                            type="submit"
                            className="save-category-button"
                            disabled={loading}
                        >
                            {loading
                                ? "Saving..."
                                : isEditMode
                                    ? "Update Category"
                                    : "Create Category"}
                        </button>

                    </div>

                </form>

            </div>
        </div>
    );
}

export default CategoryModal;