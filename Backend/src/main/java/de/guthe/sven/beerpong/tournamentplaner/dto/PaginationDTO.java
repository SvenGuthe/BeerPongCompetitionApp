package de.guthe.sven.beerpong.tournamentplaner.dto;

import javax.validation.constraints.NotNull;
import java.util.List;

public class PaginationDTO<T> {

	@NotNull(message = "The size of the PaginationDTO have to be set.")
	public long size;

	@NotNull(message = "The pages of the PaginationDTO have to be set.")
	public int pages;

	@NotNull(message = "The data of the PaginationDTO have to be set.")
	public List<T> data;

	public PaginationDTO(long size, int pages, List<T> data) {
		this.size = size;
		this.pages = pages;
		this.data = data;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

}
