import api from "./api";

const publicCategoryService = {

    // GET ALL PUBLIC CATEGORIES
    getAll: async () => {

        const response =
            await api.get("/categories");

        return response.data;
    },


    // GET CATEGORY BY ID
    getById: async (id) => {

        const response =
            await api.get(`/categories/${id}`);

        return response.data;
    }

};

export default publicCategoryService;