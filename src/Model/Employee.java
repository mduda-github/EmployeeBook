package Model;

public class Employee {
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

  public String getSurname() {
    return surname;
  }

  public PositionComponent getPosition() {
    return position;
  }

  public String getDateOfEmployment() {
    return dateOfEmployment;
  }

  public int getSalary() {
    return salary;
  }
}
