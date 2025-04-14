package org.muzi.apiserver.service;


import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.muzi.apiserver.dto.TodoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
@Log4j2
public class TodoServiceTests {

    @Autowired
    TodoService todoService;

    @Test
    public void testGet(){

        Long tno = 50L;
        log.info(todoService.get(tno));

    }

    @Test
    public void testRegister(){
        TodoDTO todoDTO = TodoDTO.builder()
                .title("Title ...... //")
                .content("Content / / / /.... ")
                .dueDate(LocalDate.of(2025,04,14))
                .build();

        log.info(todoService.register(todoDTO));
    }

}
