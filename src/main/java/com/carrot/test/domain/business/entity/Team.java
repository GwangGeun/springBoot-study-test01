package com.carrot.test.domain.business.entity;

import com.carrot.test.domain.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "team")
public class Team extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idf_team")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "team")
    private List<Account> accountList = new ArrayList<>();

}
