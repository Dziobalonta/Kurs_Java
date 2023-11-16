package pl.edu.uj.javaframe;
import java.lang.Math;

public class Int extends Value{
    @Override
    public Value create(String val) {
        Int v = new Int();
        v.value = Integer.parseInt(val);
        return v;
    }

    @Override
    public Value add(Value v) {

        Int result  = new Int();
        if(v.value instanceof Integer){
            result.value = (Integer)this.value + (Integer)v.value;
        }else{
            result.value = (Integer)this.value + Double.valueOf(v.value.toString()).intValue();
        }
        return result;
    }

    @Override
    public Value sub(Value v) {
        Int result  = new Int();
        if(v.value instanceof Integer){
            result.value = (Integer)this.value - (Integer)v.value;
        }else{
            result.value = (Integer)this.value - Double.valueOf(v.value.toString()).intValue();
        }
        return result;
    }

    @Override
    public Value mul(Value v) {
        Int result  = new Int();
        if(v.value instanceof Integer){
            result.value = (Integer)this.value * (Integer)v.value;
        }else{
            result.value = (Integer)this.value * Double.valueOf(v.value.toString()).intValue();
        }
        return result;
    }

    @Override
    public Value div(Value v) {
        Int result  = new Int();
        if(v.value instanceof Integer){
            result.value = (Integer)this.value / (Integer)v.value;
        }else{
            result.value = (Integer)this.value / Double.valueOf(v.value.toString()).intValue();
        }
        return result;
    }

    @Override
    public Value pow(Value v) {
        Int result  = new Int();
        if(v.value instanceof Integer){
            result.value = (int)Math.pow( (Integer)this.value,  (Integer)v.value );
        }else{
            result.value = (int)Math.pow((Integer)this.value, Double.valueOf(v.value.toString()).intValue());
        }
        return result;
    }

    @Override
    public boolean eq(Value v) {
        boolean result;
        if(v.value instanceof Integer){
            result = (int)this.value == (int)v.value;
        }else{
            result = (Integer)this.value == Double.valueOf(v.value.toString()).intValue();
        }
        return result;
    }

    @Override
    public boolean lte(Value v) { // less than
        boolean result;
        if(v.value instanceof Integer){
            result = (int)this.value <= (int)v.value;
        }else{
            result = (Integer)this.value <= Double.valueOf(v.value.toString()).intValue();
        }
        return result;
    }

    @Override
    public boolean gte(Value v) { // greater than

        boolean result;
        if(v.value instanceof Integer){
            result = (int)this.value >= (int)v.value;
        }else{
            result = (Integer)this.value >= Double.valueOf(v.value.toString()).intValue();
        }
        return result;
    }

    @Override
    public boolean neq(Value v) { //not equal
        boolean result;
        if(v.value instanceof Integer){
            result = (int)this.value != (int)v.value;
        }else{
            result = (Integer)this.value != Double.valueOf(v.value.toString()).intValue();
        }
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) { // jesli ten sam obiekt
            return true;
        }
        if (other == null || getClass() != other.getClass()) { // jesli inny typ lub null
            return false;
        }
        Int konwersja = (Int) other;

        return (int)this.value == (int)konwersja.value;
    }

    @Override
    public int hashCode() {

        int result = 11;
        result = 31 * result;
        return result;
    }
}
