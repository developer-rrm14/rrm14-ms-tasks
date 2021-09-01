package com.rrm14.tasks.model;

import java.time.LocalDate;

import com.rrm14.tasks.enums.TaskStatus;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TaskModel {
	
	private Long id;
	private String descricao;
	private LocalDate dataCriacao;
	private LocalDate dataConclusao;
	private TaskStatus statusTarefa;

}
