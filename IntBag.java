package Lab01_Summer;

import java.util.Scanner;

/**
 * IntBag
 */
public class IntBag {
    private int[] bag;
    private boolean fibo = false;
    static boolean addedPrompt = false;
    public static void main(String[] args) {
        int choice = 0;
        IntBag ib;
        boolean created = false;
        Scanner in = new Scanner(System.in);
        ib = new IntBag();
        while (choice != 8) {
            System.out.println("1-Create a new empty collection(any previous values are lost!)\n" + //
                "2-Read a set of positive values into the collection\n" + //
                "3-Print the collection of values.\n" + //
                "4-Add a value to the collection of values at a specified location\n" + //
                "5-Remove the value at a specified location from the collection of values\n" + //
                "6-Remove all instances of a value within the collection.\n" + //
                "7-Create fibonacci.\n" + //
                "8-Quit the program.");
        System.out.print("What is your choice: ");
        choice = in.nextInt();
        System.out.println();
        
        
        if (choice == 1) {
            ib = new IntBag();
            System.out.println("New collection is created!!!");
        }
        else if(choice == 2)
        {
            boolean zero = false;
            int num = -1;
            do {
                System.out.print("Write an integer to add (To stop write zero): ");
                num = in.nextInt();
                if (num == 0) {
                    zero = true;
                }else{
                    ib.add(num);
                }
            } while (!zero);
            ib.add(-1);
            System.out.println("Numbers are added!!");
            System.out.println();

        }
        else if(choice == 3)
        {
            System.out.println("The collection is: " + ib);
        }
        else if(choice == 4)
        {
            System.out.print("Which value you want to add: ");
            int num = in.nextInt();
            System.out.println();
            System.out.print("Which index you want to put in: ");
            int index = in.nextInt();
            System.out.println();
            ib.add(num, index);
            if (addedPrompt) {
                System.out.println("Numbers are added to the collection!");
            }else
            {
               System.out.println("The index is out of bounds");
            }

        }
        else if(choice == 5)
        {
            System.out.print("What index do you want to remove: ");
            int index = in.nextInt();
            ib.remove(index);
        }
        else if(choice == 6)
        {
            System.out.print("What number do you want to remove: ");
            int num = in.nextInt();
            ib.removeAll(num);
        }else if(choice == 7)
        {
            ib = new IntBag();
            System.out.print("What is the limit of the fibonacci calculations: ");
            int limit = in.nextInt();
            ib.fibonacci(limit);
        }
    }
        
    }
    public IntBag()
    {
        bag =  new int[4];
    }
    public void add(int n)
    {
        removeAll(-1);
        boolean added = false;
        for(int i = 0; i < bag.length; i++)
        {
            if (fibo) {
                if (i == 0) {
                
                }   
                else
                {
                if(bag[i] == 0)
                {
                    bag[i] = n;
                    i = bag.length;
                    added = true;
                }
                }
            }
            else
            {
                if(bag[i] == 0)
                {
                    bag[i] = n;
                    i = bag.length;
                    added = true;
                }
            }
            
        }
        if(!added)
        {
            int[] temp = new int[bag.length*2];
            int k = 0;
            for (int i = 0; i < bag.length; i++) {
                temp[i] = bag[i];
                k++;
            }
            temp[k] = n;
            bag = temp;
        }
    }
    public int[] add(int n, int index)
    {

        boolean indexInBound = false;
        if (index > 0 && index < bag.length) {
            indexInBound = true;
            addedPrompt = true;
        }
        if (indexInBound) {
            if(bag[index] == 0)
        {
            bag[index] = n;
            return bag;
        }
        else
        {
            int elementNum = 0;
            for (int i = 0; i < bag.length; i++) {
                if(bag[i] != 0)
                {
                    elementNum++;
                }
            }
            int[] temp;
            if(elementNum+1>bag.length)
            {
                temp = new int[bag.length*2];
            }else{
                temp = new int[bag.length];
            }
            for(int i = 0; i < index; i++)
            {
                temp[i] = bag[i];
            } 
            temp[index] = n;
            for(int i = index+1; i < bag.length+1; i++)
            {
                
                if(i > elementNum+1)
                {
                    temp[i-1] = 0;
                }else
                {
                    if(i != bag.length)
                    {
                        temp[i] = bag[i-1];
                    }
                }
            }
            bag = temp;
            return bag;
        }
    }
    return bag;
        
    }
    public void remove(int index)
    {
        boolean empty = false;
        if (getValue(index) == 0) {
            empty = true;
        }
        if (!empty) {
            boolean found = false;
            int k = bag.length - 1;
            for (int i = bag.length-1; i > 0; i--) {
                if (!found) {
                    if (getValue(i) != 0) {
                        found = true;
                        k = i;
                    }
                }
            }
            bag[index] = bag[k];
            bag[k] = 0;
        }
    }
    public void removeAll(int num)
    {
        int count = 0;
        for (int i : bag) {
            if (i == num) {
               count++;
            }
        }
        for (int j = 0; j < count; j++){
            for (int i = 0; i < bag.length; i++) {
                if(getValue(i) == num)
                {
                    bag[i] = 0;
                }
            }
        }
    }
    public int getElementNum()
    {   
        int sum = 0;
        for (int i : bag) {
            if (i != 0) {
                sum++;
            }
        }
        return sum;
    }

    public int[] fibonacci(int limit)
    {
        fibo = true;
        add(0);
        add(1);
        for (int i = 2; i < limit; i++) {
            add((getValue(i-1)+getValue(i-2)));
        }
        return bag;
    }
    public int getValue(int index)
    {
        if (index < bag.length) {
            return bag[index];
        }
        return 0;
    }
    public String toString()
    {
        String result = "";
        result += "[ ";
        if(fibo)
        {
        for (int i = 0; i < bag.length; i++) {
            if (i == 0) {
                result += bag[i] + " ";
            }else{
                if (bag[i] > 0) {
                    result += bag[i] + " ";
                }else{
                    i = bag.length;
                }
                }
            }
                
        }else{
        for (int i = 0; i < bag.length; i++) {
                if (bag[i] > 0) {
                    result += bag[i] + " ";
                }else{
                    if (i>getElementNum()) {
                        i = bag.length;
                    }
                }
            }
        }
        result += "]";
        
        return result;
    }
}