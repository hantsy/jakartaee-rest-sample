package com.example.domain.task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {
    long countByKeyword(String keyword, Task.Status status);
    
    List<Task> searchByKeyword(String keyword,
                               Task.Status status,
                               int offset,
                               int limit);
    
    List<Task> findByCreatedBy(String name);
    
    Task save(Task task);
    
    Optional<Task> findOptionalById(Long id);
    
    boolean delete(Task task);
}
