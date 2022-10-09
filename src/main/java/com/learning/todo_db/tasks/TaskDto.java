package com.learning.todo_db.tasks;

import lombok.Data;
import org.springframework.lang.Nullable;

import java.util.Date;

@Data
public class TaskDto {
    Long id;

    @Nullable
    String name;

    @Nullable
    Date due_date;

    @Nullable
    Boolean done;
}
