package org.muzi.apiserver.service;

import jakarta.transaction.Transactional;
import org.muzi.apiserver.domain.Todo;
import org.muzi.apiserver.dto.PageRequestDTO;
import org.muzi.apiserver.dto.PageResponseDTO;
import org.muzi.apiserver.dto.TodoDTO;

@Transactional
public interface TodoService {

    TodoDTO get(Long tno);

    Long register(TodoDTO dto);

    void modify(TodoDTO dto);

    void remove(Long tno);

    PageResponseDTO<TodoDTO> getList(PageRequestDTO pageRequestDTO);


    default TodoDTO entityToDTO(Todo todo){
        TodoDTO todoDTO =
                TodoDTO.builder()
                        .tno(todo.getTno())
                        .title(todo.getTitle())
                        .content(todo.getContent())
                        .complete(todo.isComplete())
                        .dueDate(todo.getDueDate())
                        .build();
        return  todoDTO;
    }

    default Todo dtoToEntity(TodoDTO todoDTO){
        return Todo.builder()
                    .tno(todoDTO.getTno())
                    .title(todoDTO.getTitle())
                    .content(todoDTO.getContent())
                    .complete(todoDTO.isComplete())
                    .dueDate(todoDTO.getDueDate())
                    .build();
    }

}
