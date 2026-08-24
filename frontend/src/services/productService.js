import api from "./api";

const productService = {
    getAll: async () => {
        const response = await api.get("/admin/products");
        return response.data;
    },

    getById: async (id) => {
        const response = await api.get(`/admin/products/${id}`);
        return response.data;
    },

    create: async (product) => {
        const response = await api.post(
            "/admin/products",
            product
        );
        return response.data;
    },

    update: async (id, product) => {
        const response = await api.put(
            `/admin/products/${id}`,
            product
        );
        return response.data;
    },

    delete: async (id) => {
        const response = await api.delete(
            `/admin/products/${id}`
        );
        return response.data;
    },
};

export default productService;