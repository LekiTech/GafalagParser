package org.lekitech.gafalag.hibernate.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * Date: 12.11.2021
 * Project: GafalagParser
 * Class: Language
 *
 * @author Enver Eskendarov (envereskendarov@gmail.com)
 * @version 1.0
 */
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "language")
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "iso639_2")
    private String iso639_2;
    @Column(name = "iso639_3")
    private String iso639_3;
    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime created_at;
    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updated_at;

    public Language(String name, String iso639_2, String iso639_3) {
        this.name = name;
        this.iso639_2 = iso639_2;
        this.iso639_3 = iso639_3;
    }
}
