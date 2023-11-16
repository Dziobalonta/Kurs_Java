package pl.edu.uj.javaframe;
import java.lang.Math;
import java.util.Objects;

public class ImaginaryDouble extends MyDouble {

    protected Object im;

    @Override
    public Value create(String val)
    {
        ImaginaryDouble result = new ImaginaryDouble();
        if (val.contains("i")) {
            String[] parts = val.split("i");
            result.value = Double.parseDouble(parts[0]);
            result.im = Double.parseDouble(parts[1]);

        } else {
            result.value = Double.parseDouble(val);
        }
        return result;
    }
    @Override
    public Value add(Value v) {
        ImaginaryDouble result = new ImaginaryDouble();

        if (v instanceof ImaginaryDouble) {
            ImaginaryDouble imaginaryDouble = (ImaginaryDouble) v;
            result.value = (double)this.value + (double)imaginaryDouble.value;
            result.im = (double)this.im + (double) imaginaryDouble.im;
        } else if (v instanceof ImaginaryInt) {
            ImaginaryInt imaginaryInt = (ImaginaryInt) v;
            result.value = (Double)this.value + Double.valueOf(imaginaryInt.value.toString());
            result.im = (Double)this.im + Double.valueOf(imaginaryInt.im.toString());
        } else {
            if (v instanceof MyDouble) {
                MyDouble myDouble = (MyDouble) v;
                result.value = (double)this.value + (double)myDouble.value;
                result.im = this.im;
            } else if (v instanceof Int) {
                Int myInt = (Int) v;
                result.value = (Double)this.value + Double.valueOf(myInt.value.toString());
                result.im = this.im;
            }
        }

        return result;
    }
    @Override
    public String toString() {
        String str = value.toString() + "i" + im.toString();
        return str;
    }

    @Override
    public Value sub(Value v) {
        ImaginaryDouble result = new ImaginaryDouble();

        if (v instanceof ImaginaryDouble) {
            ImaginaryDouble imaginaryDouble = (ImaginaryDouble) v;
            result.value = (double)this.value - (double)imaginaryDouble.value;
            result.im = (double)this.im - (double)imaginaryDouble.im;
        } else if (v instanceof ImaginaryInt) {
            ImaginaryInt imaginaryInt = (ImaginaryInt) v;
            result.value = (Double)this.value - Double.valueOf(imaginaryInt.value.toString());
            result.im = (Double)this.im - Double.valueOf(imaginaryInt.im.toString());
        } else {
            if (v instanceof MyDouble) {
                MyDouble myDouble = (MyDouble) v;
                result.value = (double)this.value - (double)myDouble.value;
                result.im = this.im;
            } else if (v instanceof Int) {
                Int myInt = (Int) v;
                result.value = (Double)this.value - Double.valueOf(myInt.value.toString());
                result.im = this.im;
            }
        }
        return result;
    }
    @Override
    public Value mul(Value v) {
        ImaginaryDouble result = new ImaginaryDouble();

        if (v instanceof ImaginaryDouble) {
            ImaginaryDouble imaginaryDouble = (ImaginaryDouble) v;
            result.value =(double)this.value * (double)imaginaryDouble.value - (double)this.im * (double)imaginaryDouble.im;
            result.im = (double)this.value * (double) imaginaryDouble.im + (double) imaginaryDouble.value * (double)this.im;
        } else if (v instanceof ImaginaryInt) {
            ImaginaryInt imaginaryInt = (ImaginaryInt) v;
            double Value = Double.valueOf(imaginaryInt.value.toString());
            double Im = Double.valueOf(imaginaryInt.im.toString());
            result.value =(Double)this.value * Value - (Double)this.im * Im;
            result.im = (Double)this.value * Im + Value * (Double)this.im;
        } else {
            if (v instanceof MyDouble) {
                MyDouble myDouble = (MyDouble) v;
                result.value = (double)this.value * (double)myDouble.value;
                result.im = this.im;
            } else if (v instanceof Int) {
                Int myInt = (Int) v;
                result.value = (Double)this.value * Double.valueOf(myInt.value.toString());
                result.im = this.im;
            }
        }

        return result;
    }

    @Override
    public Value div(Value v) {
        ImaginaryDouble result = new ImaginaryDouble();

        if (v instanceof ImaginaryDouble) {
            ImaginaryDouble imaginaryDouble = (ImaginaryDouble) v;
            result.value = ((double)this.value * (double)imaginaryDouble.value + (double)this.im * (double)imaginaryDouble.im) /
                    (Math.pow( (double)imaginaryDouble.value, 2) + Math.pow( (double)imaginaryDouble.im, 2));
            result.im = ((double)this.im * (double)imaginaryDouble.value - (double)this.value * (double)imaginaryDouble.im) /
                    (Math.pow( (double)imaginaryDouble.value, 2) + Math.pow( (double)imaginaryDouble.im, 2));
        } else if (v instanceof ImaginaryInt) {
            ImaginaryInt imaginaryInt = (ImaginaryInt) v;
            double Value = Double.valueOf(imaginaryInt.value.toString());
            double Im = Double.valueOf(imaginaryInt.im.toString());
            result.value = ((Double)this.value * Value + (Double)this.im * Im) /
                    (Math.pow( Value, 2) + Math.pow( Im, 2));
            result.im = ((Double)this.im * Value - (Double)this.value * Im) /
                    (Math.pow( Value, 2) + Math.pow( Im, 2));
        } else {
            if (v instanceof MyDouble) {
                MyDouble myDouble = (MyDouble) v;
                result.value = (double)this.value / (double)myDouble.value;
                result.im = this.im;
            } else if (v instanceof Int) {
                Int myInt = (Int) v;
                result.value = (Double)this.value / Double.valueOf(myInt.value.toString());
                result.im = this.im;
            }
        }

        return result;
    }

    @Override
    public boolean eq(Value v) {

        if (v instanceof MyDouble) {
            MyDouble myDouble = (MyDouble) v;
            if((double)this.value == (double)myDouble.value) return false;
        } else if (v instanceof Int) {
            Int myInt = (Int) v;
            if(!Objects.equals((Double) this.value, Double.valueOf(myInt.value.toString()))) return false;
        }

        if (v instanceof ImaginaryDouble) {
            ImaginaryDouble imaginaryDouble = (ImaginaryDouble) v;
            return (double)this.im == (double)imaginaryDouble.im;
        } else if (v instanceof ImaginaryInt) {
            ImaginaryInt imaginaryInt = (ImaginaryInt) v;
            return Objects.equals((Double) this.im, Double.valueOf(imaginaryInt.im.toString()));
        }
        return true;
    }

    @Override
    public boolean lte(Value v){
        throw new ArithmeticException("Inequalities of imaginary numbers are not well defined ");
    }

    @Override
    public boolean gte(Value v){
        throw new ArithmeticException("Inequalities of imaginary numbers are not well defined ");
    }

    @Override
    public boolean neq(Value v) {

        if (v instanceof MyDouble) {
            MyDouble myDouble = (MyDouble) v;
            if((double)this.value == (double)myDouble.value) return false;
        } else if (v instanceof Int) {
            Int myInt = (Int) v;
            if(((Double) this.value).equals(Double.valueOf(myInt.value.toString()))) return false;
        }

        if (v instanceof ImaginaryDouble) {
            ImaginaryDouble imaginaryDouble = (ImaginaryDouble) v;
            if((double)this.im != (double)imaginaryDouble.im || (double)this.value != (double) imaginaryDouble.value) return true;
        } else if (v instanceof ImaginaryInt) {
            ImaginaryInt imaginaryInt = (ImaginaryInt) v;
            double Value = Double.valueOf(imaginaryInt.value.toString());
            double Im = Double.valueOf(imaginaryInt.im.toString());

            if(!Objects.equals((Double) this.im, Im) || !Objects.equals((Double) this.value, Value)) return true;
        }
        return true;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) { // jesli ten sam obiekt
            return true;
        }
        if (other == null || getClass() != other.getClass()) { // jesli inny typ lub null
            return false;
        }
        ImaginaryDouble konwersja = (ImaginaryDouble) other;

        return ( (double)this.value == (double)konwersja.value && (double)this.im == (double)konwersja.im );
    }

    @Override
    public int hashCode() {

        int result = 17;
        result = 31 * result;
        return result;
    }

}
