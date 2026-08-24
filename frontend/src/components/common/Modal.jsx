function Modal({ isOpen, onClose, children }) {
    if (!isOpen) {
        return null;
    }

    return (
        <div className="modal-overlay" onClick={onClose}>
            <div
                className="modal-container"
                onClick={(event) => event.stopPropagation()}
            >
                <button
                    className="modal-close"
                    onClick={onClose}
                    type="button"
                >
                    ×
                </button>

                {children}
            </div>
        </div>
    );
}

export default Modal;