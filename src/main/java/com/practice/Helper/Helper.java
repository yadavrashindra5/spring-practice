package com.practice.Helper;

import com.practice.entities.Student;
import com.practice.response.PageableResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public class Helper {
    public static <U,V>PageableResponse<U> getPageableResponse(Page<U>page,Class<V> type){
        List<U> listOfStudents = page.getContent();
        PageableResponse<U>pageableResponse=new PageableResponse<U>();
        pageableResponse.setList(listOfStudents);
        pageableResponse.setPageNumber(page.getNumber());
        pageableResponse.setPageSize(page.getSize());
        pageableResponse.setLastPage(page.isLast());
        pageableResponse.setTotalPages(page.getTotalPages());
        pageableResponse.setTotalElement(page.getTotalElements());
        return pageableResponse;
    }
}
