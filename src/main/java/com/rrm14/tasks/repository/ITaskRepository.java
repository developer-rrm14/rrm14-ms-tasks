package com.rrm14.tasks.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rrm14.tasks.entity.TaskEntity;

@Repository
public interface ITaskRepository extends JpaRepository<TaskEntity, Long> {
	List<TaskEntity> findByDescricaoContainingIgnoreCase(String descricao);
}
