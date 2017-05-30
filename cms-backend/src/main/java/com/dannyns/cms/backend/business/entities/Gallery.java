package com.dannyns.cms.backend.business.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

import static com.dannyns.cms.backend.business.entities.Gallery.TABLE_NAME;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = TABLE_NAME)
public class Gallery extends BaseEntity {

    public static final String TABLE_NAME = "galleries";

    @OneToMany()
    @Builder.Default
    private Set<Image> images = new HashSet<>();

    @OneToMany()
    @Builder.Default
    private Set<GalleryTranslation> translations = new HashSet<>();
}
