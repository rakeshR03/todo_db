package com.learning.todo_db.tasks;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {
    private TaskRepository taskRepository;
    private ModelMapper modelMapper;

    public TaskService(TaskRepository taskRepository, ModelMapper modelMapper){
        this.taskRepository = taskRepository;
        this.modelMapper = modelMapper;
    }

    public List<TaskDto> getAllTasks(){
        return taskRepository.findAll()
                .stream()
                .map(x -> modelMapper.map(x,TaskDto.class))
                .collect(Collectors.toList());

    }

    public TaskDto getTaskById(Long id){
        TaskEntity task = taskRepository.findById(id).orElse(null);
        //handle error for null
        if(task == null){
            throw new TaskNotFoundException(id);
        }
        return modelMapper.map(task, TaskDto.class);
    }

    public TaskDto createNewTask(TaskDto taskDto){

        if(taskDto.getDue_date() != null && taskDto.getDue_date().before( new Date())){
            throw new TaskInvalidException("Due date must be in future");
        }
        TaskEntity task = modelMapper.map(taskDto, TaskEntity.class);

        TaskEntity savedTask = taskRepository.save(task);
        return modelMapper.map(savedTask, TaskDto.class);
    }

    static class TaskNotFoundException extends IllegalArgumentException{
        public TaskNotFoundException(Long id){
            super("Task with "+id+" not found");
        }
    }

    static class TaskAlreadyExistException extends IllegalArgumentException{
        public TaskAlreadyExistException(Long id){
            super("Task with "+id+" already exist");
        }
    }

    static class TaskInvalidException extends IllegalArgumentException{
        public TaskInvalidException(String message){
            super(message);
        }
    }
}
