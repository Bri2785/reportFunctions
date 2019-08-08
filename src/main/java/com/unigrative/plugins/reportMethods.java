package com.unigrative.plugins;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;

public final class reportMethods {

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

    public static BufferedImage rotateClockwise90(BufferedImage src) {
        int width = src.getWidth();
        int height = src.getHeight();

        BufferedImage dest = new BufferedImage(height, width, src.getType());

        Graphics2D graphics2D = dest.createGraphics();
        graphics2D.translate((height - width) / 2, (height - width) / 2);
        graphics2D.rotate(Math.PI / 2, height / 2, width / 2);
        graphics2D.drawRenderedImage(src, null);

        return dest;
    }
}
