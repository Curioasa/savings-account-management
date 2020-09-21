package com.ing.technicaltest.domain;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@NoArgsConstructor
public class User {
    @Id
    @Setter(AccessLevel.PROTECTED)
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @OneToOne(mappedBy = "user")
    private SavingsAccount savingsAccount;

    public User(Integer id, String username, String firstName, String lastName, SavingsAccount savingsAccount) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.savingsAccount = savingsAccount;
        this.savingsAccount.setUser(this);
    }
}
