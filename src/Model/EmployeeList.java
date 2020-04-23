package Model;

import java.util.ArrayList;

public class EmployeeList {

    ArrayList<Employee> list;


    public EmployeeList() {

        list = new ArrayList<>();
        list.add(new Model.Employee("Jan", "Kowalski", Model.PositionComponent.CEO, "12-06-2012", 20000));
        list.add(new Model.Employee("Mary", "Arsenal", Model.PositionComponent.CUSTOMER_SUPPORT, "02-11-2012", 5000));
        list.add(new Model.Employee("John", "Liverpool", Model.PositionComponent.CUSTOMER_SUPPORT, "12-06-2012", 5000));
        list.add(new Model.Employee("Lana", "Chelsea", Model.PositionComponent.ACCOUNTANT, "12-06-2012", 9000));
        list.add(new Model.Employee("Mark", "Man City", Model.PositionComponent.LAWER, "12-06-2012", 13000));
        list.add(new Model.Employee("Peter", "Everton", Model.PositionComponent.SALES_MANAGER, "12-06-2012", 12000));
        list.add(new Model.Employee("Tom", "Tottenham", Model.PositionComponent.SALES_ASSISTANT, "12-06-2012", 6000));
        list.add(new Model.Employee("Frank", "Newcastle", Model.PositionComponent.MARKETING_MANAGER, "12-06-2012", 7500));
        list.add(new Model.Employee("Samanta", "Southampton", Model.PositionComponent.RECEPTIONIST, "12-06-2012", 4500));

    }

    public ArrayList<Employee> getList() {
        return list;
    }

    public void addEmployee(String name, String surname, PositionComponent position, String dateOfEmployment, int salary) {
        list.add(new Model.Employee(name,  surname,  position,  dateOfEmployment,  salary));
    }

    public void removeEmployee(int index) {
        list.remove(index);
    }

    public Employee getEmployee(int index) {
        return list.get(index);
    }

    public void updateEmployee(int index, String name, String surname, PositionComponent position, String dateOfEmployment, int salary) {
        Employee employee = getEmployee(index);
        employee.setName(name);
        employee.setSurname(surname);
        employee.setPosition(position);
        employee.setDateOfEmployment(dateOfEmployment);
        employee.setSalary(salary);
    }


}
