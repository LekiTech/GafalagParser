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
 * Class: Definition
 *
 * @author Enver Eskendarov (envereskendarov@gmail.com)
 * @version 1.0
 */
@Getter
@Setter
@NoArgsConstructor
@Entity(name = "definition")
public class Definition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private PostgresUUIDType id;
    @Basic
    @Column(name = "expression_id", nullable = false)
    private PostgresUUIDType expressionId;
    @Basic
    @Column(name = "part_of_speech_id")
    private int partOfSpeechId;
    @Basic
    @Column(name = "language_id", nullable = false)
    private int languageId;
    @Basic
    @Column(name = "source_id", nullable = false)
    private PostgresUUIDType sourceId;
    @Basic
    @Column(name = "definition_text", nullable = false, length = -1)
    private String definitionText;
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
    @JoinColumn(name = "part_of_speech_id", referencedColumnName = "id", insertable = false, updatable = false)
    private PartOfSpeech partOfSpeechByPartOfSpeechId;
    @ManyToOne
    @JoinColumn(name = "language_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private Language languageByLanguageId;
    @ManyToOne
    @JoinColumn(name = "source_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private Source sourceBySourceId;
    @OneToMany(mappedBy = "definitionByDefinitionId")
    private Collection<DefinitionCategory> definitionCategoriesById;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Definition that = (Definition) o;
        return this.partOfSpeechId == that.partOfSpeechId
                && this.languageId == that.languageId
                && Objects.equals(this.id, that.id)
                && Objects.equals(this.expressionId, that.expressionId)
                && Objects.equals(this.sourceId, that.sourceId)
                && Objects.equals(this.definitionText, that.definitionText)
                && Objects.equals(this.createdAt, that.createdAt)
                && Objects.equals(this.updatedAt, that.updatedAt)
                && Objects.equals(this.expressionByExpressionId, that.expressionByExpressionId)
                && Objects.equals(this.partOfSpeechByPartOfSpeechId, that.partOfSpeechByPartOfSpeechId)
                && Objects.equals(this.languageByLanguageId, that.languageByLanguageId)
                && Objects.equals(this.sourceBySourceId, that.sourceBySourceId)
                && Objects.equals(this.definitionCategoriesById, that.definitionCategoriesById);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                id,
                expressionId,
                partOfSpeechId,
                languageId,
                sourceId,
                definitionText,
                createdAt,
                updatedAt,
                expressionByExpressionId,
                partOfSpeechByPartOfSpeechId,
                languageByLanguageId,
                sourceBySourceId,
                definitionCategoriesById
        );
    }
}
