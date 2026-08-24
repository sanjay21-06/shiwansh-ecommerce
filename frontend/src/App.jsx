import { BrowserRouter, Routes, Route } from "react-router-dom";

import Home from "./pages/home/Home";
import Login from "./pages/auth/Login";
import Products from "./pages/products/Products";
import ProductPage from "./pages/admin/products/ProductPage";
import AdminDashboard from "./pages/admin/AdminDashboard";
import CategoryPage from "./pages/admin/categories/CategoryPage";
import ProductDetails from "./pages/products/ProductDetails";

function App() {
    return (
        <BrowserRouter>
            <Routes>

                {/* Customer Side */}
                <Route
                    path="/"
                    element={<Home />}
                />

                <Route
                    path="/login"
                    element={<Login />}
                />

                {/* Admin Side */}
                <Route
                    path="/admin"
                    element={<AdminDashboard />}
                />

                <Route
                    path="/admin/categories"
                    element={<CategoryPage />}
                />

                <Route
                    path="/products"
                    element={<Products />}
                />

                <Route
                    path="/admin/products"
                    element={<ProductPage />}
                />

                <Route
                    path="/products/:id"
                    element={<ProductDetails />}
                />

            </Routes>
        </BrowserRouter>
    );
}

export default App;