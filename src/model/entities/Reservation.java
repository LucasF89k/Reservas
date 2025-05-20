package model.entities;

import model.exceptions.DomainException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.IllformedLocaleException;
import java.util.concurrent.TimeUnit;

public class Reservation {

    private Integer roomNumber;
    private Date checkIn;
    private Date CheckOut;

    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public Reservation(Integer roomNumber, Date checkIn, Date CheckOut) {
        if (!CheckOut.after(checkIn)) {
            throw new DomainException("Check-Out date must be after check-in date");
        }
        this.roomNumber = roomNumber;
        this.checkIn = checkIn;
        this.CheckOut = CheckOut;

    }

    public Date getCheckIn() {
        return checkIn;
    }


    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Date getCheckOut() {
        return CheckOut;
    }

    public long duration() {
        long diff = getCheckOut().getTime() - checkIn.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    public void updateDates(Date checkIn, Date CheckOut) {
        Date now = new Date();
        if (checkIn.before(now) || CheckOut.before(now)) {
            throw  new DomainException ("Reservation dates for update must be future dates");
        }
        if (!CheckOut.after(checkIn)) {
            throw new DomainException ("Check-Out date must be after check-in date");

        }
        this.checkIn = checkIn;
        this.CheckOut = CheckOut;
    }
    @Override
    public String toString() {
        return "Room "
                + roomNumber
                + ", CheckIn: "
                + sdf.format(checkIn)
                + ", CheckOut: "
                + sdf.format(CheckOut)
                + ", "
                + duration()
                + " nights";
    }
}
