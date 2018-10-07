/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorysystem_keaganhilliard.Model;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
/**
 *
 * @author keagan
 */
public class InHousePart extends Part {
    private final IntegerProperty machineID;
    
    public InHousePart() {
        super();
        this.machineID = new SimpleIntegerProperty();
    }
    
    public InHousePart(String name, Integer ID) {
        super(name, ID);
        this.machineID = new SimpleIntegerProperty();
    }
    
    public void setMachineID(int machineID) {
        this.machineID.set(machineID);
    }
    
    public int getMachineID() {
        return machineID.get();
    }
    
    public IntegerProperty machineIDProperty() {
        return machineID;
    }
}
