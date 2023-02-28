package MyDate;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


    public class Date {
        private byte year;
        private int yearAddition;
        private byte month;
        private byte day;

        // конструктори
        public Date(int day, int month, int year) {
            this.yearAddition = year - (year % 100);
            if (year > 100) this.yearAddition = year - (year % 100);

            this.year = (byte) year;
            this.month = (byte) month;
            this.day = (byte) day;
        }

        public Date(String dateStr) {
            String[] date = dateStr.split("\\.");

            this.day = Byte.parseByte(date[0]);
            this.month = Byte.parseByte(date[1]);

            int yearInt = Integer.parseInt(date[2]);

            this.yearAddition = yearInt - (yearInt % 100);
            if (yearInt > 100) this.yearAddition = yearInt - (yearInt % 100);

            this.year = (byte) (yearInt % 100);
        }

        public Date(LocalDate date) {
            this.day = (byte) date.getDayOfMonth();
            this.month = (byte) date.getMonthValue();

            int yearInt = date.getYear();
            this.yearAddition = yearInt - (yearInt % 100);
            if (yearInt > 100) this.yearAddition = yearInt - (yearInt % 100);
            this.year = (byte) (yearInt % 100);

        }

        // методы
        public void setYear(int year) {
            this.yearAddition = year - (year % 100);
            if (year > 100) this.yearAddition = year - (year % 100);
            this.year = (byte) (year % 100);
        }

        public void setMonth(int month) {
            this.month = (byte) month;
        }

        public void setDay(int day) {
            this.day = (byte) day;
        }

        public int getYear() {
            return (int) year + yearAddition;
        }

        public int getMonth() {
            return month;
        }

        public int getDay() {
            return day;
        }

        public int getAdd() {
            return yearAddition;
        }

        public boolean isLeapYear() {
            int yearInt = year + yearAddition;
            return (yearInt % 4 == 0 && yearInt % 100 != 0) || (yearInt % 400 == 0);
        }

        public Date addDays(int days) {
            LocalDate date = LocalDate.of((int) year + yearAddition, month, day);
            Date newDate = new Date(this.getDay(), this.getMonth(), this.getYear());
            date = date.plusDays(days);
            newDate.setDay(date.getDayOfMonth());
            newDate.setMonth(date.getMonthValue());
            newDate.setYear(date.getYear());
            return newDate;
        }

        public Date subtractDays(int days) {
            return addDays(-days);
        }

        public int compare(Date other) {
            LocalDate thisDate = LocalDate.of(year + yearAddition, month, day);
            LocalDate otherDate = LocalDate.of(other.getYear() + other.getAdd(), other.getMonth(), other.getDay());
            return thisDate.compareTo(otherDate);
        }

        public int daysBetween(Date other) {
            LocalDate thisDate = LocalDate.of(year + yearAddition, month, day);
            LocalDate otherDate = LocalDate.of(other.getYear() + other.getAdd(), other.getMonth(), other.getDay());
            return (int) ChronoUnit.DAYS.between(thisDate, otherDate);
        }

        public boolean after(Date other) {
            return this.daysBetween(other) < 0;
        }

        public boolean before(Date other) {
            return this.daysBetween(other) > 0;
        }


        public String toString() {
            return String.format("%02d.%02d.%d", day, month, (int) year + yearAddition);
        }

        public boolean equals(Date other) {
            if (day != other.getDay()) return false;
            if (month != other.getMonth()) return false;
            if (year != other.getYear()) return false;
            return yearAddition == other.getAdd();
        }

    }