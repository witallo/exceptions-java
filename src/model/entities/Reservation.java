package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import model.exceptions.DomainException;

public class Reservation {
	private Integer roomNumber;
	private Date checkIn;
	private Date checkOut;

	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public Reservation(Integer roomNumber, Date checkIn, Date checkOut) throws DomainException{
		if (checkOut.after(checkIn)) {
			throw new DomainException("Error in reservation: Check-out date must be after Check-in date.");
		}
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

	public void UpdateDates(Date checkin, Date checkout) throws DomainException {
		Date dateNow = new Date();
		if (checkIn.before(dateNow) || checkOut.before(dateNow)) {
			throw new DomainException("Reservation dates for update must be future dates.");
		}
		if (checkOut.after(checkIn)) {
			throw new DomainException("Error in reservation: Check-out date must be after Check-in date.");

		}

		this.checkIn = checkin;
		this.checkOut = checkout;

	}

	@Override
	public String toString() {
		return "Room " + roomNumber + ", Check-in: " + sdf.format(checkOut) + ", Check-out: " + sdf.format(checkOut)
				+ ", " + duration() + " nights.";
	}
}
