package com.example2017.android.admin;

/**
 * Created by M7moud on 18-Jan-18.
 */
public class CodeValue {

    String person1,person2,person3,person4,person5;

    public CodeValue() {
    }

    public CodeValue(String person1, String person2, String person3, String person4, String person5) {
        this.person1 = person1;
        this.person2 = person2;
        this.person3 = person3;
        this.person4 = person4;
        this.person5 = person5;
    }

    public String getPerson1() {
        return person1;
    }

    public void setPerson1(String person1) {
        this.person1 = person1;
    }

    public String getPerson2() {
        return person2;
    }

    public void setPerson2(String person2) {
        this.person2 = person2;
    }

    public String getPerson3() {
        return person3;
    }

    public void setPerson3(String person3) {
        this.person3 = person3;
    }

    public String getPerson4() {
        return person4;
    }

    public void setPerson4(String person4) {
        this.person4 = person4;
    }

    public String getPerson5() {
        return person5;
    }

    public void setPerson5(String person5) {
        this.person5 = person5;
    }
}
