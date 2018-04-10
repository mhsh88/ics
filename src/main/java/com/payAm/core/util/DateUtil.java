package com.payAm.core.util;

import com.payAm.core.constant.PropertiesConstants;
import com.payAm.core.i18n.PersianUtil;

import java.util.Arrays;
import java.util.Date;

/**
 * Created by Payam Mostafaei
 * Creation Time: 2017/Jan/10 - 03:13 PM
 */
public class DateUtil {

    public static final String DATE_FORMAT = "yyyy/MM/dd";
    public static final String TIME_FORMAT = "hh:mm";
    public static final String DATE_TIME_SEPERATOR = "-";
    public static final String MILLISECOND_SEPERATOR = ".";

    public static final int GREGORIAN_MIN_YEAR = 1850;
    public static final int GREGORIAN_MAX_YEAR = 2150;
    public static final int JALALI_MIN_YEAR = 1200;
    public static final int JALALI_MAX_YEAR = 1500;

    public static final long ONE_DAY_MILISECONDS = 24 * 60 * 60 * 1000L;
    public static final long ONE_MONTH_MILISECONDS = 31 * 24 * 60 * 60 * 1000L;
    public static final long ONE_YEAR_MILISECONDS = 365 * 24 * 60 * 60 * 1000L;

    protected static int[] gDaysInMonth = new int[12];
    protected static int[] jDaysInMonth = new int[12];

    public enum TimeSection {
        MILLISECOND(0),
        SECOND(1),
        MINUTE(2),
        HOUR(3),
        DAY(4);

        private int position;

        private TimeSection(int position) {
            this.position = position;
        }

        public int getPosition() {
            return position;
        }
    }

    static {
        // Initializing date of gregorian months.
        gDaysInMonth[0] = 31;
        gDaysInMonth[1] = 28;
        gDaysInMonth[2] = 31;
        gDaysInMonth[3] = 30;
        gDaysInMonth[4] = 31;
        gDaysInMonth[5] = 30;
        gDaysInMonth[6] = 31;
        gDaysInMonth[7] = 31;
        gDaysInMonth[8] = 30;
        gDaysInMonth[9] = 31;
        gDaysInMonth[10] = 30;
        gDaysInMonth[11] = 31;

        // Initializing date of jalali months.
        jDaysInMonth[0] = 31;
        jDaysInMonth[1] = 31;
        jDaysInMonth[2] = 31;
        jDaysInMonth[3] = 31;
        jDaysInMonth[4] = 31;
        jDaysInMonth[5] = 31;
        jDaysInMonth[6] = 30;
        jDaysInMonth[7] = 30;
        jDaysInMonth[8] = 30;
        jDaysInMonth[9] = 30;
        jDaysInMonth[10] = 30;
        jDaysInMonth[11] = 29;
    }

    public static Date jalaliStringToDate(String jalaliString) {
        try {
            return jalaliStringToDateTime(jalaliString, null);
        } catch (Exception e) {
            return null;
        }
    }

    public static Date jalaliStringToDateTime(String jalaliString) {
        if (!StringUtil.hasText(jalaliString)) {
            return null;
        }
        String[] jalaliArray = jalaliString.split(DATE_TIME_SEPERATOR);
        if (jalaliArray.length == 1) {
            return jalaliStringToDate(jalaliString);
        } else if (jalaliArray.length != 2) {
            return null;
        }
        try {
            return jalaliStringToDateTime(jalaliArray[0], jalaliArray[1]);
        } catch (Exception e) {
            return null;
        }
    }

    private static Date jalaliStringToDateTime(String jalaliDateString, String timeString) {
        if (!StringUtil.hasText(jalaliDateString)) {
            return null;
        }
        jalaliDateString = PersianUtil.replacePersianWithEnglishNumbers(jalaliDateString);
        String[] stringArray = jalaliDateString.split("/");
        if (stringArray.length != 3) {
            return null;
        }
        int jY;
        int jM;
        int jD;
        try {
            jY = Integer.parseInt(stringArray[0]);
            jM = Integer.parseInt(stringArray[1]);
            jD = Integer.parseInt(stringArray[2]);
        } catch (NumberFormatException e) {
            return null;
        }

        int gY, gM, gD;
        long gDayNo, jDayNo;
        int leap;

        int i;

        jY = jY - 979;
        jM = jM - 1;
        jD = jD - 1;

        jDayNo = 365 * jY + (jY / 33) * 8 + (jY % 33 + 3) / 4;
        for (i = 0; i < jM; ++i) {
            jDayNo += jDaysInMonth[i];
        }
        jDayNo += jD;

        gDayNo = jDayNo + 79;

        gY = (int) (1600 + 400 * (gDayNo / 146097));
        gDayNo = gDayNo % 146097;

        leap = 1;
        if (gDayNo >= 36525) {
            gDayNo--;
            gY += 100 * (gDayNo / 36524);
            gDayNo = gDayNo % 36524;

            if (gDayNo >= 365) {
                gDayNo++;
            } else {
                leap = 0;
            }
        }

        gY += 4 * (gDayNo / 1461);
        gDayNo %= 1461;

        if (gDayNo >= 366) {
            leap = 0;

            gDayNo--;
            gY += gDayNo / 365;
            gDayNo = gDayNo % 365;
        }

        int append = 0;
        for (i = 0; gDayNo >= gDaysInMonth[i] + append; i++) {
            if ((i == 1) && (leap != 0)) {
                append = 1;
            } else {
                append = 0;
            }
            gDayNo -= gDaysInMonth[i] + append;
        }

        gM = i + 1;
        gD = (int) (gDayNo + 1);

        Date date = new Date(gY - 1900, gM - 1, gD);

        stringToTime(date, timeString);
        return date;
    }

    public static String dateToJalaliString(Date date) {
        if (date == null) {
            return null;
        } else {
            String jalaliString = "";
            int gY = date.getYear() + 1900;
            int gM = date.getMonth() + 1;
            int gD = date.getDate();

            int jY, jM, jD;
            long gDayNo, jDayNo;
            int jNp;

            int i;

            gY = gY - 1600;
            gM = gM - 1;
            gD = gD - 1;

            gDayNo = 365 * gY + (gY + 3) / 4 - (gY + 99) / 100 + (gY + 399) / 400;
            for (i = 0; i < gM; ++i) {
                gDayNo += gDaysInMonth[i];
            }
            if (gM > 1 && ((gY % 4 == 0 && gY % 100 != 0) || (gY % 400 == 0))) {// leap and after February
                ++gDayNo;
            }
            gDayNo += gD;

            jDayNo = gDayNo - 79;

            jNp = (int) (jDayNo / 12053);
            jDayNo %= 12053;

            jY = (int) (979 + 33 * jNp + 4 * (jDayNo / 1461));
            jDayNo %= 1461;

            if (jDayNo >= 366) {
                jY += (jDayNo - 1) / 365;
                jDayNo = (jDayNo - 1) % 365;
            }

            for (i = 0; i < 11 && jDayNo >= jDaysInMonth[i]; ++i) {
                jDayNo -= jDaysInMonth[i];
            }

            jM = i + 1;
            jD = (int) (jDayNo + 1);
            int[] result = new int[3];
            result[0] = jY;
            result[1] = jM;
            result[2] = jD;

            jalaliString = getFormatedJalaliDate(result[0] + "/" + result[1] + "/" + result[2]);
            return jalaliString;
        }
    }

    public static String dateTimeToJalaliString(Date dateTime) {
        return dateTimeToJalaliString(dateTime, false, false);
    }

    public static String dateTimeToJalaliString(Date dateTime, boolean showSeconds, boolean showMilliseconds) {
        if (dateTime == null) {
            return null;
        }
        String dateTimeJalaliString = dateToJalaliString(dateTime);
        dateTimeJalaliString += DATE_TIME_SEPERATOR + timeToString(dateTime, showSeconds, showMilliseconds);
        return dateTimeJalaliString;
    }

    public static Date gregorianStringToDate(String gregorianString) {
        if (!StringUtil.hasText(gregorianString)) {
            return null;
        }
        try {
            return new Date(gregorianString);
        } catch (Exception e) {
            return null;
        }
    }

    public static Date gregorianStringToDateTime(String gregorianString) {
        if (!StringUtil.hasText(gregorianString)) {
            return null;
        }
        try {
            gregorianString = PersianUtil.replacePersianWithEnglishNumbers(gregorianString);
            String[] dateTimeArray = gregorianString.split(DATE_TIME_SEPERATOR);
            Date date = new Date(dateTimeArray[0]);
            String timeString = dateTimeArray.length > 1 ? dateTimeArray[1] : "";
            stringToTime(date, timeString);
            return date;
        } catch (Exception e) {
            return null;
        }
    }

    public static Date stringToTime(String timeString) {
        return stringToTime(new Date(0), timeString);
    }

    public static Date stringToTime(Date date, String timeString) {
        if (timeString != null && timeString.length() > 0) {
            String[] timeArray = timeString.split(":");
            if (timeArray.length > 0) {
                date.setHours(Integer.valueOf(timeArray[0]));
            }
            if (timeArray.length > 1) {
                date.setMinutes(Integer.valueOf(timeArray[1]));
            }
            if (timeArray.length > 2) {
                if (timeArray[2].contains(MILLISECOND_SEPERATOR)) {
                    String[] secondArray = timeArray[2].split("\\" + MILLISECOND_SEPERATOR);
                    date.setSeconds(Integer.valueOf(secondArray[0]));
                    date.setTime(date.getTime() + Long.valueOf(secondArray[1]));
                } else {
                    date.setSeconds(Integer.valueOf(timeArray[2]));
                }
            }
        }
        return date;
    }

    public static String dateToGregorianString(Date date) {
        if (date == null) {
            return null;
        }
        return getFormatedGregorianDate(date.getYear() + "/" + date.getMonth() + "/" + date.getDate());
    }

    public static String dateTimeToGregorianString(Date dateTime) {
        return dateTimeToGregorianString(dateTime, false, false);
    }

    public static String dateTimeToGregorianString(Date dateTime, boolean showSeconds, boolean showMilliseconds) {
        if (dateTime == null) {
            return null;
        }
        String formatedDate = dateToGregorianString(dateTime);
        String formatedTime = timeToString(dateTime, showSeconds, showMilliseconds);
        return formatedDate + DATE_TIME_SEPERATOR + formatedTime;
    }

    public static String timeToString(Date dateTime) {
        return timeToString(dateTime, false, false);
    }

    public static String timeToString(Date dateTime, boolean showSeconds, boolean showMilliseconds) {
        if (dateTime == null) {
            return null;
        }
        if (dateTime instanceof java.sql.Date) {
            dateTime = new Date(((Date) dateTime).getTime());
        }
        String timeToString = "";
        timeToString += dateTime.getHours() >= 10 ? dateTime.getHours() : "0" + dateTime.getHours();
        timeToString += dateTime.getMinutes() >= 10 ? ":" + dateTime.getMinutes() : ":0" + dateTime.getMinutes();
        if (showSeconds) {
            timeToString += dateTime.getSeconds() >= 10 ? ":" + dateTime.getSeconds() : ":0" + dateTime.getSeconds();
        }
        if (showMilliseconds) {
            int milliseconds = getMilliseconds(dateTime);
            timeToString += MILLISECOND_SEPERATOR + (milliseconds >= 100 ? milliseconds : (milliseconds >= 10 ? "0" + milliseconds : "00" + milliseconds));
        }
        return timeToString;
    }

    private static String getFormatedGregorianDate(String dateString) {
        try {
            String[] dateArray = dateString.split("/");
            String yearString = (Integer.valueOf(dateArray[0].trim()) + 1900) + "";
            int month = Integer.valueOf(dateArray[1].trim()) + 1;
            String monthString = month >= 10 ? "" + month : "0" + month;
            int day = Integer.valueOf(dateArray[2].trim());
            String dayString = day >= 10 ? "" + day : "0" + day;
            return yearString + "/" + monthString + "/" + dayString;
        } catch (Exception e) {
            return null;
        }
    }

    private static String getFormatedJalaliDate(String jalaliDateString) {
        try {
            String[] dateArray = jalaliDateString.split("/");
            String yearString = dateArray[0].trim();
            int month = Integer.valueOf(dateArray[1].trim());
            String monthString = month >= 10 ? "" + month : "0" + month;
            int day = Integer.valueOf(dateArray[2].trim());
            String dayString = day >= 10 ? "" + day : "0" + day;
            return yearString + "/" + monthString + "/" + dayString;
        } catch (Exception e) {
            return null;
        }
    }

    public static String dateTimeToLocalizedString(Date dateTime, String calendar) {
        return dateTimeToLocalizedString(dateTime, calendar, false, false);
    }

    public static String dateTimeToLocalizedString(Date dateTime, String calendar, boolean showSeconds, boolean showMilliseconds) {
        if (PropertiesConstants.GREGORIAN.equals(calendar)) {
            return dateTimeToGregorianString(dateTime, showSeconds, showMilliseconds);
        } else {
            return dateTimeToJalaliString(dateTime, showSeconds, showMilliseconds);
        }
    }

    public static String dateToLocalizedString(Date date, String calendar) {
        if (PropertiesConstants.GREGORIAN.equals(calendar)) {
            return dateToGregorianString(date);
        } else {
            return dateToJalaliString(date);
        }
    }

    public static Date localizedStringToDateTime(String dateTimeString, String calendar) {
        if (PropertiesConstants.GREGORIAN.equals(calendar)) {
            return gregorianStringToDateTime(dateTimeString);
        } else {
            return jalaliStringToDateTime(dateTimeString);
        }
    }

    public static Date localizedStringToDate(String dateString, String calendar) {
        if (PropertiesConstants.GREGORIAN.equals(calendar)) {
            return gregorianStringToDate(dateString);
        } else {
            return jalaliStringToDate(dateString);
        }
    }

    public static boolean isLeapYear(int yearNumber, String calendar) {
        if (PropertiesConstants.GREGORIAN.equals(calendar)) {
            return (yearNumber % 4 == 0) && ((yearNumber % 100 != 0) || (yearNumber % 400 == 0));
        } else {
            return yearNumber % 33 == 1 || yearNumber % 33 == 5 || yearNumber % 33 == 9 || yearNumber % 33 == 13 || yearNumber % 33 == 17
                    || yearNumber % 33 == 22 || yearNumber % 33 == 26 || yearNumber % 33 == 30;
        }
    }

    public static Date getFirstMinuteOfDay(Date date) {
        return DateUtil.jalaliStringToDateTime(DateUtil.dateToJalaliString(date) + DateUtil.DATE_TIME_SEPERATOR + "00:00");
    }

    public static Date getLastMinuteOfDay(Date date) {
        return DateUtil.jalaliStringToDateTime(DateUtil.dateToJalaliString(date) + DateUtil.DATE_TIME_SEPERATOR + "23:59");
    }

    public static Date getFirstDayOfYear(Date date, String calendar) {
        String[] currentDateArray = StringUtil.convertStringToStringArray(dateToLocalizedString(date, calendar), StringUtil.SLASH);
        return getFirstDayOfYear(Integer.valueOf(currentDateArray[0]), calendar);
    }

    public static Date getFirstDayOfYear(int yearNumber, String calendar) {
        String firstDayOfYearString = yearNumber + "/01/01";
        return localizedStringToDate(firstDayOfYearString, calendar);
    }

    public static Date getFirstDayOfCurrentYear(String calendar) {
        return getFirstDayOfYear(new Date(), calendar);
    }

    public static Date getLastDayOfYear(Date date, String calendar) {
        Integer currentYearNumber = Integer.valueOf(StringUtil.convertStringToStringArray(dateToLocalizedString(date, calendar), StringUtil.SLASH)[0]);
        return getLastDayOfYear(currentYearNumber, calendar);
    }

    public static Date getLastDayOfYear(int yearNumber, String calendar) {
        Integer lastDayOfYear = PropertiesConstants.GREGORIAN.equals(calendar) ? 31 : (DateUtil.isLeapYear(yearNumber, calendar) ? 30 : 29);
        return DateUtil.localizedStringToDate(yearNumber + "/12/" + lastDayOfYear, calendar);
    }

    public static Date getLastDayOfCurrentYear(String calendar) {
        return getLastDayOfYear(new Date(), calendar);
    }

    public static Date getFirstDayOfMonth(Date date, String calendar) {
        String currentDateString = dateToLocalizedString(new Date(), calendar);
        String[] currentDateArray = StringUtil.convertStringToStringArray(currentDateString, StringUtil.SLASH);
        String firstDayOfMonthString = currentDateArray[0] + "/" + currentDateArray[1] + "/01";
        return localizedStringToDate(firstDayOfMonthString, calendar);
    }

    public static Date getLastDayOfMonth(Date date, String calendar) {
        String currentDateString = dateToLocalizedString(new Date(), calendar);
        String[] currentDateArray = StringUtil.convertStringToStringArray(currentDateString, StringUtil.SLASH);
        Integer currentYearNumber = Integer.valueOf(currentDateArray[0]);
        Integer currentMonthNumber = Integer.valueOf(currentDateArray[1]);
        Integer lastDayOfMonth = 30;
        if (PropertiesConstants.JALALI.equals(calendar)) {
            if (currentMonthNumber >= 1 && currentMonthNumber <= 6) {
                lastDayOfMonth = 31;
            } else if (currentMonthNumber == 12 && !isLeapYear(currentYearNumber, calendar)) {
                lastDayOfMonth = 29;
            }
        } else {
            if (Arrays.asList(1, 3, 5, 7, 8, 10, 12).contains(currentMonthNumber)) {
                lastDayOfMonth = 31;
            } else if (currentMonthNumber == 2) {
                lastDayOfMonth = isLeapYear(currentYearNumber, calendar) ? 29 : 28;
            }
        }
        return DateUtil.localizedStringToDate(currentYearNumber + "/" + currentMonthNumber + "/" + lastDayOfMonth, calendar);
    }

    public static Date removeTimeFromDate(Date date) {
        if (date != null) {
            return gregorianStringToDate(dateToGregorianString(date));
        } else {
            return null;
        }
    }

    public static int getMilliseconds(Date date) {
        return new Long(date.getTime() % 1000L).intValue();
    }

    public static int getDaysCountOfMonth(Date date, String calendar) {
        String dateToString = dateToLocalizedString(date, calendar);
        int localizedYear = Integer.parseInt(dateToString.split("/")[0]);
        int localizedMonth = Integer.parseInt(dateToString.split("/")[1]);
        return getDaysCountOfMonth(localizedYear, localizedMonth, calendar);
    }

    private static int getDaysCountOfMonth(int localizedYear, int localizedMonth, String calendar) {
        if (PropertiesConstants.GREGORIAN.equals(calendar)) {
            if (localizedMonth == 2 && isLeapYear(localizedYear, calendar)) {
                return 29;
            } else {
                return gDaysInMonth[localizedMonth - 1];
            }
        } else {
            if (localizedMonth == 12 && isLeapYear(localizedYear, calendar)) {
                return 30;
            } else {
                return jDaysInMonth[localizedMonth - 1];
            }
        }
    }

    public static Date rollByDay(Date date, int dayRoll) {
        if (date != null) {
            long lDate = date.getTime();
            lDate += (dayRoll * ONE_DAY_MILISECONDS);
            return new Date(lDate);
        }
        return date;
    }

    public static Date rollByMonth(Date date, int monthRoll, String calendar) {
        while (monthRoll > 0) {
            date = rollByDay(date, getDaysCountOfMonth(date, calendar));
            monthRoll--;
        }
        return date;
    }

    public static boolean dateStringIsValid(String dateString, boolean hasTime, String calendar) {
        if (!StringUtil.hasText(dateString)) {
            return false;
        }
        dateString = PersianUtil.replacePersianWithEnglishNumbers(dateString);
        String[] dateTimeArray = dateString.split(DATE_TIME_SEPERATOR);
        if (dateTimeArray.length != 1 && dateTimeArray.length != 2) {
            return false;
        }
        if (dateTimeArray.length == 1 && hasTime) {
            return false;
        }
        if (dateTimeArray.length == 2 && !hasTime) {
            return false;
        }
        String[] dateArray = dateTimeArray[0].split("/");
        if (dateArray.length != 3) {
            return false;
        }
        try {
            int year = Integer.parseInt(dateArray[0]);
            int minYear = PropertiesConstants.GREGORIAN.equals(calendar) ? GREGORIAN_MIN_YEAR : JALALI_MIN_YEAR;
            int maxYear = PropertiesConstants.GREGORIAN.equals(calendar) ? GREGORIAN_MAX_YEAR : JALALI_MAX_YEAR;
            if (year < minYear || year > maxYear) {
                return false;
            }
            int month = Integer.parseInt(dateArray[1]);
            if (month < 1 || month > 12) {
                return false;
            }
            int day = Integer.parseInt(dateArray[2]);
            if (day < 1 || day > getDaysCountOfMonth(year, month, calendar)) {
                return false;
            }
            if (hasTime && !timeStringIsValid(dateTimeArray[1])) {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static boolean timeStringIsValid(String timeString) {
        String[] timeArray = StringUtil.convertStringToStringArray(timeString, StringUtil.COLON);
        if (timeArray.length != 2) {
            return false;
        }
        int hour = Integer.parseInt(timeArray[0]);
        if (hour < 0 || hour > 23) {
            return false;
        }
        int minute = Integer.parseInt(timeArray[1]);
        if (minute < 0 || minute > 59) {
            return false;
        }
        return true;
    }

    public static Date getLastTimeInDay(Date date) {
        Date todayStartTime = removeTimeFromDate(date);
        Date tomorrowStartTime = DateUtil.rollByDay(todayStartTime, 1);
        return new Date(tomorrowStartTime.getTime() - 1);
    }

    public static int compareTwoTime(Date start, Date end) {
        long diff = 0;
        try {
            diff = getTwoTimeDifference(start, end, true, true);
        } catch (IllegalArgumentException e) {
            return -1;
        }
        if (diff > 0) {
            return 1;
        }
        return 0;
    }

    public static long getTwoTimeDifference(Date start, Date end, boolean calcSeconds, boolean calcMilliseconds) throws IllegalArgumentException {
        Date startTime = stringToTime(timeToString(start, calcSeconds, calcMilliseconds));
        Date endTime = stringToTime(timeToString(end, calcSeconds, calcMilliseconds));
        return getTwoDateDifference(startTime, endTime);
    }

    public static long getTwoDateDifference(Date start, Date end) throws IllegalArgumentException {
        if (start == null || end == null) {
            throw new IllegalArgumentException("One of start or end is null.");
        }
        if (end.before(start)) {
            throw new IllegalArgumentException("End date can not be less than start date.");
        }
        return end.getTime() - start.getTime();
    }
}
