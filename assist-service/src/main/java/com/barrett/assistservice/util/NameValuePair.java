package com.barrett.assistservice.util;

import java.io.Serializable;
import org.apache.commons.httpclient.util.LangUtils;

public class NameValuePair implements Serializable {
    private String name;
    private String value;

    public NameValuePair() {
        this((String)null, (String)null);
    }

    public NameValuePair(String name, String value) {
        this.name = null;
        this.value = null;
        this.name = name;
        this.value = value;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public String toString() {
        return "name=" + this.name + ", " + "value=" + this.value;
    }

    public boolean equals(Object object) {
        if (object == null) {
            return false;
        } else if (this == object) {
            return true;
        } else if (!(object instanceof NameValuePair)) {
            return false;
        } else {
            NameValuePair that = (NameValuePair)object;
            return LangUtils.equals(this.name, that.name) && LangUtils.equals(this.value, that.value);
        }
    }

    public int hashCode() {
        int hash = 17;
        hash = LangUtils.hashCode(hash, this.name);
        hash = LangUtils.hashCode(hash, this.value);
        return hash;
    }
}

