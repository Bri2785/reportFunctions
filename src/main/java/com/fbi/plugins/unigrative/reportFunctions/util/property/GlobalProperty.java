package com.fbi.plugins.unigrative.reportFunctions.util.property;

import com.fbi.plugins.unigrative.reportFunctions.util.property.reader.Reader;

public final class GlobalProperty<T> extends PluginProperty<T>
{
    public GlobalProperty(final String key, final Reader<T> transformer) {
        super(key, transformer);
    }
    
    public T get(final PropertyGetter plugin) {
        return this.reader.read(plugin.getProperty(this.getKey()));
    }
    
    public String getKey() {
        return this.key;
    }
}
