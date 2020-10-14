package ru.geekbrains.controller.requesthandler;

<<<<<<< HEAD
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
=======
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
>>>>>>> upstream/develop
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.model.Task;
import ru.geekbrains.service.requesthandler.TaskService;

@RequestMapping("/")
@RestController()
@AllArgsConstructor
@Log
public class RequestHandler {

    private final TaskService taskService;

    @PostMapping()
    public ResponseEntity<String> getTask(@RequestBody Task task) {
        if(isBlank(task)) {
            return new ResponseEntity<>("error: object is empty", HttpStatus.BAD_REQUEST);
        }

        if(taskService.add(task)) {
            return new ResponseEntity<>("ok", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("error: duplicate", HttpStatus.BAD_REQUEST);
        }
    }

    public boolean isBlank(Task task){
        return task.getTaskId() == null ||
                task.getCity() == null ||
                task.getCounty() == null ||
                task.getTaskId().equals("") ||
                task.getCounty().equals("") ||
                task.getCity().equals("");
=======
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.exception.DuplicateTaskException;
import ru.geekbrains.exception.VariableBlankException;
import ru.geekbrains.model.ResponseMessage;
import ru.geekbrains.model.Task;
import ru.geekbrains.service.requesthandler.RequestHandlerService;
import ru.geekbrains.service.requesthandler.TaskService;

@RestController("/")
@RequiredArgsConstructor
@Slf4j
public class RequestHandler {

    private final RequestHandlerService requestHandlerService;

    @PostMapping()
    public ResponseEntity<Object> addTask(@RequestBody @NonNull Task task) throws Exception {
        return requestHandlerService.addTask(task);

>>>>>>> upstream/develop
    }
}
