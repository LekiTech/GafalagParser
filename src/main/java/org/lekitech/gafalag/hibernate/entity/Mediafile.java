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
 * Class: Mediafile
 *
 * @author Enver Eskendarov (envereskendarov@gmail.com)
 * @version 1.0
 */
@Getter
@Setter
@NoArgsConstructor
@Entity(name = "mediafile")
public class Mediafile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "expression_id", nullable = false)
    private PostgresUUIDType expressionId;
    @Basic
    @Column(name = "mediatype", nullable = false)
    private MediaType mediatype;
    @Basic
    @Column(name = "url", nullable = false, length = -1)
    private String url;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Mediafile that = (Mediafile) o;
        return this.id == that.id
                && Objects.equals(this.expressionId, that.expressionId)
                && this.mediatype == that.mediatype
                && Objects.equals(this.url, that.url)
                && Objects.equals(this.createdAt, that.createdAt)
                && Objects.equals(this.updatedAt, that.updatedAt)
                && Objects.equals(this.expressionByExpressionId, that.expressionByExpressionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                id,
                expressionId,
                mediatype,
                url,
                createdAt,
                updatedAt,
                expressionByExpressionId
        );
    }
}
