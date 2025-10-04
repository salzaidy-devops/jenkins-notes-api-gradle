package com.example.notes.service;

import com.example.notes.model.Note;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class NoteService {
    private final Map<Long, Note> store = new LinkedHashMap<>();
    private final AtomicLong seq = new AtomicLong(0);

    public NoteService() {
        create(new Note(null, "Welcome", "Your first note"));
        create(new Note(null, "Jenkins", "CI/CD practice with Gradle"));
    }

    public List<Note> findAll() { return new ArrayList<>(store.values()); }

    public Optional<Note> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    public Note create(Note n) {
        long id = seq.incrementAndGet();
        n.setId(id);
        store.put(id, n);
        return n;
    }

    public Optional<Note> update(Long id, Note n) {
        if (!store.containsKey(id)) return Optional.empty();
        n.setId(id);
        store.put(id, n);
        return Optional.of(n);
    }

    public boolean delete(Long id) {
        return store.remove(id) != null;
    }
}
