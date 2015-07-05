package com.lianyu.android.runtracker;

import android.annotation.SuppressLint;
import java.util.Date;

public class Run {

	private long mId;
	private Date mStartDate;

	public Run() {
		super();
		mId = -1;
		mStartDate = new Date();
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return mId;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		mId = id;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return mStartDate;
	}

	/**
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(Date startDate) {
		mStartDate = startDate;
	}

	public int getDurationSeconds(long endMillis) {
		return (int) ((endMillis - mStartDate.getTime()) / 1000);
	}

	@SuppressLint("DefaultLocale")
	public static String formatDuration(int durationSeconds) {
		int seconds = durationSeconds % 60;
		int minutes = ((durationSeconds - seconds) / 60) % 60;
		int hours = (durationSeconds - (minutes * 60) - seconds) / 3600;
		return String.format("%02d:%02d:%02d", hours, minutes, seconds);
	}

}
