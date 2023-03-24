package com.fbi.plugins.unigrative.reportFunctions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;

public final class reportMethods {

    private static final Logger LOGGER = LoggerFactory.getLogger(reportFunctionsPlugin.class);

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

    public static Integer getTreeThresholdinEach(String productTree, String thresholdList) {

        LOGGER.debug(String.format("Threshold List passed in : %s", thresholdList));
        LOGGER.debug(String.format("Product tree passed in : %s", productTree));

        String[] thresholdPairArray = thresholdList.split(",");
        LOGGER.debug(String.format("Split Pair Values: %s", Arrays.toString(thresholdPairArray)));

        ArrayList<String> thresholdPairList = new ArrayList<>(Arrays.asList(thresholdPairArray));

        for (String thresholdPair :
                thresholdPairList) {
            String[] values = thresholdPair.split(":");
            LOGGER.debug(String.format("Individual tree threshold: %s", Arrays.toString(values)));
            if (values.length < 2){
                // Something isn't right, just return 0
                LOGGER.debug("Issues parsing the pair, array length less than 2");
                return 0;
            }
            if (values[0].toLowerCase().contains(productTree.toLowerCase())){
                LOGGER.debug("Match found for product tree");
                try{
                    LOGGER.debug(String.format("Parsed threshold for %s1: %s2", productTree, Integer.parseInt(values[1])));
                    return Integer.parseInt(values[1]);
                }
                catch (Exception e){
                    LOGGER.debug("Error parsing threshold value");
                    return 0;
                }
            }

        }
        return 0;
    }

    public static boolean isTreeExcluded(String productTree, String exclusionString) {

        LOGGER.debug(String.format("Exclusion List passed in : %s", exclusionString));
        LOGGER.debug(String.format("Current product tree passed in : %s", productTree));

        String[] exclusionArray = exclusionString.split(",");
        LOGGER.debug(String.format("Split Pair Values: %s", Arrays.toString(exclusionArray)));

        ArrayList<String> exclusionList = new ArrayList<>(Arrays.asList(exclusionArray));

        for (String exclusion :
                exclusionList) {
            if (exclusion.toLowerCase().contains(productTree.toLowerCase())){
                LOGGER.debug("Match found for product tree");
                return true;
            }
        }
        return false;
    }
}
