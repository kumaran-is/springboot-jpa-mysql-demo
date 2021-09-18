package com.college.demo.audit;

import static javax.persistence.TemporalType.TIMESTAMP;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import lombok.Data;


@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
public class Auditable<U> {

	@Column(name = "created_by", nullable = false)
	@CreatedBy
	protected U createdBy;

	@Column(name = "created_date", nullable = false)
	@CreatedDate
	@Temporal(TIMESTAMP)
	protected Date createdDate;

	@Column(name = "last_modified_by", nullable = false)
	@LastModifiedBy
	protected U lastModifiedBy;

	@Column(name = "last_modified_date", nullable = false)
	@LastModifiedDate
	@Temporal(TIMESTAMP)
	protected Date lastModifiedDate;
}
