package com.image.processing.Struct;

/**
 * Created by root on 20/04/2018.
 */

public class CascadeStruct {

    private int id;
    private int cascadeFile;
    private String cascadeName;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCascadeFile() {
        return cascadeFile;
    }

    public void setCascadeFile(int cascadeFile) {
        this.cascadeFile = cascadeFile;
    }

    public String getCascadeName() {
        return cascadeName;
    }

    public void setCascadeName(String cascadeName) {
        this.cascadeName = cascadeName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
