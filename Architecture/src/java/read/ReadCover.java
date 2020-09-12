/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package read;

import dao.SourceCodeDAO;
import dao.TestingMethodDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Covers;
import model.Sourcecode;
import model.StructCoversSourceCode;
import model.Testingmethod;

/**
 *
 * @author Camila
 */
public class ReadCover {

    public ReadCover() {
    }
    
    public List<Covers> readCover(List<StructCoversSourceCode> listStructCoversSourceCode) throws ClassNotFoundException, SQLException{
        List<Covers> listCover = new ArrayList<>();
        List<Testingmethod> listName = new ArrayList<>();
        List<Sourcecode> listNameSourceCode = new ArrayList<>();
        
        for(StructCoversSourceCode a: listStructCoversSourceCode){
            listName = TestingMethodDAO.obterTestingmethodName(a.getNameTest());
            listNameSourceCode = SourceCodeDAO.obterSourcecodeName(a.getSourceCode().getName());
//            Covers covers = new Covers(listNameSourceCode.get(0), listName.get(0));
//            listCover.add(covers);
        }
         return listCover;
    }
    
}
