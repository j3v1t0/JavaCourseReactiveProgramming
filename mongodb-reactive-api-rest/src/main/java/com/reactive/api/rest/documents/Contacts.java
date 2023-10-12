package com.reactive.api.rest.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "contacts")
public class Contacts {

    @Id
    private String id;
    private String fullname;
    private String email;
    private String phone;

    public Contacts() {
    }

    public Contacts(String fullname, String email, String phone) {
        this.fullname = fullname;
        this.email = email;
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contacts contacts = (Contacts) o;
        return Objects.equals(id, contacts.id) && Objects.equals(fullname, contacts.fullname) && Objects.equals(email, contacts.email) && Objects.equals(phone, contacts.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullname, email, phone);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Contacts{");
        sb.append("id='").append(id).append('\'');
        sb.append(", fullname='").append(fullname).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", phone='").append(phone).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
