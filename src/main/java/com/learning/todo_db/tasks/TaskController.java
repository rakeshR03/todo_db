package com.learning.todo_db.tasks;

import com.learning.todo_db.common.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<TaskDto>> getAll(){
        List<TaskDto> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    @PostMapping("")
    public ResponseEntity<TaskDto> createNewTask(@RequestBody TaskDto taskDto){
        TaskDto savedTask = taskService.createNewTask(taskDto);

        return ResponseEntity.created(URI.create("/tasks/"+savedTask.getId())).body(savedTask);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable long id){
        TaskDto taskDto = taskService.getTaskById(id);
        return ResponseEntity.ok(taskDto);
    }

    @PatchMapping("/{id}")
    public void updateTaskById(){}

    @ExceptionHandler({
            TaskService.TaskInvalidException.class,
            TaskService.TaskAlreadyExistException.class,
            TaskService.TaskNotFoundException.class
    })
    ResponseEntity<ErrorResponseDto> handleError(Exception e){
        HttpStatus errorStatus;
        if(e instanceof TaskService.TaskNotFoundException){
            errorStatus = HttpStatus.NOT_FOUND;
        }

        else if(e instanceof TaskService.TaskAlreadyExistException){
            errorStatus = HttpStatus.CONFLICT;
        }

        else if(e instanceof TaskService.TaskInvalidException){
            errorStatus = HttpStatus.BAD_REQUEST;
        }

        else{
            errorStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return ResponseEntity
                .status(errorStatus)
                .body(new ErrorResponseDto(e.getMessage()));
    }
}


//controller talk to service in DTOS
//Service talk to Repository in Entity