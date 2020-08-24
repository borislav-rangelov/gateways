package com.example.demo.model;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.List;

@AllArgsConstructor
public class PageModel<T> {
    private final Page<T> page;

    public List<T> getContent() {
        return page.getContent();
    }

    public int getNumber() {
        return page.getNumber();
    }

    public int getSize() {
        return page.getSize();
    }

    public long getTotalElements() {
        return page.getTotalElements();
    }

    public int getTotalPages() {
        return page.getTotalPages();
    }
}
