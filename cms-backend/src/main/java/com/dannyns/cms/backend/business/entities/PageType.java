package com.dannyns.cms.backend.business.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import static com.dannyns.cms.backend.business.entities.PageType.TABLE_NAME;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = TABLE_NAME, uniqueConstraints = @UniqueConstraint(columnNames = {"type"}))
public class PageType extends BaseEntity {

    public static final String TABLE_NAME = "page_types";

    private String type;
}
