package com.example.domain.task;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;


public interface Activity {
    LocalDateTime due();
}
