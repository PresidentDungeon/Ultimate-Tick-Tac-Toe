/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package button;

import com.jfoenix.controls.JFXButton;

/**
 *
 * @author ander
 */
public class CustomButton extends JFXButton{
    
    private int x;
    private int y;
    private int microId;

    public CustomButton(int x, int y) {
        this.x = x;
        this.y = y;
        this.microId = getmicroBoardId(x, y);
        
        
        
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getMicroId() {
        return microId;
    }

    public void setMicroId(int microId) {
        this.microId = microId;
    }
    
    public int getmicroBoardId(int x, int y)
    {
        
        int microX = x/3;
        int microY = y/3;
        
        if (microX == 0 && microY == 0)
        {
            return 0;
        }
        else if (microX == 1 && microY == 0)
        {
            return 1;
        }
        else if (microX == 2 && microY == 0)
        {
            return 2;
        }
        else if (microX == 0 && microY == 1)
        {
            return 3;
        }
        else if (microX == 1 && microY == 1)
        {
            return 4;
        }
        else if (microX == 2 && microY == 1)
        {
            return 5;
        }
        else if (microX == 0 && microY == 2)
        {
            return 6;
        }
        else if (microX == 1 && microY == 2)
        {
            return 7;
        }
        else if (microX == 2 && microY == 2)
        {
            return 8;
        }
        return -1;
    }
    

    
    
    
    
}
