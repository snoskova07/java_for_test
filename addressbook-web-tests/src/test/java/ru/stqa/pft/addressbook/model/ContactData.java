package ru.stqa.pft.addressbook.model;

public class ContactData {
  private final String firstName;
  private final String lastName;
  private final String nickname;
  private final String company;
  private final String city;
  private final String email;
  private String group;

  //конструктор
  public ContactData(String firstName, String lastName, String nickname, String company, String city, String email, String group) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.nickname = nickname;
    this.company = company;
    this.city = city;
    this.email = email;
    this.group = group;
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

  public String getGroup() {
    return group;
  }
}
