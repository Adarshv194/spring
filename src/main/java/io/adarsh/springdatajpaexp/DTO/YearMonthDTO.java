package io.adarsh.springdatajpaexp.DTO;

public class YearMonthDTO {
    String year;
    String month;

    public YearMonthDTO() {
    }

    public YearMonthDTO(String year, String month) {
        this.year = year;
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
