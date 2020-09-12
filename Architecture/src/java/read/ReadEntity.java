/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package read;

import model.Entityprov;

/**
 *
 * @author Camila
 */
public class ReadEntity {

    public ReadEntity() {
    }

    public Entityprov GetEntity(String id) { // retorna agent
        String name = "";
        Entityprov entity = new Entityprov(id, name);
        return entity;
    }

}
