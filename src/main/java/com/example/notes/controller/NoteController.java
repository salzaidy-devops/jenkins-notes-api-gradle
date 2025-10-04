package com.example.notes.controller;

import com.example.notes.model.Note;
import com.example.notes.service.NoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteController {
    private final NoteService svc;

    public NoteController(NoteService svc) {
        this.svc = svc;
    }

    @GetMapping
    public List<Note> all() {
        return svc.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Note> one(@PathVariable Long id) {
        return svc.findById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Note> create(@RequestBody Note n) {
        Note saved = svc.create(n);
        return ResponseEntity.created(URI.create("/api/notes/" + saved.getId())).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Note> update(@PathVariable Long id, @RequestBody Note n) {
        return svc.update(id, n).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return svc.delete(id) ? ResponseEntity.noContent().build()
                              : ResponseEntity.notFound().build();
    }
}
