package com.learning.todo_db.tasks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;

@DataJpaTest
public class TasksRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    @Test
    public void canCreateTask(){
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setName("test");
        taskEntity.setDue_date(new Date());
        taskEntity.setDone(false);
        taskRepository.save(taskEntity);

        Assertions.assertEquals("test", taskRepository.findAll().get(0).getName());
    }
}
