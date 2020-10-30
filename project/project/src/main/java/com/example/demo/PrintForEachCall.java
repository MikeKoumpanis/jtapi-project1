package com.example.demo;

import com.avaya.jtapi.tsapi.adapters.CallListenerAdapter;

import javax.telephony.*;
import javax.telephony.CallListener;
import javax.telephony.callcontrol.CallControlConnectionEvent;
import javax.telephony.callcontrol.CallControlConnectionListener;
import javax.telephony.capabilities.AddressCapabilities;

import static javax.telephony.callcontrol.CallControlConnectionEvent.CALLCTL_CONNECTION_ALERTING;

public class PrintForEachCall {

    private JtapiPeer peer;
    private Provider myprovider;
    private String[] services ;
    private String myService = "myService";
    private String login = "login";
    private String password = "password";

    // Μηπως πρεπει να τα κανω autowire?
    Address address;
    MyCallListenerAdapter adapter;

    //Init proccess
    public void initProccess()  {
        if (initJtapi()) {
            if (getServices()) {
                 if (getProvider()) {
                     printForEachCall();
                 }
            }
        }
    }

    //Get JtapiPeer object
    private boolean initJtapi() {
        try {
            peer = JtapiPeerFactory.getJtapiPeer(null);
        } catch (Exception excp) {
            System.out.println("Exception during getting JtapiPeer: " + excp);
            return false;
        }
        return true;
    }

    //Get services (CTI-Links)
    private boolean getServices() {

        try {
            services = peer.getServices();
            if (services == null) {
                System.out.println("Unable to obtain the services list from JTAPI peer.\n");
                System.exit(0);
            }
            String myService = services[0];
        } catch (Exception ex) {
            System.out.println("Exception during getting services: " + ex);
            return false;
        }
        return true;
    }

    //Get Provider object
    private boolean getProvider() {
        try {
            myprovider = peer.getProvider(myService + ";login=" + login + ";passwd=" + password);
        } catch (Exception ex) {
            System.out.println("Exception during getting services: " + ex);
            return false;
        }
    return true;
    }

//Returns an Address object which corresponds to the telephone number string provided.
    private boolean printForEachCall() {
        try {
            address = myprovider.getAddress("7616");
        } catch (Exception ex) {
            System.out.println("Exception during providing an address: " + ex);
            return false;
        }

        try {
            address.addCallListener(adapter);
        } catch (Exception ex) {
            System.out.println("Exception during adding callListener to address: " + ex);
            return false;
        }
        return true;
    }
}
