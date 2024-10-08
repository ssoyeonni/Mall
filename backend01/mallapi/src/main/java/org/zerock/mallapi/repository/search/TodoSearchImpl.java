package org.zerock.mallapi.repository.search;

import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.zerock.mallapi.dto.PageRequestDTO;
import org.zerock.mallapi.entity.QTodo;
import org.zerock.mallapi.entity.Todo;

import java.util.List;

/*
query dsl 세팅
 */

@Log4j2     // 항상 log 부터 찍고 개발 ㄱㄱ
public class TodoSearchImpl extends QuerydslRepositorySupport implements TodoSearch {

    public TodoSearchImpl() {
        super(Todo.class);
    }

    @Override
    public Page<Todo> search1(PageRequestDTO pageRequestDTO) {

        log.info("search1................");
        
        QTodo todo = QTodo.todo;

        JPQLQuery<Todo> query = from(todo);

        Pageable pageable = PageRequest.of(pageRequestDTO.getPage() -1,
                pageRequestDTO.getSize(), Sort.by("tno").descending());

        this.getQuerydsl().applyPagination(pageable, query);
        
        List<Todo> list = query.fetch();  // 쿼리 실행( 목록 데이터 가져올 때)

        long total = query.fetchCount();


        return new PageImpl<>(list, pageable, total);
    }
}
