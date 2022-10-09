package com.learning.todo_db.tasks;

import com.learning.todo_db.common.BaseEntity;
import com.learning.todo_db.notes.NoteEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Entity(name = "tasks")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskEntity extends BaseEntity {

    @Column(name="name", nullable = false)
    private String name;

    @Column(name = "due_date", nullable = false )
    private Date due_date;

    @Column(name = "done", nullable = false, columnDefinition = "boolean default false")
    private boolean done;

    @OneToMany(mappedBy = "task",cascade = CascadeType.ALL)
    private List<NoteEntity> notes;
}
