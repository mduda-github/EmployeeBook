package Model;

public class Employee implements Comparable<Employee> {
  private String name;
  private String surname;
  private PositionComponent position;
  private String dateOfEmployment;
  private int salary;

  public Employee(
    String name,
    String surname,
    PositionComponent position,
    String dateOfEmployment,
    int salary
  ) {
    this.name = name;
    this.surname = surname;
    this.position = position;
    this.dateOfEmployment = dateOfEmployment;
    this.salary = salary;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public PositionComponent getPosition() {
    return position;
  }

  public void setPosition(PositionComponent position) {
    this.position = position;
  }

  public String getDateOfEmployment() {
    return dateOfEmployment;
  }

  public void setDateOfEmployment(String dateOfEmployment) {
    this.dateOfEmployment = dateOfEmployment;
  }

  public int getSalary() {
    return salary;
  }

  public void setSalary(int salary) {
    this.salary = salary;
  }

  @Override
  public int compareTo(Employee o) {
    return 0;
  }
}
