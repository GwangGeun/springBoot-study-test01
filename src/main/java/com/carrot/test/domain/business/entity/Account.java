package com.carrot.test.domain.business.entity;

import com.carrot.test.domain.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "account")
public class Account extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idf_account")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idf_team")
    private Team team;

    private String name;
    private String gender;

}
