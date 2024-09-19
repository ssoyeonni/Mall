package org.zerock.mallapi.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.zerock.mallapi.entity.Todo;

import java.time.LocalDate;
import java.util.Optional;

@SpringBootTest
@Log4j2
public class TodoRepositoryTests {

    @Autowired  // Autowired해줘야 TodoRepository에 있는 거 인식(주입) 가능
    private TodoRepository todoRepository;

    @Test
    public void test1() {

        Assertions.assertNotNull(todoRepository);   // todoRepository가 Null이 아니면 test성공

        log.info(todoRepository.getClass().getName());

    }

    @Test
    public void testInsert() {
        Todo todo = Todo.builder()
                .title("Title")
                .content("Content...")
                .dueDate(LocalDate.of(2025, 12, 31))
                .build();

        Todo result = todoRepository.save(todo);    // 데이터베이스에 저장?

        log.info(result);
    }

    @Test
    public void testRead() {
        Long tno = 1L;

        Optional<Todo> result = todoRepository.findById(tno);   // Null 체크하기 위해서 return 타입 Optional로

        Todo todo = result.orElseThrow();   // 문제 있으면 던지고, 아니면 투두 끄집어 내줘

        log.info(todo);
    }

    @Test
    public void testUpdate() {
        // 먼저 로딩하고 엔티티 객체를 변경 /setter 사용
        Long tno = 1L;

        Optional<Todo> result = todoRepository.findById(tno);   // Null 체크하기 위해서 return 타입 Optional로

        Todo todo = result.orElseThrow();   // 문제 있으면 던지고, 아니면 투두 끄집어 내줘

        todo.setTitle("Update Title");
        todo.setContent("Updated content");
        todo.setComplete(true);

        todoRepository.save(todo);

    }

    @Test
    public void testPaging() {
        // 페이지 번호는 0부터 시작
        Pageable pageable = PageRequest.of(0, 10, Sort.by("tno").descending());
        // 파라미터가 pageable이면 무조건 page 타입으로 나옴(?)

        Page<Todo> result = todoRepository.findAll(pageable);

        log.info(result.getTotalElements());

        log.info(result.getContent());

    }


}
