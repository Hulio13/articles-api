package hulio13.articlesApi.domain.entity.author;

import hulio13.articlesApi.domain.exception.IllegalStringLengthException;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(force = true, access = AccessLevel.PROTECTED)
@AttributeOverride(name = "Value", column = @Column(name = "name", nullable = false, unique = true))
public class AuthorName implements Cloneable {
    public static final int MAX_LENGTH = 50;

    public final String Value;

    public AuthorName(String value){
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

        AuthorName that = (AuthorName) o;

        return Value.equals(that.Value);
    }

    @Override
    public int hashCode() {
        return Value.hashCode();
    }

    @Override
    public AuthorName clone() {
        try {
            return (AuthorName) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
