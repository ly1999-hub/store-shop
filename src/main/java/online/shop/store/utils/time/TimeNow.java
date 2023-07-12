package online.shop.store.utils.time;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component
public class TimeNow {
    
    public Date getTimeNow(){
        long millis=System.currentTimeMillis();
        return new Date(millis);
    }
}
