package pdm.unindubria.examcountdown;

import java.util.Date;

public class TimeDifference {
    private Date startDate,endDate;
    private long timeDiff = 0;

    public TimeDifference(Date start, Date end) {
        startDate = start;
        endDate = end;

        timeDiff = end.getTime() - start.getTime();
    }

    public long getMilliseconds() {return timeDiff;}
    public long getSeconds() {return (timeDiff /1000)%60;}
    public long getMinutes() {return (timeDiff / (1000 * 60)) % 60;}
    public long getHours() {return (timeDiff / (1000 * 60 * 60)) % 24; }
    public long getYears() {return (timeDiff / (1000l * 60 * 60 * 24 * 365));}
    public long getDays() {return (timeDiff / (1000 * 60 * 60 * 24)) % 365;}
}
