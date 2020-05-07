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
  LAWYER {

    @Override
    public String toString() {
      return "Lawyer";
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
  },
}
