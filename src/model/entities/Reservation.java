package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Reservation {
	private Integer roomNumber;
	private Date checkIn;
	private Date checkOut;

	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public Reservation(Integer roomNumber, Date checkIn, Date checkOut) {
		this.roomNumber = roomNumber;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}

	public Integer getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Date getCheckIn() {
		return checkIn;
	}

	public Date getCheckOut() {
		return checkOut;
	}

	public long duration() {
		long diff = checkOut.getTime() - checkIn.getTime();
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}

	public String UpdateDates(Date checkin, Date checkout) {
		Date dateNow = new Date();
		if (checkIn.before(dateNow) || checkOut.before(dateNow)) {
			return "Error in reservation: Reservation dates for update must be future dates.";
		}
		if (checkOut.after(checkIn)) {
			return "Error in reservation: Check-out date must be after Check-in date.";

		}

		this.checkIn = checkin;
		this.checkOut = checkout;
		return null;
	}

	@Override
	public String toString() {
		return "Room " + roomNumber + ", Check-in: " + sdf.format(checkOut) + ", Check-out: " + sdf.format(checkOut)
				+ ", " + duration() + " nights.";
	}
}
