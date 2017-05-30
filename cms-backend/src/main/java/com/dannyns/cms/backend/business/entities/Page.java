package com.dannyns.cms.backend.business.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static com.dannyns.cms.backend.business.entities.Page.TABLE_NAME;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = TABLE_NAME, uniqueConstraints = @UniqueConstraint(columnNames = {"uri"}))
public class Page extends BaseEntity {

    public static final String TABLE_NAME = "pages";

    @Builder.Default
    @OneToMany(mappedBy = "page")
    private Set<PageTranslation> traslations = new HashSet<>();

    @OneToOne
    private PageType pageType;

    private String uri;
}
