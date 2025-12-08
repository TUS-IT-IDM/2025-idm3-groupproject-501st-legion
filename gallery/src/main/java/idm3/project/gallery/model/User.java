package idm3.project.gallery.model;

import jakarta.persistence.*;

@Entity
@Table(name = "_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid")
    private long userId;
    @Column(name = "FirstName", length = 50)
    private String firstName;
    @Column(name = "surname", nullable = true, length = 225)
    private String surname;
    @Column(name = "emailaddress", length = 100)
    private String emailAddress;
    @Column(name = "usertype", length = 50)
    private String userType;
    @Column(name = "username", length = 50)
    private String userName;
    @Column(name = "password", nullable = true, length = 225)
    private String password;
    @Column(name = "organization", nullable = true, length = 225)
    private String organization;

    public User(long userId, String firstName, String surname, String emailAddress, String userType, String userName, String password, String organization) {
        this.userId = userId;
        this.firstName = firstName;
        this.surname = surname;
        this.emailAddress = emailAddress;
        this.userType = userType;
        this.userName = userName;
        this.password = password;
        this.organization = organization;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", userType='" + userType + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", organization='" + organization + '\'' +
                '}';
    }

    public long getUserId() {
        return this.userId;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getSurname() {
        return this.surname;
    }

    public String getEmailAddress() {
        return this.emailAddress;
    }

    public String getUserType() {
        return this.userType;
    }

    public String getUserName() {
        return this.userName;
    }

    public String getPassword() {
        return this.password;
    }

    public String getOrganization() {
        return this.organization;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }
}
