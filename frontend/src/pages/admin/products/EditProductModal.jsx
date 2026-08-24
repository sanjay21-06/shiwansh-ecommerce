import { useEffect, useState } from "react";

function EditProductModal({
                              product,
                              categories,
                              onClose,
                              onSubmit,
                              loading,
                          }) {
    const [formData, setFormData] = useState({
        categoryId: "",
        name: "",
        description: "",
        price: "",
        sku: "",
    });

    const [errors, setErrors] = useState({});

    useEffect(() => {
        if (product) {
            setFormData({
                categoryId: product.categoryId || "",
                name: product.name || "",
                description: product.description || "",
                price: product.price || "",
                sku: product.sku || "",
            });
        }
    }, [product]);

    const handleChange = (event) => {
        const { name, value } = event.target;

        setFormData((previous) => ({
            ...previous,
            [name]: value,
        }));

        setErrors((previous) => ({
            ...previous,
            [name]: "",
        }));
    };

    const validate = () => {
        const newErrors = {};

        if (!formData.categoryId) {
            newErrors.categoryId = "Category is required";
        }

        if (!formData.name.trim()) {
            newErrors.name = "Product name is required";
        } else if (formData.name.length > 200) {
            newErrors.name =
                "Product name must not exceed 200 characters";
        }

        if (formData.description.length > 1000) {
            newErrors.description =
                "Description must not exceed 1000 characters";
        }

        if (!formData.price) {
            newErrors.price = "Price is required";
        } else if (Number(formData.price) <= 0) {
            newErrors.price =
                "Price must be greater than 0";
        }

        if (!formData.sku.trim()) {
            newErrors.sku = "SKU is required";
        } else if (formData.sku.length > 100) {
            newErrors.sku =
                "SKU must not exceed 100 characters";
        }

        setErrors(newErrors);

        return Object.keys(newErrors).length === 0;
    };

    const handleSubmit = async (event) => {
        event.preventDefault();

        if (!validate()) {
            return;
        }

        await onSubmit({
            categoryId: Number(formData.categoryId),
            name: formData.name.trim(),
            description: formData.description.trim(),
            price: Number(formData.price),
            sku: formData.sku.trim(),
        });
    };

    return (
        <div className="modal-overlay">

            <div className="modal">

                <div className="modal-header">

                    <div>
                        <h2>Edit Product</h2>
                        <p>Update product details</p>
                    </div>

                    <button
                        type="button"
                        className="modal-close"
                        onClick={onClose}
                    >
                        ×
                    </button>

                </div>

                <form onSubmit={handleSubmit}>

                    {/* Category */}

                    <div className="form-group">

                        <label>
                            Category <span>*</span>
                        </label>

                        <select
                            name="categoryId"
                            value={formData.categoryId}
                            onChange={handleChange}
                        >
                            <option value="">
                                Select category
                            </option>

                            {categories.map((category) => (
                                <option
                                    key={category.id}
                                    value={category.id}
                                >
                                    {category.name}
                                </option>
                            ))}
                        </select>

                        {errors.categoryId && (
                            <small className="form-error">
                                {errors.categoryId}
                            </small>
                        )}

                    </div>

                    {/* Product Name */}

                    <div className="form-group">

                        <label>
                            Product Name <span>*</span>
                        </label>

                        <input
                            type="text"
                            name="name"
                            value={formData.name}
                            onChange={handleChange}
                            placeholder="Enter product name"
                            maxLength={200}
                        />

                        {errors.name && (
                            <small className="form-error">
                                {errors.name}
                            </small>
                        )}

                    </div>

                    {/* Description */}

                    <div className="form-group">

                        <label>Description</label>

                        <textarea
                            name="description"
                            value={formData.description}
                            onChange={handleChange}
                            placeholder="Enter product description"
                            maxLength={1000}
                            rows={4}
                        />

                        {errors.description && (
                            <small className="form-error">
                                {errors.description}
                            </small>
                        )}

                    </div>

                    {/* Price */}

                    <div className="form-group">

                        <label>
                            Price <span>*</span>
                        </label>

                        <input
                            type="number"
                            name="price"
                            value={formData.price}
                            onChange={handleChange}
                            placeholder="Enter price"
                            min="0.01"
                            step="0.01"
                        />

                        {errors.price && (
                            <small className="form-error">
                                {errors.price}
                            </small>
                        )}

                    </div>

                    {/* SKU */}

                    <div className="form-group">

                        <label>
                            SKU <span>*</span>
                        </label>

                        <input
                            type="text"
                            name="sku"
                            value={formData.sku}
                            onChange={handleChange}
                            placeholder="Enter SKU"
                            maxLength={100}
                        />

                        {errors.sku && (
                            <small className="form-error">
                                {errors.sku}
                            </small>
                        )}

                    </div>

                    {/* Actions */}

                    <div className="modal-actions">

                        <button
                            type="button"
                            className="secondary-button"
                            onClick={onClose}
                            disabled={loading}
                        >
                            Cancel
                        </button>

                        <button
                            type="submit"
                            className="primary-button"
                            disabled={loading}
                        >
                            {loading
                                ? "Updating..."
                                : "Update Product"}
                        </button>

                    </div>

                </form>

            </div>

        </div>
    );
}

export default EditProductModal;