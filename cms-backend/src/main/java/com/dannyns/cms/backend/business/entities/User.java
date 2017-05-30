package com.dannyns.cms.backend.business.entities;

import lombok.*;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

import static com.dannyns.cms.backend.business.entities.User.TABLE_NAME;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = TABLE_NAME, uniqueConstraints = @UniqueConstraint(columnNames = {"username"}))
public class User extends BaseEntity {

    public static final String TABLE_NAME = "users";

    private String username;

    private String password;

    @Builder.Default
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();
}
