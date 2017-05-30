package com.dannyns.cms.backend.business.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

import static com.dannyns.cms.backend.business.entities.Image.TABLE_NAME;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = TABLE_NAME)
public class Image extends BaseEntity {

    public static final String TABLE_NAME = "images";

    private String name;

    private String location;
}
