package com.dannyns.cms.backend.business.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

import static com.dannyns.cms.backend.business.entities.Role.TABLE_NAME;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = TABLE_NAME, uniqueConstraints = @UniqueConstraint(columnNames = {"role"}))
public class Role extends BaseEntity {

    public static final String TABLE_NAME = "roles";

    private String role;
}
