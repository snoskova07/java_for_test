package model;

public class ContactData {
  private final String firstName;
  private final String lastName;
  private final String nickname;
  private final String company;
  private final String city;
  private final String email;

  public ContactData(String firstName, String lastName, String nickname, String company, String city, String email) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.nickname = nickname;
    this.company = company;
    this.city = city;
    this.email = email;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getNickname() {
    return nickname;
  }

  public String getCompany() {
    return company;
  }

  public String getCity() {
    return city;
  }

  public String getEmail() {
    return email;
  }
}
