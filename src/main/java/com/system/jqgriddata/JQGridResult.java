package com.system.jqgriddata;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JQGridResult<T> {
	@JsonProperty
	private Integer page;

	@JsonProperty
	private Integer records;

	@JsonProperty
	private List<T> rows;

	@JsonProperty
	private Integer total;

	public Integer getPage() {
		return page;
	}

	public Integer getRecords() {
		return records;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public int getTotal() {
		return total;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public void setRecords(Integer records) {
		this.records = records;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}
}
