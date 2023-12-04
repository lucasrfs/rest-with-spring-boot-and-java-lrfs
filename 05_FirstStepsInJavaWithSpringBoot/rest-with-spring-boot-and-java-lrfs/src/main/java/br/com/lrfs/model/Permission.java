package br.com.lrfs.model;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "permission")
public class Permission implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 180)
    private String description;

    @Override
    public String getAuthority() {
        return this.description;
    }

    public Permission() {
        
    }

    //generate getters and setters
    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description.toUpperCase();
    }

    //generate hashcode and equals
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime*result+((description == null)?0:description.hashCode());
        result = prime*result+((id == null)?0:id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(!(obj instanceof Permission)) return false;
        Permission other = (Permission) obj;
        if(description == null) {
            if(other.description != null) return false;
        } else if(!description.equals(other.description)) return false;
        if(id == null) {
            if(other.id != null) return false;
        } else if(!id.equals(other.id)) return false;
        return true;
    }

    //generate toString
    @Override
    public String toString() {
        return "Permission [description=" + description + ", id=" + id + "]";
    }



}
