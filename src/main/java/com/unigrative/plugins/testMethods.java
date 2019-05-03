package com.unigrative.plugins;

import java.util.ArrayList;
import java.util.Arrays;

public final class testMethods {

    public static boolean stateIsRestricted(final String stateList, final String currentStateAbbr){

        if (stateList.isEmpty()){
            return false;
        }
        String[] values = stateList.split("\\s*,\\s*");
        ArrayList<String> restrictedStates = new ArrayList(Arrays.asList(values));

        //return true;
        for (String state :
                restrictedStates) {
            if (state.equalsIgnoreCase(currentStateAbbr)) {
                return true;
            }
        }
        return false;

//        return restrictedStates.stream().anyMatch(s -> s.equalsIgnoreCase(currentStateAbbr)); //stream not supported in iReport
    }

}
