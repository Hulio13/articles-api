package hulio13.articlesApi.application.service;

import hulio13.articlesApi.domain.entity.Author;
import hulio13.articlesApi.domain.repository.AuthorRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorService {
    private final AuthorRepository repository;

    public AuthorService(AuthorRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void add(Author author){
        repository.create(author);
    }

    @Transactional
    public void update(Author author){
        repository.create(author);
    }


    public Optional<Author> getById(long id){
        return repository.getById(id);
    }
}
