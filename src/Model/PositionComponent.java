package Model;

public enum PositionComponent {
    ACCOUNTANT {
        @Override
        public String toString() {
            return "Accountant";
        }
    },
    CEO {
        @Override
        public String toString() {
            return "CEO";
        }
    },
    CUSTOMER_SUPPORT {
        @Override
        public String toString() {
            return "Customer Support";
        }
    },
    LAWER {
        @Override
        public String toString() {
            return "Lawer";
        }
    },
    MARKETING_MANAGER {
        @Override
        public String toString() {
            return "Marketing Manager";
        }
    },
    RECEPTIONIST {
        @Override
        public String toString() {
            return "Receptionist";
        }
    },
    SALES_ASSISTANT {
        @Override
        public String toString() {
            return "Sales Assistant";
        }
    },
    SALES_MANAGER {
        @Override
        public String toString() {
            return "Sales Manager";
        }
    };
}
