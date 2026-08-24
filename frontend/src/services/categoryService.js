import api from "./api";

const categoryService = {

    // ==============================
    // GET ALL CATEGORIES
    // ==============================

    getAll: async () => {

        const response =
            await api.get("/admin/categories");

        return response.data;
    },


    // ==============================
    // GET CATEGORY BY ID
    // ==============================

    getById: async (id) => {

        const response =
            await api.get(
                `/admin/categories/${id}`
            );

        return response.data;
    },


    // ==============================
    // CREATE CATEGORY
    // ==============================

    create: async (category) => {

        const response =
            await api.post(
                "/admin/categories",
                category
            );

        return response.data;
    },


    // ==============================
    // UPDATE CATEGORY
    // ==============================

    update: async (id, category) => {

        const response =
            await api.put(
                `/admin/categories/${id}`,
                category
            );

        return response.data;
    },


    // ==============================
    // DELETE CATEGORY
    // ==============================

    delete: async (id) => {

        const response =
            await api.delete(
                `/admin/categories/${id}`
            );

        return response.data;
    },
};

export default categoryService;