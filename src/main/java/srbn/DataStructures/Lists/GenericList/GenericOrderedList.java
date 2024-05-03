package srbn.DataStructures.Lists.GenericList;

public class GenericOrderedList<T>{
    private NodeGeneric<T> head;
    private int size;

    public GenericOrderedList(){
        head = null;
        size = 0;
    }

    public GenericOrderedList(T[] array){
        for(int i = 0; i < array.length; i++){
            add(array[i]);
        }
    }

    public void add(T data){
        NodeGeneric<T> newNode = new NodeGeneric<>(data);
        if(head == null){
            head = newNode;
        }else{
            NodeGeneric<T> current = head;
            NodeGeneric<T> previous = null;
            while(current != null){
                previous = current;
                current = current.getNext();
            }
            if(previous == null){
                newNode.setNext(head);
                head = newNode;
            }else{
                previous.setNext(newNode);
                newNode.setNext(current);
            }
        }
        size++;
    }


    public void remove(T data){
        if(head != null){
            NodeGeneric<T> current = head;
            NodeGeneric<T> previous = null;
            while(current != null && ((Comparable<T>)current.getData()).compareTo(data) != 0){
                previous = current;
                current = current.getNext();
            }
            if(current != null){
                if(previous == null){
                    head = head.getNext();
                }else{
                    previous.setNext(current.getNext());
                }
                size--;
            }
        }
    }

    public T get(int index){
        if(index >= 0 && index < size){
            NodeGeneric<T> current = head;
            for(int i = 0; i < index; i++){
                current = current.getNext();
            }
            return current.getData();
        }
        return null;
    }

    public int getSize(){
        return size;
    }

    public void print(){
        NodeGeneric<T> current = head;
        while(current != null){
            System.out.println(current.getData());
            current = current.getNext();
        }
    }


}
