package com.example.NykaaAppAPI.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class NykaaUser {
    @Id
    @GeneratedValue
    private Integer userId;
    private String name;
    private String mailId;
    private Long phoneNumber;
    private String password;

    @ManyToMany
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    @JoinTable(joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "userId")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private Set<Role> roles;

    @JsonIgnore
    @OneToMany(mappedBy = "nykaaUser", cascade = CascadeType.ALL)
    private Set<Cart> carts;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<OrderProducts> orderProducts;

    public NykaaUser(String mailId, String password) {
        this.mailId = mailId;
        this.password = password;
    }
}
