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
 * Class: DefinitionCategoryPK
 *
 * @author Enver Eskendarov (envereskendarov@gmail.com)
 * @version 1.0
 */
@Getter
@Setter
@NoArgsConstructor
public class DefinitionCategoryPK implements Serializable {

    @Id
    @Column(name = "definition_id", nullable = false)
    private PostgresUUIDType definitionId;
    @Id
    @Column(name = "category_id", nullable = false)
    private PostgresUUIDType categoryId;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final DefinitionCategoryPK that = (DefinitionCategoryPK) o;
        return Objects.equals(this.definitionId, that.definitionId)
                && Objects.equals(this.categoryId, that.categoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                definitionId,
                categoryId
        );
    }
}
