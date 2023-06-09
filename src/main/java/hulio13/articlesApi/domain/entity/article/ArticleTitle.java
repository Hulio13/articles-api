package hulio13.articlesApi.domain.entity.article;

import hulio13.articlesApi.domain.exception.IllegalStringLengthException;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(force = true, access = AccessLevel.PROTECTED)
@AttributeOverride(name = "Value", column = @Column(name = "title", nullable = false))
public class ArticleTitle implements Cloneable {
    public static final int MAX_LENGTH = 150;

    public final String Value;

    public ArticleTitle(String value){
        if (value.length() > MAX_LENGTH){
            throw new IllegalStringLengthException(
                    "Name length should be less or equal than " + MAX_LENGTH);
        }

        Value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArticleTitle that = (ArticleTitle) o;

        return Value.equals(that.Value);
    }

    @Override
    public int hashCode() {
        return Value.hashCode();
    }

    @Override
    public ArticleTitle clone() {
        try {
            return (ArticleTitle) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
