package be.pxl.com.mybbq.MyEvents.Models;

public class myEventClass {

    private String username;
    private String eventName;
    private String adress;
    private String houseNumber;
    private String city;
    private String postalCode;
    private String numberOfMaxAttendees;
    private String numberOfAttendees;
    private String date;
    private String time;
    private String comment;
    private String filters;

    public myEventClass(String username, String eventName, String adress, String houseNumber, String city, String postalCode,
                      String numberOfMaxAttendees, String date, String time, String comment, String filters, String numberOfAttendees) {
        this.username = username;
        this.eventName = eventName;
        this.adress = adress;
        this.houseNumber = houseNumber;
        this.city = city;
        this.postalCode = postalCode;
        this.numberOfMaxAttendees = numberOfMaxAttendees;
        this.numberOfAttendees = numberOfAttendees;
        this.date = date;
        this.time = time;
        this.comment = comment;
        this.filters = filters;
    }

    public String getNumberOfMaxAttendees() {
        return numberOfMaxAttendees;
    }

    public void setNumberOfMaxAttendees(String numberOfMaxAttendees) {
        this.numberOfMaxAttendees = numberOfMaxAttendees;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getNumberOfAttendees() {
        return numberOfAttendees;
    }

    public void setNumberOfAttendees(String numberOfAttendees) {
        this.numberOfAttendees = numberOfAttendees;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getFilters() {
        return filters;
    }

    public void setFilters(String filters) {
        this.filters = filters;
    }
}
