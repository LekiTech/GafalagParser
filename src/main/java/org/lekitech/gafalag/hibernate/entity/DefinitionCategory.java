package org.lekitech.gafalag.hibernate.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.type.PostgresUUIDType;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Date: 13.11.2021
 * Project: GafalagParser
 * Class: DefinitionCategory
 *
 * @author Enver Eskendarov (envereskendarov@gmail.com)
 * @version 1.0
 */
@Getter
@Setter
@NoArgsConstructor
@Entity(name = "definition_category")
@IdClass(DefinitionCategoryPK.class)
public class DefinitionCategory {

    @Id
    @Column(name = "definition_id", nullable = false)
    private PostgresUUIDType definitionId;
    @Id
    @Column(name = "category_id", nullable = false)
    private PostgresUUIDType categoryId;
    @Basic
    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    @ManyToOne
    @JoinColumn(name = "definition_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private Definition definitionByDefinitionId;
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private Category categoryByCategoryId;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final DefinitionCategory that = (DefinitionCategory) o;
        return Objects.equals(this.definitionId, that.definitionId)
                && Objects.equals(this.categoryId, that.categoryId)
                && Objects.equals(this.createdAt, that.createdAt)
                && Objects.equals(this.definitionByDefinitionId, that.definitionByDefinitionId)
                && Objects.equals(this.categoryByCategoryId, that.categoryByCategoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                definitionId,
                categoryId,
                createdAt,
                definitionByDefinitionId,
                categoryByCategoryId
        );
    }
}
