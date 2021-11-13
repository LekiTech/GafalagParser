package org.lekitech.gafalag.hibernate.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;

/**
 * Date: 12.11.2021
 * Project: GafalagParser
 * Class: Language
 *
 * @author Enver Eskendarov (envereskendarov@gmail.com)
 * @version 1.0
 */
@Getter
@Setter
@NoArgsConstructor
@Entity(name = "language")
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "name", nullable = false, length = -1)
    private String name;
    @Basic
    @Column(name = "iso639_2", length = 2)
    private String iso6392;
    @Basic
    @Column(name = "iso639_3", length = 3)
    private String iso6393;
    @Basic
    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    @Basic
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
    @OneToMany(mappedBy = "languageByLanguageId")
    private Collection<Definition> definitionsById;
    @OneToMany(mappedBy = "languageByLanguageId")
    private Collection<Dialect> dialectsById;
    @OneToMany(mappedBy = "languageByLanguageId")
    private Collection<Etymology> etymologiesById;
    @OneToMany(mappedBy = "languageByLanguageId")
    private Collection<Expression> expressionsById;

    public Language(String name, String iso6392, String iso6393) {
        this.name = name;
        this.iso6392 = iso6392;
        this.iso6393 = iso6393;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Language that = (Language) o;
        return this.id == that.id
                && Objects.equals(this.name, that.name)
                && Objects.equals(this.iso6392, that.iso6392)
                && Objects.equals(this.iso6393, that.iso6393)
                && Objects.equals(this.createdAt, that.createdAt)
                && Objects.equals(this.updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                id,
                name,
                iso6392,
                iso6393,
                createdAt,
                updatedAt,
                definitionsById,
                dialectsById,
                etymologiesById,
                expressionsById
        );
    }
}
