package com.reserv.myapplicationeli.tools.datetools;

import java.util.Calendar;

public class SolarCalendar {

	private static int year;
	private static int month;
	private static int date;
	private static int weekDay;
	private static Calendar calendar;

	public SolarCalendar() {
		this.calendar = Calendar.getInstance();
		calSolarCalendar();
	}

	public SolarCalendar(Calendar calendar) {
		this.calendar = calendar;
		calSolarCalendar();
	}
	//jorjian to persian
	public static String calSolarCalendar(int georgianYear,int georgianMonth,int georgianDate) {
		int ld;
		/*int georgianYear = calendar.get(Calendar.YEAR);
		int georgianMonth = calendar.get(Calendar.MONTH) + 1;
		int georgianDate = calendar.get(Calendar.DATE);*/
		//georgianMonth=georgianMonth;
		//weekDay = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		int[] buf1 = new int[] { 0, 31, 59, 90, 120, 151, 181, 212, 243, 273,
				304, 334 };
		int[] buf2 = new int[] { 0, 31, 60, 91, 121, 152, 182, 213, 244, 274,
				305, 335 };
		if ((georgianYear % 4) != 0) {
			date = buf1[georgianMonth - 1] + georgianDate;
			if (date > 79) {
				date = date - 79;
				if (date <= 186) {
					switch (date % 31) {
					case 0:
						month = date / 31;
						date = 31;
						break;
					default:
						month = (date / 31) + 1;
						date = (date % 31);
						break;
					}
					year = georgianYear - 621;
				} else {
					date = date - 186;
					switch (date % 30) {
					case 0:
						month = (date / 30) + 6;
						date = 30;
						break;
					default:
						month = (date / 30) + 7;
						date = (date % 30);
						break;
					}
					year = georgianYear - 621;
				}
			} else {
				if ((georgianYear > 1996) && (georgianYear % 4) == 1) {
					ld = 11;
				} else {
					ld = 10;
				}
				date = date + ld;
				switch (date % 30) {
				case 0:
					month = (date / 30) + 9;
					date = 30;
					break;
				default:
					month = (date / 30) + 10;
					date = (date % 30);
					break;
				}
				year = georgianYear - 622;
			}
		} else {
			date = buf2[georgianMonth - 1] + georgianDate;
			if (georgianYear >= 1996) {
				ld = 79;
			} else {
				ld = 80;
			}
			if (date > ld) {
				date = date - ld;
				if (date <= 186) {
					switch (date % 31) {
					case 0:
						month = (date / 31);
						date = 31;
						break;
					default:
						month = (date / 31) + 1;
						date = (date % 31);
						break;
					}
					year = georgianYear - 621;
				} else {
					date = date - 186;
					switch (date % 30) {
					case 0:
						month = (date / 30) + 6;
						date = 30;
						break;
					default:
						month = (date / 30) + 7;
						date = (date % 30);
						break;
					}
					year = georgianYear - 621;
				}
			} else {
				date = date + 10;
				switch (date % 30) {
				case 0:
					month = (date / 30) + 9;
					date = 30;
					break;
				default:
					month = (date / 30) + 10;
					date = (date % 30);
					break;
				}
				year = georgianYear - 622;
			}
		}
		return year+"/"+month+"/"+date;
	}
	private void calSolarCalendar() {
		int ld;
		int georgianYear = calendar.get(Calendar.YEAR);
		int georgianMonth = calendar.get(Calendar.MONTH) + 1;
		int georgianDate = calendar.get(Calendar.DATE);
		weekDay = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		int[] buf1 = new int[] { 0, 31, 59, 90, 120, 151, 181, 212, 243, 273,
				304, 334 };
		int[] buf2 = new int[] { 0, 31, 60, 91, 121, 152, 182, 213, 244, 274,
				305, 335 };
		if ((georgianYear % 4) != 0) {
			date = buf1[georgianMonth - 1] + georgianDate;
			if (date > 79) {
				date = date - 79;
				if (date <= 186) {
					switch (date % 31) {
					case 0:
						month = date / 31;
						date = 31;
						break;
					default:
						month = (date / 31) + 1;
						date = (date % 31);
						break;
					}
					year = georgianYear - 621;
				} else {
					date = date - 186;
					switch (date % 30) {
					case 0:
						month = (date / 30) + 6;
						date = 30;
						break;
					default:
						month = (date / 30) + 7;
						date = (date % 30);
						break;
					}
					year = georgianYear - 621;
				}
			} else {
				if ((georgianYear > 1996) && (georgianYear % 4) == 1) {
					ld = 11;
				} else {
					ld = 10;
				}
				date = date + ld;
				switch (date % 30) {
				case 0:
					month = (date / 30) + 9;
					date = 30;
					break;
				default:
					month = (date / 30) + 10;
					date = (date % 30);
					break;
				}
				year = georgianYear - 622;
			}
		} else {
			date = buf2[georgianMonth - 1] + georgianDate;
			if (georgianYear >= 1996) {
				ld = 79;
			} else {
				ld = 80;
			}
			if (date > ld) {
				date = date - ld;
				if (date <= 186) {
					switch (date % 31) {
					case 0:
						month = (date / 31);
						date = 31;
						break;
					default:
						month = (date / 31) + 1;
						date = (date % 31);
						break;
					}
					year = georgianYear - 621;
				} else {
					date = date - 186;
					switch (date % 30) {
					case 0:
						month = (date / 30) + 6;
						date = 30;
						break;
					default:
						month = (date / 30) + 7;
						date = (date % 30);
						break;
					}
					year = georgianYear - 621;
				}
			} else {
				date = date + 10;
				switch (date % 30) {
				case 0:
					month = (date / 30) + 9;
					date = 30;
					break;
				default:
					month = (date / 30) + 10;
					date = (date % 30);
					break;
				}
				year = georgianYear - 622;
			}
		}
	}

	public String getWeekDay() {
		String strWeekDay = "";
		switch (weekDay) {
		case 0:
			strWeekDay = "�����";
			break;
		case 1:
			strWeekDay = "������";
			break;
		case 2:
			strWeekDay = "�� ����";
			break;
		case 3:
			strWeekDay = "��������";
			break;
		case 4:
			strWeekDay = "��� ����";
			break;
		case 5:
			strWeekDay = "����";
			break;
		case 6:
			strWeekDay = "����";
			break;
		}
		return strWeekDay;
	}

	public String getMonth() {
		String strMonth = "";
		switch (month) {
		case 1:
			strMonth = "�������";
			break;
		case 2:
			strMonth = "��������";
			break;
		case 3:
			strMonth = "�����";
			break;
		case 4:
			strMonth = "���";
			break;
		case 5:
			strMonth = "�����";
			break;
		case 6:
			strMonth = "������";
			break;
		case 7:
			strMonth = "���";
			break;
		case 8:
			strMonth = "����";
			break;
		case 9:
			strMonth = "���";
			break;
		case 10:
			strMonth = "��";
			break;
		case 11:
			strMonth = "����";
			break;
		case 12:
			strMonth = "�����";
			break;
		}
		return strMonth;
	}

	public String getDescribedDateFormat() {
		StringBuilder describedDateFormat = new StringBuilder();
		describedDateFormat.append(getWeekDay()).append(" ")
				.append(String.valueOf(date)).append(" ").append(getMonth())
				.append(" ").append(String.valueOf(year)).append(" �.� ")
				.append(" ���� ").append(getTime());
		return String.valueOf(describedDateFormat);
	}

	public String getNumericDateFormat() {
		StringBuilder numericDateFormat = new StringBuilder();
		numericDateFormat.append(String.valueOf(year)).append("/")
				.append(String.valueOf(month)).append("/")
				.append(String.valueOf(date)).append(" ").append(getTime());
		return String.valueOf(numericDateFormat);
	}

	public String getTime() {
		int h, m, s;
		h = calendar.get(Calendar.HOUR_OF_DAY);
		m = calendar.get(Calendar.MINUTE);
		s = calendar.get(Calendar.SECOND);
		StringBuilder time = new StringBuilder();
		time.append(h < 10 ? "0" + h : h).append(":")
				.append(m < 10 ? "0" + m : m).append(":")
				.append(s < 10 ? "0" + s : s);
		return String.valueOf(time);
	}

	public long getTimeInMillis() {
		return calendar.getTimeInMillis();
	}

	@Override
	public String toString() {
		return getDescribedDateFormat();
	}

	public static void main(String[] args) {
	SolarCalendar solarCalendar = new SolarCalendar();
	System.out.println(solarCalendar.getDescribedDateFormat());
	System.out.println(solarCalendar.getNumericDateFormat());
	System.out.println(solarCalendar.getTime());
	System.out.println(solarCalendar.getMonth());
	System.out.println(solarCalendar.getWeekDay());
	System.out.println(solarCalendar.getTimeInMillis() );
}

}