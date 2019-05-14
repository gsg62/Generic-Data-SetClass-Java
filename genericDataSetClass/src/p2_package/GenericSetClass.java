
package p2_package;

/**
 * Class for managing sets of GenericData that extend Comparable
 * @author greg geary
 */
public class GenericSetClass<GenericData extends java.lang.Comparable
         <GenericData>>extends java.lang.Object
{
    private int arrayCapacity;
    private int arraySize;
    private java.lang.Object[] genericSetArray;
    public static final int DEFAULT_ARRAY_CAPACITY = 10;
    public static final int BASE_TWO = 2;
    
    /**
     * Default constructor
     * <p>
     * Initializes set array, sets power set array to null
     */
    public GenericSetClass()
    {
        genericSetArray = new Object[DEFAULT_ARRAY_CAPACITY];
        arrayCapacity = DEFAULT_ARRAY_CAPACITY;
        arraySize = 0;
    }
    /**
     * Initialization constructor
     * <p>
     * Allows specification of set array capacity
     * <p>
     * Initializes set array, sets power set array to null
     * @param initialCapacity 
     */
    public GenericSetClass(int initialCapacity)
    {
        arrayCapacity = initialCapacity;
        genericSetArray = new Object[arrayCapacity];       
        arraySize = 0;  
    }
    /**
     * Copy constructor
     * <p>
     * Duplicates copied set class
     * <p>
     * Also responsible for correct initialization/update 
     * of set class array and power set array
     * @param copied - GenericSetClass object to be copied 
     */
    public GenericSetClass(GenericSetClass<GenericData> copied)
    {
        this.arraySize = copied.arraySize;
        this.arrayCapacity = copied.arrayCapacity;
        this.genericSetArray = new Object[this.arrayCapacity];
         
        int index = 0;
        // adds all data into duplicate generic setArray 
        while(index < this.arraySize) 
        {
            this.genericSetArray[index] = copied.genericSetArray[index];
            index++;
        }
    }
    /**
     * Adds generic element to set
     * <p>
     * increases capacity using checkForResize if array is full
     * <p>
     * does not allow duplicate values to be added to the set
     * <p>
     * @param item - GenericData value to be added to set 
     */
    public void addItem(GenericData item)
    {
        checkForResize(); 
        // adds item if unique and increments array size
        if( !hasElement(item) )
        {
            this.genericSetArray[arraySize] = (Object) item;
            this.arraySize++;
        }
    }
    /**
     * Local function tests for resize of the set array
     * <p>
     * If array needs to be resized, array capacity is doubled;
     * otherwise, no change
     * <p>
     * @return boolean report that resize was conducted
     */
    private boolean checkForResize()
    {
        // arraySize check
        if( arrayCapacity == arraySize )
        {
            arrayCapacity = 2*arrayCapacity;
            // saves values from last setArray and 
            // creates new larger genericSetArray
            Object[] copyValues = this.genericSetArray;
            this.genericSetArray = new Object[this.arrayCapacity];    
            int index = 0;
            // adds all values into new array
            while( index < this.arraySize ) 
            {
                this.genericSetArray[index] = copyValues[index];
                index++;
            }
            return true;
        }
        return false;
    }
    /**
    * Provides list of set array elements as comma-delimited string
    * <p>
    * @overrides toString in class java.lang.Object
    * <p>
    * @returns String holding objects from array
    */
    @SuppressWarnings("Unchecked")
    @Override
    public java.lang.String toString()
    {
        String setArrayString = ""; 
        int index = 0;
        // adds each element from the setArray to a string with comma and spaces
        while( index < arraySize )
        {
            setArrayString += (GenericData) 
                    this.genericSetArray[index].toString();
            // checks to not add comma after last element
            if( index != (arraySize - 1) )
            {
                setArrayString += ", ";
            }
            index++;
        }
        return setArrayString;
    }
    /**
     * Tests to indicate if integer value is one of the set elements
     * <p>
     * @param testElement - integer element to be found in set
     * <p>
     * @return boolean result of test
     */
    @SuppressWarnings("Unchecked")
    public boolean hasElement(GenericData testElement)
    {
        int index = 0;
        GenericData gd1, gd2;
        // loops through setArray to check for element
        while(index < this.arraySize) 
        {         
            gd1 = (GenericData) genericSetArray[index];
            gd2 = testElement;
            // tests whether values are equal
            if( gd1.compareTo(gd2) == 0 )
            {
                return true;
            } 
            index++;
        }
        return false;
    }
    /**
     * Removes value at given index; moves all succeeding data down in array
     * <p>
     * @param indexToRemove - integer index of element value to remove 
     */
    private void removeAtIndex( int indexToRemove )
    {
        // clears element from genericSetArray
        genericSetArray[indexToRemove] = null;         
        // moves the rest of the items one space back in the array
        while( indexToRemove < arraySize - 1 )
        {
            genericSetArray[indexToRemove] = genericSetArray[indexToRemove + 1];
            indexToRemove++;
        }
        arraySize -= 1;
    }
    /**
     * Removes value if it is found in set
     * <p>
     * @param valToRemove - integer value to be removed
     * <p>
     * @return boolean result of operation success
     */
    public boolean removeValue( GenericData valToRemove )
    {
        int index = 0;
        int saveIndex = 0;
        GenericData gd1;
        // checks whether or not element is in setArray
        if( hasElement(valToRemove) ) 
        {
            // if it is, this loop will save the index it is at
            while( index < arraySize ) 
            {
                gd1 = (GenericData) genericSetArray[index];
                if( gd1.compareTo(valToRemove) == 0)
                {
                    saveIndex = index;    
                }
                index++;
            }
            // then removes at index and returns true
            removeAtIndex( saveIndex );
        return true; 
        }
        return false;
    }
    
    /**
     * Returns the intersection of THIS set and the given other set
     * <p>
     * @param other - SetClass data with which intersection is found
     * <p>
     * @return SetClass object with intersection of two sets
     */
    @SuppressWarnings("Unchecked")
    public GenericSetClass<GenericData> findIntersection(
            GenericSetClass<GenericData> other)
    {
        GenericSetClass<GenericData> intersectSet = new GenericSetClass();       
        int index = 0;
        int intersectSetSize = 0;
        // loops through both arrays to find same values and add to new set
        while( index < other.arraySize )
        {
            if( this.hasElement((GenericData) other.genericSetArray[index]) )
            {
                intersectSet.addItem((GenericData)
                        other.genericSetArray[index]);
                
            }
            index++;
        }
        return intersectSet;
    }  
    /**
     * Returns the union of THIS set and the give other set
     * <p>
     * @param other - SetClass data with which union is found
     * <p>
     * @return SetClass object with union of two sets
     */
    @SuppressWarnings("Unchecked")
    public GenericSetClass<GenericData> findUnion(GenericSetClass
            <GenericData> other)
    {
        // creates copy of first set
        GenericSetClass<GenericData> unionSet = new GenericSetClass(other);
        int index = 0;
        // adds all values from this set to other
        while( index < this.arraySize )
        {
            unionSet.addItem((GenericData) this.genericSetArray[index]);
            index++;
        }
        return unionSet;
    }
    /**
     * Finds relative complement of THIS set in given other set
     * <p>
     * Returns other set having removed any items intersecting with THIS set
     * <p>
     * @param other - SetClass object from which THIS SetClass items 
     * will be removed
     * <p>
     * @return SetClass object with data as specified
     */
    @SuppressWarnings("Unchecked")
    public GenericSetClass<GenericData> findRelativeComplementOfThisSetIn(
            GenericSetClass<GenericData> other)
    {
        // creates copy of other
        GenericSetClass<GenericData> otherCopy = new GenericSetClass(other);
        // finds and copies the intersecting set
        GenericSetClass<GenericData> intersectingSet = 
                new GenericSetClass(this.findIntersection(other));
        int index = 0;
        // removes all values that intersect
        while( index < intersectingSet.arraySize )
        {
            otherCopy.removeValue((GenericData) 
                    intersectingSet.genericSetArray[index]);
            index++;
        }
        return otherCopy;
    }
    /**
     * copies one Object array to another
     * <p>
     * @param destArray - Object array being copied to
     * @param sourceArray - Object array being copied from 
     */
    public void copyArray(java.lang.Object[] destArray,
                       java.lang.Object[] sourceArray)
    {
        int index = 0;
        while( index < sourceArray.length ) 
            {
                sourceArray[index] = destArray[index];
                index++;
            }
    }
    /**
     * Swaps values within given array
     * <p>
     * @param localArray - array of Objects used for swapping
     * @param indexOne - integer index for one of the two items to be swapped
     * @param indexOther - integer index for 
     * the other of the two items to be swapped 
     */
    public void swapValues(java.lang.Object[] localArray,
                        int indexOne,
                        int indexOther)
    {
        // saves values to local variables
        Object indexOneValue = localArray[indexOne];
        Object indexOtherValue = localArray[indexOther];
        // swaps values
        localArray[indexOne] = indexOtherValue;
        localArray[indexOther] = indexOneValue;
    }
    
    /**
     * Description: Sorts elements using the bubble sort algorithm
     * <p>
     * @return String data holding list of sorted objects
     */
    @SuppressWarnings("Unchecked")
    public java.lang.String runBubbleSort()
    {
        long startTime = System.nanoTime();

        int index = 0;
        boolean swapFlag = true;
        GenericData gd1, gd2;
        int compareVal;
        while( swapFlag == true )
        {
            swapFlag = false;
            index = 0;
            while( index < (arraySize - 1) )
            {
                // organizes data for comparing
                gd1 = (GenericData) genericSetArray[index];
                gd2 = (GenericData) genericSetArray[index + 1];              
                // if greater than, move frowards
                compareVal = gd1.compareTo(gd2);
                if( compareVal > 0 )
                {
                    swapValues(genericSetArray, index, index + 1);
                    swapFlag = true;
                    
                    
                }
                index++;                
            }
        }
        long endTime = System.nanoTime();
        String time = Long.toString((endTime - startTime));
        //System.out.println("bubble sort time: " + time);
        return this.toString();
    }
    @SuppressWarnings("Unchecked")
    public java.lang.String runInsertionSort()
    {
        // create indexes and GD
        int index1;
        int index2;
        int compVal;
        GenericData saveGD;
        for( index2 = 1; index2 < arraySize; index2++ )
        {
            // saves first loop elemnt to test if less than
            saveGD = (GenericData) this.genericSetArray[index2];
            // compares to the rest of elements moving backwards
            for( index1 = index2; index1 >= 0; index1-- )
            {
                compVal = saveGD.compareTo((GenericData)
                        this.genericSetArray[index1]);
                // if less than, swap indexes
                if( compVal < 0 )
                {
                    swapValues(this.genericSetArray, index1, index1 + 1);
                }
            }
        }
        return toString();
    }
    @SuppressWarnings("Unchecked")
    public java.lang.String runSelectionSort()
    {
        int minSaveIndex;
        int index;
        int swapIndex;
        int compVal; 
        GenericData minGD;
        for( swapIndex = 0; swapIndex < arraySize; swapIndex++)  
        {
            // saves temporary min and index of
            minGD = (GenericData) this.genericSetArray[swapIndex];
            minSaveIndex = swapIndex;
            // looks for new min
            for( index = swapIndex; index < arraySize; index++)
            {
                compVal = minGD.compareTo((GenericData)
                        genericSetArray[index]);
                // if next min is found, save value and index
                if( compVal > 0 )
                {
                    minGD = (GenericData) this.genericSetArray[index];
                    minSaveIndex = index;
                }
            }
            // moves temporary min to next up position in set
            swapValues(this.genericSetArray, swapIndex, minSaveIndex);
        }
        return toString();
    }
}
