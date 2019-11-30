package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.Objects;

@XStreamAlias("contact")

@Entity
@Table(name = "addressbook")
public class ContactData {
  @XStreamOmitField
  @Id
  @Column(name="id")
  private int id = Integer.MAX_VALUE;
  @Expose
  @Column(name="firstname")
  private String firstName;
  @Expose
  @Column(name="lastname")
  private String lastName;
  @Expose
  @Column(name="address")
  @Type(type="text")
  private String address;
  @Expose
  @Column(name="email")
  @Type(type="text")
  private String email;
  @Expose
  @Column(name="home")
  @Type(type="text")
  private String home;
  @Expose
  @Column(name="mobile")
  @Type(type="text")
  private String mobile;
  @Expose
  @Column(name="work")
  @Type(type="text")
  private String work;
  @Transient
  private String allPhones;
  @Expose
  @Column(name="email2")
  @Type(type="text")
  private String email2;
  @Expose
  @Column(name="email3")
  @Type(type="text")
  private String email3;
  @Transient
  private String allEmails;
  @Expose
  @Transient
  private String group;
  @Expose
  @Column(name="photo")
  @Type(type="text")
  private String photo;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return id == that.id &&
            Objects.equals(firstName, that.firstName) &&
            Objects.equals(lastName, that.lastName) &&
            Objects.equals(address, that.address) &&
            Objects.equals(email, that.email) &&
            Objects.equals(home, that.home) &&
            Objects.equals(mobile, that.mobile) &&
            Objects.equals(work, that.work) &&
            Objects.equals(email2, that.email2) &&
            Objects.equals(email3, that.email3) &&
            Objects.equals(photo, that.photo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstName, lastName, address, email, home, mobile, work, email2, email3, photo);
  }

  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public ContactData withFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public ContactData withLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }

  public ContactData withEmail(String email) {
    this.email = email;
    return this;
  }

  public ContactData withHomePhone(String home) {
    this.home = home;
    return this;
  }

  public ContactData withMobilePhone(String mobile) {
    this.mobile = mobile;
    return this;
  }

  public ContactData withWorkPhone(String work) {
    this.work = work;
    return this;
  }

  public ContactData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }

  public ContactData withGroup(String group) {
    this.group = group;
    return this;
  }

  public ContactData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }

  public ContactData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }

  public ContactData withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;
  }

  public ContactData withPhoto(File photo) {
    this.photo = photo.getPath();
    return this;
  }

  public int getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getAddress() {
    return address;
  }

  public String getEmail() {
    return email;
  }

  public String getHomePhone() {
    return home;
  }

  public String getMobilePhone() {
    return mobile;
  }

  public String getWorkPhone() {
    return work;
  }

  public String getAllPhones() {
    return allPhones;
  }

  public String getGroup() {
    return group;
  }

  public String getEmail2() {
    return email2;
  }

  public String getEmail3() {
    return email3;
  }

  public String getAllEmails() {
    return allEmails;
  }

  public File getPhoto() {
    return new File(photo);
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            '}';
  }
}
