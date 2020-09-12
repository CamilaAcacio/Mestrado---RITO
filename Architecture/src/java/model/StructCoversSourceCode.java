/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;

/**
 *
 * @author Camila
 */
public class StructCoversSourceCode {
    private String nameTest;
    private Sourcecode sourceCode;

    public StructCoversSourceCode(String nameTest, Sourcecode sourceCode) {
      
        this.nameTest = nameTest;
        this.sourceCode = sourceCode;
    }

    public StructCoversSourceCode() {
    }

    public String getNameTest() {
        return nameTest;
    }

    public void setNameTest(String nameTest) {
        this.nameTest = nameTest;
    }

    public Sourcecode getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(Sourcecode sourceCode) {
        this.sourceCode = sourceCode;
    }
    
    
    
}
