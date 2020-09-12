/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traceability;

/**
 *
 * @author Camila
 */
public class Construct {
    
    private String type;
    private String nameConstruct;
    private String nameClass;

    public Construct() {
    }

    public Construct(String type, String nameConstruct, String nameClass) {
        this.type = type;
        this.nameConstruct = nameConstruct;
        this.nameClass = nameClass;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNameConstruct() {
        return nameConstruct;
    }

    public void setNameConstruct(String nameConstruct) {
        this.nameConstruct = nameConstruct;
    }

    public String getNameClass() {
        return nameClass;
    }

    public void setNameClass(String nameClass) {
        this.nameClass = nameClass;
    }
    
    
    
}
