package com.example.domain.task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {
    long countByKeyword(String keyword, TaskStatus status);
    
    List<Task> searchByKeyword(String keyword,
                               TaskStatus status,
                               int offset,
                               int limit);
    
    List<Task> findByCreatedBy(String name);
    
    Task save(Task task);
    
    Optional<Task> findOptionalById(Long id);
    
    boolean delete(Task task);
}
