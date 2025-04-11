package org.muzi.apiserver.repository.search;

import org.muzi.apiserver.domain.Todo;
import org.springframework.data.domain.Page;

public interface TodoSearch {
    Page<Todo> search1();
}
