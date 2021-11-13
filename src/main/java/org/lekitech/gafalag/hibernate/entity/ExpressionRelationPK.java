package org.lekitech.gafalag.hibernate.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.type.PostgresUUIDType;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

/**
 * Date: 13.11.2021
 * Project: GafalagParser
 * Class: ExpressionRelationPK
 *
 * @author Enver Eskendarov (envereskendarov@gmail.com)
 * @version 1.0
 */
@Getter
@Setter
@NoArgsConstructor
public class ExpressionRelationPK implements Serializable {

    @Id
    @Column(name = "expression_1_id", nullable = false)
    private PostgresUUIDType expression1Id;
    @Id
    @Column(name = "expression_2_id", nullable = false)
    private PostgresUUIDType expression2Id;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ExpressionRelationPK that = (ExpressionRelationPK) o;
        return Objects.equals(this.expression1Id, that.expression1Id)
                && Objects.equals(this.expression2Id, that.expression2Id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                expression1Id,
                expression2Id
        );
    }
}
