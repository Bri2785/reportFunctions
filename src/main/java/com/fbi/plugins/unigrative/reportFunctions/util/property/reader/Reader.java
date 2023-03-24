
package com.fbi.plugins.unigrative.reportFunctions.util.property.reader;

@FunctionalInterface
public interface Reader<T>
{
    T read(final String p0);
}
