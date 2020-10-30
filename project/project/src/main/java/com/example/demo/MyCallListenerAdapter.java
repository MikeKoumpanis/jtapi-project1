package com.example.demo;

import com.avaya.jtapi.tsapi.adapters.CallControlConnectionListenerAdapter;
import com.avaya.jtapi.tsapi.adapters.CallListenerAdapter;
import org.springframework.stereotype.Component;

import javax.telephony.Call;
import javax.telephony.CallEvent;
import javax.telephony.CallListener;
import javax.telephony.callcontrol.CallControlConnectionEvent;

@Component
public class MyCallListenerAdapter extends CallListenerAdapter implements CallListener {

    public void callActive(CallEvent event) {
        System.out.println("Hey, a new call! Answer it");
    }

}
