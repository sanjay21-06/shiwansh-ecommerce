import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import Modal from "../../components/common/Modal";

function AdminLogin() {
    const navigate = useNavigate();

    const [isLoginOpen, setIsLoginOpen] = useState(true);

    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    const handleSubmit = (event) => {
        event.preventDefault();

        console.log("Admin Email:", email);
        console.log("Admin Password:", password);

        // Backend authentication will be connected later.

        // Temporary navigation for testing.
        navigate("/admin");
    };

    return (
        <div className="login-page">

            {/* =========================
                ADMIN LOGIN CONTENT
            ========================= */}
            <div className="login-page-content">

                <h1>
                    Shiwansh Admin
                </h1>

                <p>
                    Administration Portal
                </p>

                <button
                    className="login-button"
                    onClick={() => setIsLoginOpen(true)}
                >
                    Admin Login
                </button>

                <div className="login-navigation">

                    <p>
                        Are you a customer?
                    </p>

                    <Link to="/login">
                        User Login
                    </Link>

                </div>

            </div>


            {/* =========================
                ADMIN LOGIN MODAL
            ========================= */}
            <Modal
                isOpen={isLoginOpen}
                onClose={() => setIsLoginOpen(false)}
            >

                <div className="login-form">

                    <h2>
                        Admin Login
                    </h2>

                    <p className="login-subtitle">
                        Sign in to manage the Shiwansh store.
                    </p>


                    <form onSubmit={handleSubmit}>

                        {/* EMAIL */}
                        <div className="form-group">

                            <label htmlFor="admin-email">
                                Admin Email
                            </label>

                            <input
                                id="admin-email"
                                type="email"
                                placeholder="Enter admin email"
                                value={email}
                                onChange={(event) =>
                                    setEmail(event.target.value)
                                }
                                required
                            />

                        </div>


                        {/* PASSWORD */}
                        <div className="form-group">

                            <label htmlFor="admin-password">
                                Password
                            </label>

                            <input
                                id="admin-password"
                                type="password"
                                placeholder="Enter admin password"
                                value={password}
                                onChange={(event) =>
                                    setPassword(event.target.value)
                                }
                                required
                            />

                        </div>


                        {/* LOGIN BUTTON */}
                        <button
                            type="submit"
                            className="login-submit"
                        >
                            Login as Admin
                        </button>

                    </form>

                </div>

            </Modal>

        </div>
    );
}

export default AdminLogin;