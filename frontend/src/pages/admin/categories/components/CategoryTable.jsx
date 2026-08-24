function CategoryTable({ categories, onEdit, onDelete }) {
    return (
        <div className="category-table-wrapper">
            <table className="category-table">

                <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Status</th>
                    <th>Actions</th>
                </tr>
                </thead>

                <tbody>
                {categories.length === 0 ? (
                    <tr>
                        <td colSpan="5" className="empty-state">
                            No categories found
                        </td>
                    </tr>
                ) : (
                    categories.map((category) => (
                        <tr key={category.id}>

                            <td>#{category.id}</td>

                            <td>
                                <strong>{category.name}</strong>
                            </td>

                            <td>
                                {category.description || "-"}
                            </td>

                            <td>
                                    <span
                                        className={
                                            category.active
                                                ? "status active"
                                                : "status inactive"
                                        }
                                    >
                                        {category.active
                                            ? "Active"
                                            : "Inactive"}
                                    </span>
                            </td>

                            <td>
                                <div className="table-actions">

                                    <button
                                        className="edit-button"
                                        onClick={() =>
                                            onEdit(category)
                                        }
                                    >
                                        Edit
                                    </button>

                                    <button
                                        className="delete-button"
                                        onClick={() =>
                                            onDelete(category)
                                        }
                                    >
                                        Delete
                                    </button>

                                </div>
                            </td>

                        </tr>
                    ))
                )}
                </tbody>

            </table>
        </div>
    );
}

export default CategoryTable;