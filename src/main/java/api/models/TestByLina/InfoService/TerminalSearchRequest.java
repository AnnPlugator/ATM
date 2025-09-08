package api.models.InfoService;

public class TerminalSearchRequest {
    private String city;
    private boolean cashWithdraw;
    private boolean cashDeposit;
    private boolean acceptPayments;
    private boolean nfc;
    private boolean is24Hour;
    private String searchQuery;
    private int pageNumber;
    private int pageSize;
    private String sortBy;

    public TerminalSearchRequest(){}

    public TerminalSearchRequest(String city, boolean cashWithdraw, boolean cashDeposit,
                                 boolean acceptPayments, boolean nfc, boolean is24Hour,
                                 String searchQuery, int pageNumber, int pageSize, String sortBy) {
        this.city = city;
        this.cashWithdraw = cashWithdraw;
        this.cashDeposit = cashDeposit;
        this.acceptPayments = acceptPayments;
        this.nfc = nfc;
        this.is24Hour = is24Hour;
        this.searchQuery = searchQuery;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.sortBy = sortBy;
    }

    public String getCity() {
        return city;
    }

    public boolean isCashWithdraw() {
        return cashWithdraw;

    }

    public boolean isCashDeposit() {
        return cashDeposit;
    }

    public boolean isAcceptPayments() {
        return acceptPayments;
    }

    public boolean isNfc() {
        return nfc;
    }

    public boolean isIs24Hour() {
        return is24Hour;
    }

    public String getSearchQuery() {
        return searchQuery;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public String getSortBy() {
        return sortBy;
    }

    public TerminalSearchRequest setCity(String city) {
        this.city = city;
        return this;
    }

    public TerminalSearchRequest setCashWithdraw(boolean cashWithdraw) {
        this.cashWithdraw = cashWithdraw;
        return this;
    }

    public TerminalSearchRequest setCashDeposit(boolean cashDeposit) {
        this.cashDeposit = cashDeposit;
        return this;
    }

    public TerminalSearchRequest setAcceptPayments(boolean acceptPayments) {
        this.acceptPayments = acceptPayments;
        return this;
    }

    public TerminalSearchRequest setNfc(boolean nfc) {
        this.nfc = nfc;
        return this;
    }

    public TerminalSearchRequest setIs24Hour(boolean is24Hour) {
        this.is24Hour = is24Hour;
        return this;
    }

    public TerminalSearchRequest setSearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
        return this;
    }

    public TerminalSearchRequest setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
        return this;
    }

    public TerminalSearchRequest setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public TerminalSearchRequest setSortBy(String sortBy) {
        this.sortBy = sortBy;
        return this;
    }


}
