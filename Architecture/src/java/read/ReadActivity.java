/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package read;

import model.Activity;

/**
 *
 * @author Camila
 */
public class ReadActivity {

    public ReadActivity() {
    }

    public Activity GetActivity(String id) { // retorna agent
        Activity activity = new Activity(id);
        return activity;
    }

}
