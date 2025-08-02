package api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AtmResponse {
    private int count;
    private int pageNumber;
    private int pageSize;
    private List<Atm> atm;

    // Getters and setters

    public int getCount() {
        return count;
    }


    public void setCount(int count) {
        this.count = count;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<Atm> getAtm() {
        return atm;
    }

    public void setAtm(List<Atm> atm) {
        this.atm = atm;
    }
}