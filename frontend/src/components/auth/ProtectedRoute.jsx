import { Navigate, useLocation } from "react-router-dom";

function ProtectedRoute({
                            children,
                            allowedRoles = []
                        }) {

    const location = useLocation();

    const token = localStorage.getItem("token");
    const userData = localStorage.getItem("user");

    /*
     * No login information
     */
    if (!token || !userData) {

        /*
         * Admin route
         */
        if (location.pathname.startsWith("/admin")) {
            return (
                <Navigate
                    to="/admin/login"
                    replace
                />
            );
        }

        /*
         * Customer route
         */
        return (
            <Navigate
                to="/login"
                replace
            />
        );
    }


    let user;

    try {

        user = JSON.parse(userData);

    } catch (error) {

        console.error(
            "Invalid user data:",
            error
        );

        localStorage.removeItem("token");
        localStorage.removeItem("user");

        return (
            <Navigate
                to="/login"
                replace
            />
        );
    }


    /*
     * Role protection
     */
    if (
        allowedRoles.length > 0 &&
        !allowedRoles.includes(user.role)
    ) {

        /*
         * Customer trying to access admin
         */
        if (location.pathname.startsWith("/admin")) {

            return (
                <Navigate
                    to="/"
                    replace
                />
            );
        }

        /*
         * Any other unauthorized route
         */
        return (
            <Navigate
                to="/"
                replace
            />
        );
    }


    /*
     * Everything is valid
     */
    return children;
}

export default ProtectedRoute;