package com.rrm14.tasks.validate;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.web.server.ResponseStatusException;

import com.rrm14.tasks.model.TaskModel;
import com.rrm14.tasks.enums.TaskStatus;

public class TaskValidate {
	
	protected static final String MENSAGEM_NOT_FOUND_CONSULTA = "Nenhum documento encontrado para a consulta solicitada";
	protected static final String MENSAGEM_NOT_FOUND_ALTERACAO = "Nenhum documento encontrado para a alteração solicitada";
	protected static final String MENSAGEM_NOT_FOUND_EXCLUSAO = "Nenhum documento encontrado para a exclusão solicitada";
	protected static final String MENSAGEM_BAD_REQUEST_ALTERACAO_TAREFA = "Dados inválidos para efetuar a alteração da tarefa informada";
	
	protected void trataListaTarefasNotFound(List<TaskModel> tarefas) {
		if(tarefas == null || tarefas.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,MENSAGEM_NOT_FOUND_CONSULTA );
		}
	}
	
	protected void trataTarefaNotFound(TaskModel tarefa, String mensagem) {
		if (tarefa == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, mensagem);
		}
	}
	
	protected TaskModel trataCamposAlteracao(TaskModel tarefa, TaskModel tarefaToUpdate ) {
		
		if(tarefa == null || todosCamposVazios(tarefa)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,MENSAGEM_BAD_REQUEST_ALTERACAO_TAREFA );
		}
		
		if(!ObjectUtils.isEmpty(tarefa.getDescricao())) {
			tarefaToUpdate.setDescricao(tarefa.getDescricao());
		}
		
		if(!ObjectUtils.isEmpty(tarefa.getStatusTarefa())) {
			tarefaToUpdate.setStatusTarefa(tarefa.getStatusTarefa());
			
			if (TaskStatus.CONCLUIDA.equals(tarefa.getStatusTarefa())) {
				tarefaToUpdate.setDataConclusao(LocalDate.now());
			}
		}
		
		return tarefaToUpdate;
			
	}
	
	protected boolean todosCamposVazios(TaskModel tarefa) {
		return ObjectUtils.isEmpty(tarefa.getDescricao()) && ObjectUtils.isEmpty(tarefa.getStatusTarefa());
	}
	

}
