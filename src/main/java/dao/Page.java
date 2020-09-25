package dao;

import java.util.List;

public class Page {
    private List<Object> items;
    private int pageNumber;
    private int itemsPerPage;
    private Long totalItems;
    private Long totalPages;

    public Page(List<Object> items, int pageNumber, int itemsPerPage, Long totalItems, Long totalPages) {
        this.items = items;
        this.pageNumber = pageNumber;
        this.itemsPerPage = itemsPerPage;
        this.totalItems = totalItems;
        this.totalPages = totalPages;
    }

    public List<Object> getItems() {
        return items;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public int getItemsPerPage() {
        return itemsPerPage;
    }

    public Long getTotalItems() {
        return totalItems;
    }

    public Long getTotalPages() {
        return totalPages;
    }
}
