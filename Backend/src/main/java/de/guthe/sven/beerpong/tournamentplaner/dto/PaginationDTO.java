package de.guthe.sven.beerpong.tournamentplaner.dto;

import java.util.List;

public class PaginationDTO<T> {

    public long size;

    public int pages;

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
