package org.zerock.mallapi.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.zerock.mallapi.dto.TodoDTO;
import org.zerock.mallapi.entity.Todo;
import org.zerock.mallapi.repository.TodoRepository;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;

    @Override
    public TodoDTO get(Long tno) {

        Optional<Todo> result = todoRepository.findById(tno);    // 조회

        Todo todo = result.orElseThrow();

        return entityToDTO(todo);
    }

}
