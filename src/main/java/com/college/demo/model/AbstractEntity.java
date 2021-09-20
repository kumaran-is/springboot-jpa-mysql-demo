package com.college.demo.model;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import com.college.demo.audit.Auditable;
import lombok.Data;


@MappedSuperclass
@Data
@EntityListeners(AuditingEntityListener.class)
public class AbstractEntity extends Auditable<String> {
	
	@Id
	@Column(nullable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

}
