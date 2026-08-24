import axios from "axios";

const API_URL = "http://localhost:8080/api/products";

const publicProductService = {

    getAll: async () => {

        const response = await axios.get(API_URL);

        return response.data;
    },

    getById: async (id) => {

        const response =
            await axios.get(`${API_URL}/${id}`);

        return response.data;
    }

};

export default publicProductService;