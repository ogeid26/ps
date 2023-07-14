package org.austral.edu;

import org.austral.edu.Exceptions.ConstantVariableException;

public class DataObject {

    public String type;
    public String value;
    public String keyword;

    public DataObject() {
        this.type = null;
        this.value = null;
        this.keyword = "let";
    }

    public void setType(String type) throws ConstantVariableException {
        if (canOverride()) {
            this.type = type;
        } else {
            throw new ConstantVariableException();
        }
    }

    public void setValue(String type) throws ConstantVariableException {
        if (canOverride()) {
            this.type = type;
        } else {
            throw new ConstantVariableException();
        }
    }

    private boolean canOverride() {
        return keyword.equals("let");
    }
}
