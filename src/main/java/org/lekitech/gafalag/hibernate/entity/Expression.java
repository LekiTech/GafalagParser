package org.lekitech.gafalag.hibernate.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.PostgresUUIDType;

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
 * Class: Expression
 *
 * @author Enver Eskendarov (envereskendarov@gmail.com)
 * @version 1.0
 */
@Getter
@Setter
@NoArgsConstructor
@Entity(name = "expression")
public class Expression {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private PostgresUUIDType id;
    @Basic
    @Column(name = "spelling", nullable = false, length = -1)
    private String spelling;
    @Basic
    @Column(name = "misspelling")
    private boolean misspelling;
    @Basic
    @Column(name = "inflection", length = -1)
    private String inflection;
    @Basic
    @Column(name = "gender_id")
    private int genderId;
    @Basic
    @Column(name = "language_id", nullable = false)
    private int languageId;
    @Basic
    @Column(name = "dialect_id")
    private int dialectId;
    @Basic
    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    @Basic
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
    @OneToMany(mappedBy = "expressionByExpressionId")
    private Collection<Definition> definitionsById;
    @OneToMany(mappedBy = "expressionByExpressionId")
    private Collection<Etymology> etymologiesById;
    @ManyToOne
    @JoinColumn(name = "gender_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Gender genderByGenderId;
    @ManyToOne
    @JoinColumn(name = "language_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private Language languageByLanguageId;
    @ManyToOne
    @JoinColumn(name = "dialect_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Dialect dialectByDialectId;
    @OneToMany(mappedBy = "expressionByExpression1Id")
    private Collection<ExpressionRelation> expressionRelationsBy1Id;
    @OneToMany(mappedBy = "expressionByExpression2Id")
    private Collection<ExpressionRelation> expressionRelationsBy2Id;
    @OneToMany(mappedBy = "expressionByExpressionId")
    private Collection<Mediafile> mediafilesById;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Expression that = (Expression) o;
        return this.misspelling == that.misspelling
                && this.genderId == that.genderId
                && this.languageId == that.languageId
                && this.dialectId == that.dialectId
                && Objects.equals(this.id, that.id)
                && Objects.equals(this.spelling, that.spelling)
                && Objects.equals(this.inflection, that.inflection)
                && Objects.equals(this.createdAt, that.createdAt)
                && Objects.equals(this.updatedAt, that.updatedAt)
                && Objects.equals(this.definitionsById, that.definitionsById)
                && Objects.equals(this.etymologiesById, that.etymologiesById)
                && Objects.equals(this.genderByGenderId, that.genderByGenderId)
                && Objects.equals(this.languageByLanguageId, that.languageByLanguageId)
                && Objects.equals(this.dialectByDialectId, that.dialectByDialectId)
                && Objects.equals(this.expressionRelationsBy1Id, that.expressionRelationsBy1Id)
                && Objects.equals(this.expressionRelationsBy2Id, that.expressionRelationsBy2Id)
                && Objects.equals(this.mediafilesById, that.mediafilesById);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                id,
                spelling,
                misspelling,
                inflection,
                genderId,
                languageId,
                dialectId,
                createdAt,
                updatedAt,
                definitionsById,
                etymologiesById,
                genderByGenderId,
                languageByLanguageId,
                dialectByDialectId,
                expressionRelationsBy1Id,
                expressionRelationsBy2Id,
                mediafilesById
        );
    }
}
