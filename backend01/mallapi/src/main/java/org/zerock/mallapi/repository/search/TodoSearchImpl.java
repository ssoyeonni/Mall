package org.zerock.mallapi.repository.search;

import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.zerock.mallapi.entity.QTodo;
import org.zerock.mallapi.entity.Todo;

/*
query dsl 세팅
 */

@Log4j2     // 항상 log 부터 찍고 개발 ㄱㄱ
public class TodoSearchImpl extends QuerydslRepositorySupport implements TodoSearch {

    public TodoSearchImpl() {
        super(Todo.class);
    }

    @Override
    public Page<Todo> search1() {

        log.info("search1................");
        
        QTodo todo = QTodo.todo;

        JPQLQuery<Todo> query = from(todo);
        
        query.where(todo.title.contains("1"));

        Pageable pageable = PageRequest.of(1, 10, Sort.by("tno").descending());

        this.getQuerydsl().applyPagination(pageable, query);
        
        query.fetch();  // 쿼리 실행( 목록 데이터 가져올 때)

        query.fetchCount();


        return null;
    }
}
