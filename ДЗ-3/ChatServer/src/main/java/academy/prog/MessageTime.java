package academy.prog;

import java.util.Calendar;
import java.util.Date;

public class MessageTime {
    private int delay = 60;
    public  MessageTime(){
}

    public boolean isUserPresent(Date userDate){
        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(userDate);
        calendar.add(Calendar.SECOND, delay);
        Date compareDate = calendar.getTime();

        return currentDate.before(compareDate);
    }
}


