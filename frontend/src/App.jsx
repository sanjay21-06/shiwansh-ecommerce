import {
    BrowserRouter,
    Routes,
    Route,
    Navigate
} from "react-router-dom";

import Home from "./pages/home/Home";

import Login from "./pages/Login";
import Register from "./pages/Register";

import AdminLogin from "./pages/auth/AdminLogin";

import Products from "./pages/products/Products";
import ProductDetails from "./pages/products/ProductDetails";

import Categories from "./pages/categories/Categories";

import AdminDashboard from "./pages/admin/AdminDashboard";
import CategoryPage from "./pages/admin/categories/CategoryPage";
import ProductPage from "./pages/admin/products/ProductPage";

import ProtectedRoute from "./components/auth/ProtectedRoute";


function App() {

    return (

        <BrowserRouter>

            <Routes>

                {/* =====================================
                    CUSTOMER ROUTES
                ===================================== */}

                <Route
                    path="/"
                    element={<Home />}
                />

                <Route
                    path="/login"
                    element={<Login />}
                />

                <Route
                    path="/register"
                    element={<Register />}
                />

                <Route
                    path="/products"
                    element={<Products />}
                />

                <Route
                    path="/products/:id"
                    element={<ProductDetails />}
                />

                {/* CUSTOMER CATEGORIES */}

                <Route
                    path="/categories"
                    element={<Categories />}
                />

                <Route
                    path="/categories/:id"
                    element={<Categories />}
                />


                {/* =====================================
                    ADMIN LOGIN
                ===================================== */}

                <Route
                    path="/admin/login"
                    element={<AdminLogin />}
                />


                {/* =====================================
                    ADMIN DASHBOARD
                ===================================== */}

                <Route
                    path="/admin"
                    element={
                        <ProtectedRoute
                            allowedRoles={["ADMIN"]}
                        >
                            <AdminDashboard />
                        </ProtectedRoute>
                    }
                />


                {/* =====================================
                    ADMIN PRODUCTS
                ===================================== */}

                <Route
                    path="/admin/products"
                    element={
                        <ProtectedRoute
                            allowedRoles={["ADMIN"]}
                        >
                            <ProductPage />
                        </ProtectedRoute>
                    }
                />


                {/* =====================================
                    ADMIN CATEGORIES
                ===================================== */}

                <Route
                    path="/admin/categories"
                    element={
                        <ProtectedRoute
                            allowedRoles={["ADMIN"]}
                        >
                            <CategoryPage />
                        </ProtectedRoute>
                    }
                />


                {/* =====================================
                    FALLBACK
                ===================================== */}

                <Route
                    path="*"
                    element={
                        <Navigate
                            to="/"
                            replace
                        />
                    }
                />

            </Routes>

        </BrowserRouter>
    );
}

export default App;