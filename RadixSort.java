import java.util.*;

class Main{
    static int[] arr = {234,7373,52,342,624};
    static MyStack.Column[] base = MyStack.fillBase();
    static int digit;
    static int lengthValue = 0;
    static int minusOne = -1;

    public static void main(String args[]){
        for(int i =0; i<arr.length;i++){
            arr[i]=Integer.valueOf(arr[i]);
        }
        printArr();
        System.out.println(" ");
        digit = countDigitsValue(greaterValueArray(arr))-1;
        while(digit >= 0){
            result();
            digit--;
            printArr();
            System.out.println(" ");
        }
    }

    static void result(){
        String m;
        char c;
        int valueToPush;
        for(int j=0; j<arr.length;j++){
            lengthValue = countDigitsValue(arr[j])+minusOne;
            valueToPush = arr[j];
            m = String.valueOf(valueToPush);
            try{
                c = m.charAt(lengthValue);
            }
            catch(StringIndexOutOfBoundsException a){
                c = '0';
            }
            MyStack.push(base,Integer.parseInt(String.valueOf(c)),valueToPush);
        }
        printColumns(base);
        int x = 0;
        for(int p=0; p<base.length;p++){
            if(base[p].pushPointer!=0){
                while(base[p].items > 0){
                    arr[x] = MyStack.pop(base,p);
                    x++;
                }
            }
        }
        minusOne--;
    }

    static int countDigitsValue(int valueToPush){
        int length = 0;
        length = String.valueOf(valueToPush).length();
        //System.out.print(length);
        return length;
    }

    static int greaterValueArray(int[] arr){
        int greaterValue =  arr[0];
        for(int u = 0; u+1<arr.length; u++){
            if(arr[u+1]>greaterValue){
                greaterValue = arr[u+1];
            }
        }
        return greaterValue;
    }
    static void printArr(){
        for(int i : arr){
            System.out.print(i+" ");

        }
        System.out.println(" ");
    }
    static void printColumns(MyStack.Column[] base){
        for(int i=9; i>-1; i--){
            for(int j=0; j<10; j++){
                System.out.print(base[j].stack[i]+" ");
            }
            System.out.println(" ");
        }
        System.out.println(" ");
    }
}

class MyStack{
    static int item = 0;
    static Column[] base = new Column[10];
    static class Column{
        int[] stack = new int[10];
        int pushPointer = 0;
        int items = 0;
    }

    //push,pop,fillbase methods for stack
    static Column[] fillBase(){
        for(int i = 0; i<10; i++){
            base[i] = new Column();
        }
        return base;
    }

    static void push(Column[] base, int columnIndex,int pushValue){
        base[columnIndex].stack[base[columnIndex].pushPointer] = pushValue;
        base[columnIndex].pushPointer++;
        base[columnIndex].items++;
    }

    static int pop(Column[] base, int columnIndex){
        int pointer_1;
        int return_value =  base[columnIndex].stack[0];
        for(int i=0; i<base[columnIndex].items; i++){
            pointer_1 = base[columnIndex].stack[i+1];
            base[columnIndex].stack[i] = pointer_1;
        }
        base[columnIndex].items--;
        base[columnIndex].pushPointer--;
        return return_value;
    }
}