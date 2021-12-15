package com.example.poc.ms2.service;

import com.example.poc.ms2.model.BookDto;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@FeignClient(name = "ms-1")
public interface BookClient {

    @Retry(name="book", fallbackMethod = "getDefaultFallback")
    @RequestMapping(method = RequestMethod.GET, path = "/book")
    List<BookDto> getAllBook();

    default List<BookDto> getDefaultFallback(Exception e) {
        return getDefaultBook("1");
    }

    @Cacheable("book")
    default List<BookDto> getDefaultBook(String v) {
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("1");
        return new ArrayList() {{
            add(new BookDto().title("Harry Potter and the Philosopher's Stone").author("J. K. Rowling").id(1L));
        }};
    }

}
