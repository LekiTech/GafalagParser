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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;

/**
 * Date: 13.11.2021
 * Project: GafalagParser
 * Class: Dialect
 *
 * @author Enver Eskendarov (envereskendarov@gmail.com)
 * @version 1.0
 */
@Getter
@Setter
@NoArgsConstructor
@Entity(name = "dialect")
public class Dialect {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "language_id", nullable = false, insertable = false, updatable = false)
    private int languageId;
    @Basic
    @Column(name = "name", nullable = false, length = -1)
    private String name;
    @Basic
    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    @Basic
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
    @ManyToOne
    @JoinColumn(name = "language_id", referencedColumnName = "id", nullable = false)
    private Language languageByLanguageId;
    @OneToMany(mappedBy = "dialectByDialectId")
    private Collection<Etymology> etymologiesById;
    @OneToMany(mappedBy = "dialectByDialectId")
    private Collection<Expression> expressionsById;

    public Dialect(int languageId, String name) {
        this.languageId = languageId;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Dialect that = (Dialect) o;
        return this.id == that.id
                && this.languageId == that.languageId
                && Objects.equals(this.name, that.name)
                && Objects.equals(this.createdAt, that.createdAt)
                && Objects.equals(this.updatedAt, that.updatedAt)
                && Objects.equals(this.languageByLanguageId, that.languageByLanguageId)
                && Objects.equals(this.etymologiesById, that.etymologiesById)
                && Objects.equals(this.expressionsById, that.expressionsById);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                id,
                languageId,
                name,
                createdAt,
                updatedAt,
                languageByLanguageId,
                etymologiesById,
                expressionsById
        );
    }
}
