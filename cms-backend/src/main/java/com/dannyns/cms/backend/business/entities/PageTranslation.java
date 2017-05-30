package com.dannyns.cms.backend.business.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import static com.dannyns.cms.backend.business.entities.PageTranslation.TABLE_NAME;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = TABLE_NAME, uniqueConstraints = @UniqueConstraint(columnNames = {"page_id", "language_id"}))
public class PageTranslation extends BaseEntity {

    public static final String TABLE_NAME = "page_translations";

    @ManyToOne()
    private Page page;

    @ManyToOne()
    private Language language;

    private String title;

    private String description;

    private String content;
}
