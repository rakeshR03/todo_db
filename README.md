# todo

## API Docs

**GET** '/tasks' 📄
>get a list of all task
```json
[
  {
    "id": 1,
    "title": "this is something I need to complete."
    "due_date": 20220205,
    "completed": false
  },
  {
    "id": 2,
    "title": "this is also something I need to complete."
    "due_date": 20220206,
    "completed": true
  }
]
```

**GET** '/tasks/{id}'
>get a task by id
```json
[
  {
    "id": 1,
    "title": "this is something I need to complete."
    "due_date": 20220205,
    "completed": false,
    "notes":[
      {
        "id": 1,
        "title": "abc"
        "body": "lorem ipsum lorem ipsum."
      }
    ]
  }
]
```
| query parameter | definition |
|-----------------|------------|
| 'notes'         | 'if true, include notes in result' |

example -
'/tasks/4?notes=true' - fetch details of task id = 4, including its notes.

**GET** '/tasks/{id}/notes'
>get all notes inside a task
```json
[
  {
    "id": 1,
    "title": "abc"
    "body": "lorem ipsum lorem ipsum."
  },
  {
    "id": 2,
    "title": "xyz"
    "body": "lorem ipsum lorem ipsum."
  }
]
```

### References

If 📄 is used, it means the endpoint supports '?size=10&page=2' type of paging


## Entities
![image](https://user-images.githubusercontent.com/85061401/188067760-7225ff12-2a2f-444c-9ec5-00b608aeb2ff.png)
