// 
// Decompiled by Procyon v0.5.30
// 

package com.fbi.plugins.unigrative.reportFunctions.util.property.reader;

public class LongReader implements Reader<Long>
{
    @Override
    public Long read(final String value) {
        try {
            return Long.valueOf(value);
        }
        catch (NumberFormatException e) {
            return null;
        }
    }
}
