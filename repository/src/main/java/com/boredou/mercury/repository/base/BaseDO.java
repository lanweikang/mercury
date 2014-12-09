package com.boredou.mercury.repository.base;

import java.io.Serializable;
import java.util.Date;

import com.boredou.mercury.repository.util.Pagination;
import lombok.Getter;
import lombok.Setter;

public abstract class BaseDO extends Pagination implements Serializable {
	@Getter
	@Setter
	private long id;

	@Getter
	@Setter
	private Date gmtCreated;

	@Getter
	@Setter
	private Date gmtModified;

    protected BaseDO() {
    }
}
