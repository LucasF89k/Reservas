package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Reservation {

    private Integer roomNumber;
    private Date checkIn;
    private Date CheckOut;

    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public Reservation(Integer roomNumber, Date checkIn, Date CheckOut) {
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

    public String updateDates(Date checkIn, Date CheckOut) {
        Date now = new Date();
        if (checkIn.before(now) || CheckOut.before(now)) {
            return "Reservation dates for update must be future dates";
        }
        if (!CheckOut.after(checkIn)) {
            return "Check-Out date must be after check-in date";

        }
        this.checkIn = checkIn;
        this.CheckOut = CheckOut;
        return null;
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
