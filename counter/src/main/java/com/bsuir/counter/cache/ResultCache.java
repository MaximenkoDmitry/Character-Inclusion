package com.bsuir.counter.cache;

import com.bsuir.counter.domain.DataInput;
import com.bsuir.counter.models.Post;
import com.bsuir.counter.repo.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Slf4j
public class ResultCache {
    private static final Map<DataInput, Long> resultMap = new HashMap<>();

    @Autowired
    private PostRepository postRepository = new PostRepository() {
        @Override
        public <S extends Post> S save(S s) {
            return null;
        }

        @Override
        public <S extends Post> Iterable<S> saveAll(Iterable<S> iterable) {
            return null;
        }

        @Override
        public Optional<Post> findById(Long aLong) {
            return Optional.empty();
        }

        @Override
        public boolean existsById(Long aLong) {
            return false;
        }

        @Override
        public Iterable<Post> findAll() {
            return null;
        }

        @Override
        public Iterable<Post> findAllById(Iterable<Long> iterable) {
            return null;
        }

        @Override
        public long count() {
            return 0;
        }

        @Override
        public void deleteById(Long aLong) {

        }

        @Override
        public void delete(Post post) {

        }

        @Override
        public void deleteAll(Iterable<? extends Post> iterable) {

        }

        @Override
        public void deleteAll() {

        }
    };

    private Iterable<Post> posts = postRepository.findAll();

    public PostRepository getPostRepository() {
        return postRepository;
    }

    public void setPostRepository(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Optional<Long> getResult(final DataInput dataInput) {
        Long result = resultMap.get(dataInput);
        if (result == null) {
            return Optional.empty();
        }
        log.debug("Cache returned a result: " + result);
        return Optional.of(result);
    }

    public void putResult(final DataInput dataInput, final Long result) {
        log.debug("Cache received a result: " + result);
        resultMap.put(dataInput, result);
    }
}
