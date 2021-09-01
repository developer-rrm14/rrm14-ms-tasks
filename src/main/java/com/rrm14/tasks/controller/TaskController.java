package com.rrm14.tasks.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rrm14.tasks.model.TaskModel;
import com.rrm14.tasks.service.TaskService;


@RestController
@RequestMapping("/")
public class TaskController {
	
	@Autowired
	private TaskService tarefaService;
	
	@GetMapping
	public String init() {
		return "Welcome to Flow Task Microservice";
	}
	
	@PostMapping("/tarefa")
	public TaskModel create(@RequestBody TaskModel tarefa) {
		return tarefaService.save(tarefa);
	}
	
	@GetMapping("/tarefas")
	public List<TaskModel> getAll(){
		return tarefaService.findAll();
	}
	
	@GetMapping("/tarefa/{id}")
	public TaskModel findById(@PathVariable Long id) {
		return tarefaService.findById(id);
	}
	
	@GetMapping("/tarefas/descricao/{descricao}")
	public List<TaskModel> findByDescricao(@PathVariable String descricao){
		return tarefaService.findByDescricao(descricao);	
	}
	
	@PutMapping("/tarefa/{id}")
	public void update(@PathVariable Long id, @RequestBody TaskModel tarefa) {
		tarefaService.update(id, tarefa);
	}
	
	@DeleteMapping("/tarefa/{id}")
	public void delete(@PathVariable Long id) {
		tarefaService.deleteById(id);
	}

}
