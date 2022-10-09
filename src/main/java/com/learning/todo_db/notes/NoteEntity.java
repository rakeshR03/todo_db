package com.learning.todo_db.notes;

import com.learning.todo_db.common.BaseEntity;
import com.learning.todo_db.tasks.TaskEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name="notes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NoteEntity extends BaseEntity {

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Column(name = "body", nullable = false, length = 1000)
    private String body;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "task_id")
    private TaskEntity task;

}
