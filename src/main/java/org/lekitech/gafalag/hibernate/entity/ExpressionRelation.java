package org.lekitech.gafalag.hibernate.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
 * Class: ExpressionRelation
 *
 * @author Enver Eskendarov (envereskendarov@gmail.com)
 * @version 1.0
 */
@Getter
@Setter
@NoArgsConstructor
@Entity(name = "expression_relation")
@IdClass(ExpressionRelationPK.class)
public class ExpressionRelation {

    @Id
    @Column(name = "expression_1_id", nullable = false)
    private PostgresUUIDType expression1Id;
    @Id
    @Column(name = "expression_2_id", nullable = false)
    private PostgresUUIDType expression2Id;
    @Basic
    @Column(name = "relation_type_id", nullable = false)
    private int relationTypeId;
    @Basic
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    @ManyToOne
    @JoinColumn(name = "expression_1_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private Expression expressionByExpression1Id;
    @ManyToOne
    @JoinColumn(name = "expression_2_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private Expression expressionByExpression2Id;
    @ManyToOne
    @JoinColumn(name = "relation_type_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private RelationType relationTypeByRelationTypeId;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ExpressionRelation that = (ExpressionRelation) o;
        return this.relationTypeId == that.relationTypeId
                && Objects.equals(this.expression1Id, that.expression1Id)
                && Objects.equals(this.expression2Id, that.expression2Id)
                && Objects.equals(this.createdAt, that.createdAt)
                && Objects.equals(this.expressionByExpression1Id, that.expressionByExpression1Id)
                && Objects.equals(this.expressionByExpression2Id, that.expressionByExpression2Id)
                && Objects.equals(this.relationTypeByRelationTypeId, that.relationTypeByRelationTypeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                expression1Id,
                expression2Id,
                relationTypeId,
                createdAt,
                expressionByExpression1Id,
                expressionByExpression2Id,
                relationTypeByRelationTypeId
        );
    }
}
