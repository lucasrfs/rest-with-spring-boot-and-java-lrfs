package br.com.lrfs.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")  
public class User implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

    @Column(name = "user_name", unique = true)
    private String userName;

    //fullname
    @Column(name = "full_name")
    private String fullName;

    //password
    @Column(name = "password")
    private String password;

    //account_non_expired
    @Column(name = "account_non_expired")
    private boolean accountNonExpired;

    //account_non_locked
    @Column(name = "account_non_locked")
    private boolean accountNonLocked;

    //credentials_non_expired
    @Column(name = "credentials_non_expired")
    private boolean credentialsNonExpired;

    //enabled
    @Column(name = "enabled")
    private boolean enabled;

    public User() {
        
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="user_permission", joinColumns = {@JoinColumn(name="id_user")}, inverseJoinColumns = {@JoinColumn(name="id_permission")})
    private List<Permission> permissions;

    public List<String> getRoles() {
        List<String> roles = new ArrayList<>();
        for(Permission permission : this.permissions) {
            roles.add(permission.getDescription());
        }
        return roles;
    }


    //generate getters and setters
    public long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getFullName() {
        return fullName;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.permissions;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    //generate setters
    public void setId(long id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName.toLowerCase();
    }

    public void setFullName(String fullName) {
        this.fullName = fullName.toUpperCase();
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    //generate hashcode and equals
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime*result+(accountNonExpired?1231:1237);
        result = prime*result+(accountNonLocked?1231:1237);
        result = prime*result+(credentialsNonExpired?1231:1237);
        result = prime*result+(enabled?1231:1237);
        result = prime*result+((fullName == null)?0:fullName.hashCode());
        result = prime*result+(int)(id^(id>>>32));
        result = prime*result+((password == null)?0:password.hashCode());
        result = prime*result+((userName == null)?0:userName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(!(obj instanceof User)) return false;
        User other = (User) obj;
        if(accountNonExpired != other.accountNonExpired) return false;
        if(accountNonLocked != other.accountNonLocked) return false;
        if(credentialsNonExpired != other.credentialsNonExpired) return false;
        if(enabled != other.enabled) return false;
        if(fullName == null) {
            if(other.fullName != null) return false;
        } else if(!fullName.equals(other.fullName)) return false;
        if(id != other.id) return false;
        if(password == null) {
            if(other.password != null) return false;
        } else if(!password.equals(other.password)) return false;
        if(userName == null) {
            if(other.userName != null) return false;
        } else if(!userName.equals(other.userName)) return false;
        return true;
    }

    
}
