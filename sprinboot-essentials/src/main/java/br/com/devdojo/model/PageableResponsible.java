package br.com.devdojo.model;

import br.com.devdojo.util.CustomSortDeserializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ricardors on 08/08/2018.
 */

//Responsa da API contém um { content:[], page, size, totalElements }
public class PageableResponsible<T> extends PageImpl<T> {
    private Boolean last;
    private Boolean first;
    private int totalPages;

    public PageableResponsible (@JsonProperty("content") List<T> content,
                                @JsonProperty("number") int page,
                                @JsonProperty("size") int size,
                                @JsonProperty("totalElements") long totalElements,
                                @JsonProperty("sort") @JsonDeserialize(using = CustomSortDeserializer.class) Sort sort) {
        super(content, new PageRequest(page, size, sort), totalElements);
    }

    public PageableResponsible() {
        super(new ArrayList<>());
    }

    public Boolean getLast() {
        return last;
    }

    public void setLast(Boolean last) {
        this.last = last;
    }

    public Boolean getFirst() {
        return first;
    }

    public void setFirst(Boolean first) {
        this.first = first;
    }

    @Override
    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
