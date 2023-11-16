package pl.edu.uj.javaframe;

public abstract class Value{
    protected Object value;

    public abstract Value create(String val);
    public abstract  Value add(Value v);

    /**** YOUR CODE HERE *****/

    @Override
    public String toString() {
        return value.toString();
    }

    public void print(){System.out.print(toString());}
}
