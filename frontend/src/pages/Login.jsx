import { useState } from "react";
import { useNavigate, Link } from "react-router-dom";
import authService from "../services/authService";

function Login() {

    const navigate = useNavigate();

    const [formData, setFormData] = useState({
        email: "",
        password: "",
    });

    const [error, setError] = useState("");
    const [loading, setLoading] = useState(false);


    const handleChange = (event) => {

        setFormData({
            ...formData,
            [event.target.name]: event.target.value,
        });

    };


    const handleSubmit = async (event) => {

        event.preventDefault();

        setError("");
        setLoading(true);

        try {

            const data = await authService.login(formData);

            /*
             * Save JWT
             */
            localStorage.setItem(
                "token",
                data.token
            );

            /*
             * Save logged-in user
             */
            localStorage.setItem(
                "user",
                JSON.stringify({
                    id: data.id,
                    name: data.name,
                    email: data.email,
                    role: data.role,
                    active: data.active,
                })
            );

            /*
             * Customer login
             */
            navigate("/");

        } catch (error) {

            console.error(
                "Login failed:",
                error
            );

            setError(
                "Invalid email or password."
            );

        } finally {

            setLoading(false);
        }
    };


    return (
        <div className="login-page">

            <div className="login-container">

                <h1>
                    Customer Login
                </h1>

                <p>
                    Sign in to your Shiwansh account
                </p>


                {error && (
                    <div className="error-message">
                        {error}
                    </div>
                )}


                <form onSubmit={handleSubmit}>

                    <div className="form-group">

                        <label htmlFor="email">
                            Email
                        </label>

                        <input
                            id="email"
                            name="email"
                            type="email"
                            placeholder="Enter your email"
                            value={formData.email}
                            onChange={handleChange}
                            required
                        />

                    </div>


                    <div className="form-group">

                        <label htmlFor="password">
                            Password
                        </label>

                        <input
                            id="password"
                            name="password"
                            type="password"
                            placeholder="Enter your password"
                            value={formData.password}
                            onChange={handleChange}
                            required
                        />

                    </div>


                    <button
                        type="submit"
                        disabled={loading}
                    >
                        {loading
                            ? "Logging in..."
                            : "Login"}
                    </button>

                </form>


                <p>
                    Don't have an account?{" "}

                    <Link to="/register">
                        Register
                    </Link>
                </p>


                <p>
                    Admin?{" "}

                    <Link to="/admin/login">
                        Admin Login
                    </Link>
                </p>

            </div>

        </div>
    );
}

export default Login;