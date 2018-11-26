public abstract class SearchController {
    // constructor
    public SearchController() {
    }

    // member functions
    public void insertBatch(List<SearchNode> batch) {
        for (SearchNode searchnode : batch) {
            this.insert(searchnode);
        }
    }

    public abstract void insert(SearchNode searchnode);

    public abstract SearchNode remove();
}