package com.example.demo.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Calendar;

@Getter
@Setter
public class CurrentTime {
    String time;
    public CurrentTime(){
        Calendar now = Calendar.getInstance();
        String s = now.get(Calendar.YEAR)+"-"
                +((int) now.get((Calendar.MONTH))+1)+"-"
                +now.get(Calendar.DAY_OF_MONTH)+" "
                +now.get(Calendar.HOUR_OF_DAY)+":"
                +now.get(Calendar.MINUTE)+":"
                +now.get(Calendar.SECOND);
        time = s;
    }
}
