package com.example.domain;

import java.util.List;

public interface TaskRepository {
    long countByKeyword(String keyword, Task.Status status);
    
    List<Task> searchByKeyword(String keyword,
                               Task.Status status,
                               int offset,
                               int limit);
    
    List<Task> findByCreatedBy(String name);
    
    Task save(Task task);
}
