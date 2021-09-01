package com.rrm14.tasks.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="tb_task")
@Data
@NoArgsConstructor
public class TaskEntity implements Serializable {
	
	private static final long serialVersionUID = 6893527854020114316L;
	
	@JsonInclude(Include.NON_NULL)
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy="increment")
	@Id
	@Column(name="id")
	private Long id;
	
	@Column(name="descricao")
	private String descricao;
	
	@Column(name="dataCriacao")
	private LocalDate dataCriacao;
	
	@Column(name="dataConclusao")
	private LocalDate dataConclusao;
	
	@Column(name="statusTarefa")
	private String statusTarefa;

}
