package com.example.library2;

public class person {
    public String name;
    public String branch;
    public String component;
    public int id;

    public  person( String name1,String branch1,String component1){
        name=name1;
        branch=branch1;
        component= component1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getComponent() {
        return component;
    }


    public void setComponent(String component) {
        this.component = component;
    }
}
