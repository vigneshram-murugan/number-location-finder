package com.khaapi.sample.numberlocationfinder.service;

import com.khaapi.sample.numberlocationfinder.model.Number;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.availablephonenumbercountry.Local;
import org.springframework.beans.factory.annotation.Value;
import com.twilio.base.ResourceSet;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class NumberLocationFinderService {

    public static final String COUNTRY_CODE = "US";
    @Value("${twilio.accountId}")
    private String ACCOUNTID;

    @Value("${twilio.auth.token}")
    private String KEY;


    public List<Number> getAvailableNumbers(Integer areaCode) throws Exception {
        if (!validateRequest(areaCode)) {
            throw new Exception("Area Code should always be 3 digits length.");
        }
        Twilio.init(ACCOUNTID, KEY);
        List<Number>  numbers = new ArrayList<>();
        ResourceSet<Local> numberSet = Local.reader(COUNTRY_CODE).setAreaCode(areaCode).read();
        if (numberSet != null) {
            while (numberSet.iterator().hasNext()) {
                Local local = numberSet.iterator().next();
                Number number = new Number();
                number.setPhoneNumber(local.getFriendlyName().getEndpoint());
                number.setLocality(local.getLocality());
                number.setPostalCode(local.getPostalCode());
                numbers.add(number);
            }
        }
        return numbers;
    }


    protected boolean validateRequest(Integer areaCode) {
        boolean isValid = false;
        if (areaCode.toString().length() <= 3) {
            isValid = true;
        }
        return isValid;
    }



}
