/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p2_package;

/**
 *
 * @author ggear
 */
//import java.util.Comp
public class IntDataClass extends java.lang.Object
implements java.lang.Comparable<IntDataClass>
{
    private int	dataVal;
    
    public IntDataClass(){}
    
    public IntDataClass(int initialValue)
    {
        dataVal = initialValue;
    }
    public IntDataClass(IntDataClass copied)
    {
        dataVal = copied.dataVal;
    }
    public void setValue(int newValue)
    {
        dataVal = newValue;
    }
    public int getValue()
    {
        return dataVal;
    }
    public java.lang.String toString()
    {
        return Integer.toString(dataVal);  
    }

    @Override
    public int compareTo(IntDataClass other) 
    {   
        return this.getValue() - other.getValue();
    }
    

    
}
