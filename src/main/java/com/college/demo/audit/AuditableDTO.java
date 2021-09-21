package com.college.demo.audit;

import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Auditable DTO")
public class AuditableDTO<U> {

		// private static final long serialVersionUID = 1L;
		
		@ApiModelProperty(value = "Audit field generated automatically by JPA layer that refers to an user who created the record", name = "createdBy")
		protected U createdBy;

		@ApiModelProperty(value = "Audit field generated automatically by JPA layer that refers to a date when the record was created", name = "createdDate")
		protected LocalDateTime createdDate;

		@ApiModelProperty(value = "Audit field generated automatically by JPA layer refers to an user who last modified the record", name = "lastModifiedBy")
		protected U lastModifiedBy;

		@ApiModelProperty(value = "Audit field generated automatically by JPA layer refers to a date when the record was last modified", name = "lastModifiedDate")
		protected LocalDateTime lastModifiedDate;
}
