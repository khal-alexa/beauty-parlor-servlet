package dao;

import java.util.List;

public class Page<E> {
    private final List<E> items;
    private final int pageNumber;
    private final int itemsPerPage;
    private final int totalItems;
    private final int totalPages;

    public Page(List<E> items, int pageNumber, int itemsPerPage, int totalItems, int totalPages) {
        this.items = items;
        this.pageNumber = pageNumber;
        this.itemsPerPage = itemsPerPage;
        this.totalItems = totalItems;
        this.totalPages = totalPages;
    }

    public List<E> getItems() {
        return items;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public int getItemsPerPage() {
        return itemsPerPage;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public int getTotalPages() {
        return totalPages;
    }

}
