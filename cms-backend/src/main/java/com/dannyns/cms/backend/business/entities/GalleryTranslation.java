package com.dannyns.cms.backend.business.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import static com.dannyns.cms.backend.business.entities.GalleryTranslation.TABLE_NAME;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = TABLE_NAME, uniqueConstraints = @UniqueConstraint(columnNames = {"gallery_id", "language_id"}))
public class GalleryTranslation extends BaseEntity {

    public static final String TABLE_NAME = "gallery_translations";

    @ManyToOne()
    private Gallery gallery;

    @ManyToOne()
    private Language language;

    private String name;

    private String description;
}
