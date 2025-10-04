package com.example.notes;

import com.example.notes.model.Note;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class NoteControllerTest {

    private final TestRestTemplate rest = new TestRestTemplate();

    @Test
    void listNotes_returnsSeedData() {
        ResponseEntity<Note[]> resp = rest.getForEntity("http://localhost:8082/api/notes", Note[].class);
        assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(resp.getBody()).isNotNull();
        assertThat(resp.getBody().length).isGreaterThanOrEqualTo(2);
    }
}
