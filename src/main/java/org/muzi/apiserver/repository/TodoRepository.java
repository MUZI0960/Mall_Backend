package org.muzi.apiserver.repository;

import org.muzi.apiserver.domain.Todo;
import org.muzi.apiserver.repository.search.TodoSearch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long>, TodoSearch {

}
