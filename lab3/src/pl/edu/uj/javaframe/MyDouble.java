package pl.edu.uj.javaframe;

public class MyDouble extends Value {
    @Override
    public Value create(String val) {
        MyDouble v = new MyDouble();
        v.value = Double.parseDouble(val);
        return v;
    }

    @Override
    public Value add(Value v) {
        MyDouble result  = new MyDouble();
        result.value = (Double)this.value + Double.valueOf(v.value.toString());

        return result;

    }

    @Override
    public Value sub(Value v) {
        MyDouble result  = new MyDouble();
        if(v.value instanceof Double){
            result.value = (Double)this.value - (Double)v.value;
        }else{
            result.value = (Double)this.value - Double.valueOf(v.value.toString());
        }
        return result;
    }

    @Override
    public Value mul(Value v) {
        MyDouble result  = new MyDouble();
        if(v.value instanceof Double){
            result.value = (Double)this.value * (Double)v.value;
        }else{
            result.value = (Double)this.value * Double.valueOf(v.value.toString());
        }
        return result;
    }

    @Override
    public Value div(Value v) {
        MyDouble result  = new MyDouble();
        if(v.value instanceof Double){
            result.value = (Double)this.value / (Double)v.value;
        }else{
            result.value = (Double)this.value / Double.valueOf(v.value.toString());
        }
        return result;
    }

    @Override
    public Value pow(Value v) {
        MyDouble result  = new MyDouble();
        if(v.value instanceof Double){
            result.value = Math.pow( (Double)this.value,  (Double)v.value );
        }else{
            result.value = Math.pow((Double)this.value, Double.valueOf(v.value.toString()));
        }
        return result;
    }

    @Override
    public boolean eq(Value v) {
        return (Double)this.value == Double.parseDouble(v.value.toString());
    }

    @Override
    public boolean lte(Value v) { // less than
        return (Double)this.value <= Double.parseDouble(v.value.toString());
    }

    @Override
    public boolean gte(Value v) { // greater than
        return (Double)this.value >= Double.parseDouble(v.value.toString());

    }

    @Override
    public boolean neq(Value v) { //not equal
        return (Double) this.value != Double.parseDouble(v.value.toString());
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) { // jesli ten sam obiekt
            return true;
        }
        if (other == null || getClass() != other.getClass()) { // jesli inny typ lub null
            return false;
        }
        MyDouble konwersja = (MyDouble) other;

        return (double)this.value == (double)konwersja.value;
    }

    @Override
    public int hashCode() {

        int result = 13;
        result = 31 * result;
        return result;
    }
}
