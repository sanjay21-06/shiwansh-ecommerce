import axios from "axios";


const API_URL = "http://localhost:8080/api/products";


const productService = {

    // ==========================================
    // GET ALL ACTIVE PRODUCTS
    // ==========================================

    getActiveProducts: async () => {

        const response =
            await axios.get(API_URL);

        return response.data;
    },


    // ==========================================
    // GET ACTIVE PRODUCT BY ID
    // ==========================================

    getActiveProductById: async (id) => {

        const response =
            await axios.get(
                `${API_URL}/${id}`
            );

        return response.data;
    },


    // ==========================================
    // GET ALL PRODUCTS
    // ==========================================

    getAll: async () => {

        const response =
            await axios.get(API_URL);

        return response.data;
    },


    // ==========================================
    // GET PRODUCT BY ID
    // ==========================================

    getById: async (id) => {

        const response =
            await axios.get(
                `${API_URL}/${id}`
            );

        return response.data;
    }

};


export default productService;