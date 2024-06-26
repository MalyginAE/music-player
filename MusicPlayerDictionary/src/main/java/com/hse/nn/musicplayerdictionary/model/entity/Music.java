package com.hse.nn.musicplayerdictionary.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Builder
@Table(name = "music")
@Entity
@AllArgsConstructor
@Data
public class Music {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String musicName;

    @Column
    private String author;

    @Column
    private Integer imageId;

    @Column
    private String externalSearchId;
}
