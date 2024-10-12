package com.practice.Helper;


import com.practice.response.PageableResponse;
import org.modelmapper.ModelMapper;

import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class PageHelper {
    public static <U, V> PageableResponse<U> getPageableResponse(Page<V> page, Class<U> type) {
        PageableResponse<U> pageableResponse = new PageableResponse<>();

        List<U> userDtoList = page.getContent().stream().map(user -> new ModelMapper().map(user, type)).collect(Collectors.toList());
        pageableResponse.setList(userDtoList);

        pageableResponse.setTotalPages(page.getTotalPages());
        pageableResponse.setLastPage(page.isLast());
        pageableResponse.setTotalElement(page.getTotalElements());
        pageableResponse.setPageSize(page.getSize());
        pageableResponse.setPageNumber(page.getNumber());


        return pageableResponse;
    }
}