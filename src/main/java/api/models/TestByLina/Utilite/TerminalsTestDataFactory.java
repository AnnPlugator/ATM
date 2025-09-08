package api.models.InfoService.Utilite;

import api.models.InfoService.TerminalSearchRequest;

public final class TerminalsTestDataFactory {

    private TerminalsTestDataFactory() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    public static TerminalSearchRequest createDefaultRequest(String city) {
        return new TerminalSearchRequest()
                .setCity(city)
                .setCashWithdraw(true)
                .setCashDeposit(true)
                .setAcceptPayments(true)
                .setNfc(true)
                .setIs24Hour(false)
                .setSearchQuery("")
                .setPageNumber(1)
                .setPageSize(10)
                .setSortBy("name");
    }

    public static TerminalSearchRequest createRequest(String city, boolean withdraw,
                                                      boolean deposit, boolean payments,
                                                      boolean nfc, int pageSize) {
        return createDefaultRequest(city)
                .setCashWithdraw(withdraw)
                .setCashDeposit(deposit)
                .setAcceptPayments(payments)
                .setNfc(nfc)
                .setPageSize(pageSize);
    }

    public static TerminalSearchRequest createMoscowAllServicesRequest() {
        return createRequest("Москва", true, true, true, true,
                5);
    }

    public static TerminalSearchRequest createSpb24hRequest() {
        return createDefaultRequest("Санкт-Петербург")
                .setIs24Hour(true)
                .setSortBy("distance");
    }

    public static TerminalSearchRequest createMoscowCashWithdrowOnlyRequest(){
        return createRequest( "Москва", true, false, false,
                false, 3);
    }
}
