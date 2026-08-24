import { useState } from "react";
import Modal from "../../components/common/Modal";

function Login() {
    const [isLoginOpen, setIsLoginOpen] = useState(true);

    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    const handleSubmit = (event) => {
        event.preventDefault();

        console.log("Email:", email);
        console.log("Password:", password);

        // Backend authentication will be connected later.
    };

    return (
        <div className="login-page">

            <div className="login-page-content">
                <h1>Shiwansh E-Commerce</h1>
                <p>Admin Management Portal</p>

                <button
                    className="login-button"
                    onClick={() => setIsLoginOpen(true)}
                >
                    Admin Login
                </button>
            </div>

            <Modal
                isOpen={isLoginOpen}
                onClose={() => setIsLoginOpen(false)}
            >
                <div className="login-form">

                    <h2>Admin Login</h2>

                    <p className="login-subtitle">
                        Sign in to access the admin dashboard
                    </p>

                    <form onSubmit={handleSubmit}>

                        <div className="form-group">
                            <label htmlFor="email">
                                Email
                            </label>

                            <input
                                id="email"
                                type="email"
                                placeholder="Enter your email"
                                value={email}
                                onChange={(event) =>
                                    setEmail(event.target.value)
                                }
                                required
                            />
                        </div>

                        <div className="form-group">
                            <label htmlFor="password">
                                Password
                            </label>

                            <input
                                id="password"
                                type="password"
                                placeholder="Enter your password"
                                value={password}
                                onChange={(event) =>
                                    setPassword(event.target.value)
                                }
                                required
                            />
                        </div>

                        <button
                            type="submit"
                            className="login-submit"
                        >
                            Login
                        </button>

                    </form>

                </div>
            </Modal>

        </div>
    );
}

export default Login;