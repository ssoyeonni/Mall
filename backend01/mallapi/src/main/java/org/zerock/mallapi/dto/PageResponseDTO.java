package org.zerock.mallapi.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
 * 페이지 처리를 위한 응답 DTO
 */
@Data
public class PageResponseDTO<E> {

    private List<E> dtoList;
    private List<Integer> pageNumList;
    private PageRequestDTO pageRequestDTO;
    private boolean prev, next;     // 이전, 다음 페이지 이동
    private int totalCount, prevPage, nextPage, totalPage, current;        // 총 데이터 개수,

    @Builder(builderMethodName = "withAll")
    public PageResponseDTO(List<E> dtoList, PageRequestDTO pageRequestDTO, long total) {

        this.dtoList = dtoList;
        this.pageRequestDTO = pageRequestDTO;
        this.totalCount = (int)total;

        // 끝 페이지 계산( 1~10, 11~20 에서 10, 20 ...)
        int end = (int)(Math.ceil(pageRequestDTO.getPage() / 10.0)) * 10;   // 현재 페이지/10.0

        // 시작 페이지
        int start = end - 9;

        // 마지막 페이지( 데이터가 끝나는 페이지)
        int last = (int)(Math.ceil(totalCount / (double)pageRequestDTO.getSize()));

        // 마지막 페이지가 끝페이지 보다 작으면(end > lsat)
        end = end > last ? last : end;

        this.prev = start > 1;  // 맞으면 true 틀리면 false
        this.next = totalCount > end * pageRequestDTO.getSize();

        this.pageNumList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());

        this.prevPage = prev ? start - 1 : 0;
        this.nextPage = next ? end + 1 : 0;




    }


}
