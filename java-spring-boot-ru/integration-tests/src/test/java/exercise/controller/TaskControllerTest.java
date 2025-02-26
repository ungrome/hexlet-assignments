package exercise.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;
import org.instancio.Instancio;
import org.instancio.Select;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import java.util.HashMap;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.datafaker.Faker;
import exercise.repository.TaskRepository;
import exercise.model.Task;

// BEGIN
@SpringBootTest
@AutoConfigureMockMvc
// END
class ApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Faker faker;

    @Autowired
    private ObjectMapper om;

    @Autowired
    private TaskRepository taskRepository;

    @Test
    public void testWelcomePage() throws Exception {
        var result = mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThat(body).contains("Welcome to Spring!");
    }

    @Test
    public void testIndex() throws Exception {
        var result = mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThatJson(body).isArray();
    }

    public class TaskControllerTest {
        private Task task;

        @BeforeEach
        public void beforeEach() {
            var task = Instancio.of(Task.class)
                    .ignore(Select.field(Task::getId))
                    .supply(Select.field(Task::getDescription), () -> faker.lorem().sentence())
                    .supply(Select.field(Task::getTitle), () -> faker.lorem().word())
                    .create();
        }

        // BEGIN
        @Test
        public void testShow() throws Exception {
            String title = task.getTitle();
            String description = task.getDescription();
            taskRepository.save(task);
            var request = get("/tasks/" + task.getId())
                    .contentType(MediaType.APPLICATION_JSON);
            mockMvc.perform(request)
                    .andExpect(status().isOk());

            task = taskRepository.findById(task.getId()).get();
            assertThat(task.getTitle()).isEqualTo(title);
            assertThat(task.getDescription()).isEqualTo(description);
        }

        @Test
        public void testCreate() throws Exception {
            taskRepository.save(task);
            String title = task.getTitle();
            String description = task.getDescription();

            var request = post("/tasks")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsString(task));

            mockMvc.perform(request)
                    .andExpect(status().isCreated());

            task = taskRepository.findById(task.getId()).get();
            assertThat(task.getTitle()).isEqualTo(title);
            assertThat(task.getDescription()).isEqualTo(description);
        }

        @Test
        public void testUpdate() throws Exception {
            taskRepository.save(task);
            var data = new HashMap<>();
            data.put("title", "updated title");
            data.put("description", "updated description");

            var request = put("/tasks/" + task.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    // ObjectMapper конвертирует Map в JSON
                    .content(om.writeValueAsString(data));

            mockMvc.perform(request)
                    .andExpect(status().isOk());
            var updatedTask = taskRepository.findById(task.getId()).get();
            assertThat(updatedTask.getTitle()).isEqualTo(("updated title"));
            assertThat(updatedTask.getDescription()).isEqualTo(("updated description"));
        }

        @Test
        public void testDelete() throws Exception {
            taskRepository.save(task);
            mockMvc.perform(delete("/tasks/" + task.getId()))
                    .andExpect(status().isOk());
            boolean deletedTask = taskRepository.findById(task.getId()).isPresent();
            assertThat(deletedTask).isFalse();
        }
    }
    // END
}
