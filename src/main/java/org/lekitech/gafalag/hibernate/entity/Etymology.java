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
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Date: 13.11.2021
 * Project: GafalagParser
 * Class: Etymology
 *
 * @author Enver Eskendarov (envereskendarov@gmail.com)
 * @version 1.0
 */
@Getter
@Setter
@NoArgsConstructor
@Entity(name = "etymology")
public class Etymology {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private PostgresUUIDType id;
    @Basic
    @Column(name = "expression_id", nullable = false)
    private PostgresUUIDType expressionId;
    @Basic
    @Column(name = "language_id", nullable = false)
    private int languageId;
    @Basic
    @Column(name = "dialect_id")
    private int dialectId;
    @Basic
    @Column(name = "etymology_text", nullable = false, length = -1)
    private String etymologyText;
    @Basic
    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    @Basic
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
    @ManyToOne
    @JoinColumn(name = "expression_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private Expression expressionByExpressionId;
    @ManyToOne
    @JoinColumn(name = "language_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private Language languageByLanguageId;
    @ManyToOne
    @JoinColumn(name = "dialect_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private Dialect dialectByDialectId;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Etymology that = (Etymology) o;
        return this.languageId == that.languageId
                && this.dialectId == that.dialectId
                && Objects.equals(this.id, that.id)
                && Objects.equals(this.expressionId, that.expressionId)
                && Objects.equals(this.etymologyText, that.etymologyText)
                && Objects.equals(this.createdAt, that.createdAt)
                && Objects.equals(this.updatedAt, that.updatedAt)
                && Objects.equals(this.expressionByExpressionId, that.expressionByExpressionId)
                && Objects.equals(this.languageByLanguageId, that.languageByLanguageId)
                && Objects.equals(this.dialectByDialectId, that.dialectByDialectId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                id,
                expressionId,
                languageId,
                dialectId,
                etymologyText,
                createdAt,
                updatedAt,
                expressionByExpressionId,
                languageByLanguageId,
                dialectByDialectId
        );
    }
}
