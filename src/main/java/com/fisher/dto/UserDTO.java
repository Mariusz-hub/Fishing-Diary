package com.fisher.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;

public class UserDTO {

    @JsonProperty(value = "email")
    private String email;

    @JsonProperty(value = "firstName")
    private String firstName;

    @JsonProperty(value = "lastName")
    private String lastName;

    @JsonProperty(value = "phoneNumber")
    private String phoneNumber;

    @JsonProperty(value = "Registration_Date")
    private Date registrationDate;

    @JsonProperty(value = "enabled")
    private boolean enabled;

    public UserDTO(Builder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.phoneNumber = builder.phoneNumber;
        this.registrationDate = builder.registrationDate;
        this.enabled = builder.enabled;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", registrationDate=" + registrationDate +
                ", enabled=" + enabled +
                '}';
    }

    public static class Builder {

        private String email;

        private String firstName;

        private String lastName;

        private String phoneNumber;

        private Date registrationDate;

        private boolean enabled;

        public Builder email (String email){
            this.email = email;
            return this;
        }

        public Builder firstName(String firstName){
            this.firstName = firstName;
            return this;
        }

        public Builder lastName (String lastName){
            this.lastName = lastName;
            return this;
        }

        public Builder phoneNumber(String phoneNumber){
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder registrationDate(Date registrationDate){
            this.registrationDate = registrationDate;
            return this;
        }

        public Builder enabled(boolean enabled){
            this.enabled = enabled;
            return this;
        }

        public UserDTO build(){
           return  new UserDTO(this);
        }


    }

}
