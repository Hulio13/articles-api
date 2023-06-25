package hulio13.articlesApi.infrastructure.data.hibernate;

import hulio13.articlesApi.api.exceptions.NotFoundException;
import hulio13.articlesApi.domain.entity.Author;
import hulio13.articlesApi.domain.repository.AuthorRepository;
import hulio13.articlesApi.infrastructure.data.AlreadyExistException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JPAAuthorRepository implements AuthorRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<Author> getById(long id) {
        return Optional.ofNullable(em.find(Author.class, id));
    }

    @Override
    public List<Author> getAll() {
        return em.createQuery("select a from Author a", Author.class)
                .getResultList();
    }

    @Override
    public void remove(Author entity) {
        var author = getById(entity.getId());
        if (author.isEmpty())
            throw new NotFoundException("Author with id '" + entity.getId() +
                    "' not found");

        em.remove(author.get());
    }

    @Override
    public void removeById(long id) {
        var author = getById(id).orElseThrow(() -> new NotFoundException("Author with id '" + id +
                "' not found"));

        em.remove(author);
    }

    @Override
    public Author create(Author entity) {
        if (entity == null) throw new IllegalArgumentException("Entity is null, Author expected.");

        if (getById(entity.getId()).isPresent())
            throw new AlreadyExistException("Author with id '" + entity.getId() + "' already exist.");

        Optional<Author> byAuthorName = getByAuthorName(entity.getName().Value);
        if (byAuthorName.isPresent())
            throw new AlreadyExistException("Author with name '" + entity.getName() + "' already exist.");

        em.persist(entity);
        return entity;
    }

    @Override
    public Author update(Author entity) {
        var author = getById(entity.getId());
        if (author.isEmpty())
            throw new NotFoundException("Author with id '" + entity.getId() +
                    "' not found");

        return em.merge(entity);
    }

    @Override
    public Optional<Author> getByAuthorName(@NotNull String name) {
        return em.createQuery("select a from Author a where a.name.Value = '" + name + "'", Author.class)
                .getResultStream().findFirst();
    }
}
