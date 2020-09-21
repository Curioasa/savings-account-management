package com.ing.technicaltest.domain;

import com.ing.technicaltest.enums.Currency;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@NoArgsConstructor
public class SavingsAccount {
    @Id
    @Setter(AccessLevel.PROTECTED)
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Currency currency;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(unique = true)
    private User user;

    public SavingsAccount(Integer id, String name, Currency currency, User user) {
        this.id = id;
        this.name = name;
        this.currency = currency;
        this.user = user;
        this.user.setSavingsAccount(this);
    }
}
