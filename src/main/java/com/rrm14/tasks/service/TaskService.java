package com.rrm14.tasks.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.rrm14.tasks.entity.TaskEntity;
import com.rrm14.tasks.enums.TaskStatus;
import com.rrm14.tasks.model.TaskModel;
import com.rrm14.tasks.parser.TaskParser;
import com.rrm14.tasks.repository.ITaskRepository;
import com.rrm14.tasks.validate.TaskValidate;


@Service
public class TaskService extends TaskValidate {
	
	@Autowired
	private ITaskRepository tarefaRepository;
	
	@ResponseStatus(HttpStatus.OK)
	public List<TaskModel> findAll(){
		List<TaskEntity> tarefasEntity = tarefaRepository.findAll();
		List<TaskModel> tarefasModel = TaskParser.listEntitylToModel(tarefasEntity);
		trataListaTarefasNotFound(tarefasModel);
		return tarefasModel;
	}
	
	@ResponseStatus(HttpStatus.OK)
	public TaskModel findById(Long id) {
		TaskEntity tarefaEntity = tarefaRepository.findById(id).orElse(null);
		TaskModel tarefaModel = TaskParser.entityToModel(tarefaEntity);
		trataTarefaNotFound(tarefaModel, MENSAGEM_NOT_FOUND_CONSULTA);
		return tarefaModel;
	}
	
	@ResponseStatus(HttpStatus.OK)
	public List<TaskModel> findByDescricao(String descricao){
		List<TaskEntity> tarefasEntity = tarefaRepository.findByDescricaoContainingIgnoreCase(descricao);
		List<TaskModel> tarefasModel = TaskParser.listEntitylToModel(tarefasEntity);
		trataListaTarefasNotFound(tarefasModel);
		return tarefasModel; 
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	public TaskModel save(TaskModel tarefa) {
		tarefa.setDataCriacao(LocalDate.now());
		
		if (ObjectUtils.isEmpty(tarefa.getStatusTarefa())) {
			tarefa.setStatusTarefa(TaskStatus.A_FAZER);
		}	
		
		TaskEntity tarefaEntity = TaskParser.modelToEntity(tarefa);
		tarefaEntity = tarefaRepository.save(tarefaEntity);
		tarefa.setId(tarefaEntity.getId());
				
		return tarefa;
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(Long id, TaskModel tarefa) {
		TaskEntity tarefaToUpdate = tarefaRepository.findById(id).orElse(null);
		TaskModel tarefaModelUpdate = TaskParser.entityToModel(tarefaToUpdate);
		
		trataTarefaNotFound(tarefaModelUpdate, MENSAGEM_NOT_FOUND_ALTERACAO);
		tarefaRepository.save(TaskParser.modelToEntity(trataCamposAlteracao(tarefa, tarefaModelUpdate)));
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteById(Long id) {
		TaskEntity tarefaToDelete = tarefaRepository.findById(id).orElse(null);
		TaskModel tarefaModelDelete = TaskParser.entityToModel(tarefaToDelete);
		trataTarefaNotFound(tarefaModelDelete, MENSAGEM_NOT_FOUND_EXCLUSAO);
		tarefaRepository.deleteById(id);
	}
}
