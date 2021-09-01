package com.rrm14.tasks.parser;

import java.util.ArrayList;
import java.util.List;

import com.rrm14.tasks.entity.TaskEntity;
import com.rrm14.tasks.model.TaskModel;
import com.rrm14.tasks.enums.TaskStatus;

public class TaskParser {
	
	public static List<TaskModel> listEntitylToModel(List<TaskEntity> listTaskEntity){
		
		List<TaskModel> listTaskModel = new ArrayList<>();
	
		listTaskEntity.forEach(entity-> {
			
			TaskModel taskModel = new TaskModel();
			
			taskModel.setId(entity.getId());
			taskModel.setDescricao(entity.getDescricao());
			taskModel.setDataCriacao(entity.getDataCriacao());
			taskModel.setDataConclusao(entity.getDataConclusao());
			taskModel.setStatusTarefa(TaskStatus.valueOf(entity.getStatusTarefa()));
			
			listTaskModel.add(taskModel);
			
		});
		
		
		return listTaskModel;
		
	}
	
	public static TaskModel entityToModel(TaskEntity taskEntity){
					
		TaskModel taskModel = new TaskModel();
			
		taskModel.setId(taskEntity.getId());
		taskModel.setDescricao(taskEntity.getDescricao());
		taskModel.setDataCriacao(taskEntity.getDataCriacao());
		taskModel.setDataConclusao(taskEntity.getDataConclusao());
		taskModel.setStatusTarefa(TaskStatus.valueOf(taskEntity.getStatusTarefa()));
			
		return taskModel;
		
	}
	
	public static List<TaskEntity> listModelToEntity(List<TaskModel> listTaskModel){
		
		List<TaskEntity> listTaskEntity = new ArrayList<>();
		
		listTaskModel.forEach(model-> {
			
			TaskEntity taskEntity = new TaskEntity();
			
			taskEntity.setId(model.getId());
			taskEntity.setDescricao(model.getDescricao());
			taskEntity.setDataCriacao(model.getDataCriacao());
			taskEntity.setDataConclusao(model.getDataConclusao());
			taskEntity.setStatusTarefa(model.getStatusTarefa().name());
			
			listTaskEntity.add(taskEntity);
			
		});
		
		
		return listTaskEntity;
		
	}
	
	public static TaskEntity modelToEntity(TaskModel taskModel){
					
		TaskEntity taskEntity = new TaskEntity();
		
		taskEntity.setId(taskModel.getId());
		taskEntity.setDescricao(taskModel.getDescricao());
		taskEntity.setDataCriacao(taskModel.getDataCriacao());
		taskEntity.setDataConclusao(taskModel.getDataConclusao());
		taskEntity.setStatusTarefa(taskModel.getStatusTarefa().name());
			
		return taskEntity;
		
	}


}
