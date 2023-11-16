package pl.edu.uj.javaframe;

public class ImaginaryInt extends Int {
    protected Object im;

    @Override
    public Value create(String val) {
        ImaginaryInt result = new ImaginaryInt();
        if (val.contains("i")) {
            String[] parts = val.split("i");
            result.value = Integer.parseInt(parts[0]);
            result.im = Integer.parseInt(parts[1]);

        } else {
            result.value = Integer.parseInt(val);
        }
        return result;
    }

    @Override
    public Value add(Value v) {
        ImaginaryInt result = new ImaginaryInt();

        if (v instanceof ImaginaryInt) {
            ImaginaryInt imaginaryInt = (ImaginaryInt) v;
            result.value = (int)this.value + (int)imaginaryInt.value;
            result.im = (int)this.im + (int)imaginaryInt.im;
        } else if (v instanceof ImaginaryDouble) {
            ImaginaryDouble imaginaryDouble = (ImaginaryDouble) v;
            result.value = (Integer)this.value + Double.valueOf(imaginaryDouble.value.toString()).intValue();
            result.im = (Integer)this.im + Double.valueOf(imaginaryDouble.im.toString()).intValue();
        } else {
            if (v instanceof MyDouble) {
                MyDouble myDouble = (MyDouble) v;
                result.value = (Integer)this.value + Double.valueOf(myDouble.value.toString()).intValue();
                result.im = this.im;
            } else if (v instanceof Int) {
                Int myInt = (Int) v;
                result.value = (int)this.value + (int)myInt.value;
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
        ImaginaryInt result = new ImaginaryInt();

        if (v instanceof ImaginaryInt) {
            ImaginaryInt imaginaryInt = (ImaginaryInt) v;
            result.value = (int)this.value - (int)imaginaryInt.value;
            result.im = (int)this.im - (int)imaginaryInt.im;
        } else if (v instanceof ImaginaryDouble) {
            ImaginaryDouble imaginaryDouble = (ImaginaryDouble) v;
            result.value = (Integer)this.value - Double.valueOf(imaginaryDouble.value.toString()).intValue();
            result.im = (Integer)this.im - Double.valueOf(imaginaryDouble.im.toString()).intValue();
        } else {
            if (v instanceof MyDouble) {
                MyDouble myDouble = (MyDouble) v;
                result.value = (Integer)this.value - Double.valueOf(myDouble.value.toString()).intValue();
                result.im = this.im;
            } else if (v instanceof Int) {
                Int myInt = (Int) v;
                result.value = (int)this.value - (int)myInt.value;
                result.im = this.im;
            }
        }

        return result;
    }
    @Override
    public Value mul(Value v) {
        ImaginaryInt result = new ImaginaryInt();

        if (v instanceof ImaginaryInt) {
            ImaginaryInt imaginaryInt = (ImaginaryInt) v;
            result.value =(int)this.value * (int)imaginaryInt.value - (int)this.im * (int)imaginaryInt.im;
            result.im = (int)this.value * (int)imaginaryInt.im + (int)imaginaryInt.value * (int)this.im;
        } else if (v instanceof ImaginaryDouble) {
            ImaginaryDouble imaginaryDouble = (ImaginaryDouble) v;
            int Value = Double.valueOf(imaginaryDouble.value.toString()).intValue();
            int Im = Double.valueOf(imaginaryDouble.im.toString()).intValue();

            result.value =(Integer)this.value * Value - (Integer)this.im * Im;
            result.im = (Integer)this.value * Im + Value * (Integer)this.im;
        } else {
            if (v instanceof MyDouble) {
                MyDouble myDouble = (MyDouble) v;
                result.value = (Integer)this.value * Double.valueOf(myDouble.value.toString()).intValue();
                result.im = this.im;
            } else if (v instanceof Int) {
                Int myInt = (Int) v;
                result.value = (int)this.value * (int)myInt.value;
                result.im = this.im;
            }
        }

        return result;
    }

    @Override
    public Value div(Value v) {
        ImaginaryInt result = new ImaginaryInt();

        if (v instanceof ImaginaryInt) {
            ImaginaryInt imaginaryInt = (ImaginaryInt) v;
            result.value = (int) (((int)this.value * (int)imaginaryInt.value + (int)this.im * (int)imaginaryInt.im) /
                    (Math.pow( (int)imaginaryInt.value, 2) + Math.pow( (int)imaginaryInt.im, 2)));
            result.im = (int) ((((int) this.im * (int) imaginaryInt.value) - ((int) this.value * (int) imaginaryInt.im)) /
                    (Math.pow( (int)imaginaryInt.value, 2) + Math.pow( (int)imaginaryInt.im, 2)));
        } else if (v instanceof ImaginaryDouble) {
            ImaginaryDouble imaginaryDouble = (ImaginaryDouble) v;
            int Value = Double.valueOf(imaginaryDouble.value.toString()).intValue();
            int Im = Double.valueOf(imaginaryDouble.im.toString()).intValue();

            result.value = (int)(((Integer)this.value * Value + (Integer)this.im * Im) /
                    (Math.pow( Value, 2) + Math.pow( Im, 2)));
            result.im = (int)(((Integer)this.im * Value - (Integer)this.value * Im) /
                    (Math.pow( Value, 2) + Math.pow( Im, 2)));
        } else {
            if (v instanceof MyDouble) {
                MyDouble myDouble = (MyDouble) v;
                result.value = (Integer)this.value / Double.valueOf(myDouble.value.toString()).intValue();
                result.im = this.im;
            } else if (v instanceof Int) {
                Int myInt = (Int) v;
                result.value = (int)this.value / (int)myInt.value;
                result.im = this.im;
            }
        }

        return result;
    }

    @Override
    public boolean eq(Value v) {

        if (v instanceof MyDouble) {
            MyDouble myDouble = (MyDouble) v;
            if((Integer)this.value != Double.valueOf(myDouble.value.toString()).intValue()) return false;
        } else if (v instanceof Int) {
            Int myInt = (Int) v;
            if((int)this.value != (int)myInt.value) return false;
        }

        if (v instanceof ImaginaryInt) {
            ImaginaryInt imaginaryInt = (ImaginaryInt) v;
            return (int)this.im == (int)imaginaryInt.im;
        } else if (v instanceof ImaginaryDouble) {
            ImaginaryDouble imaginaryDouble = (ImaginaryDouble) v;
            return (Integer)this.im == Double.valueOf(imaginaryDouble.im.toString()).intValue();
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
            if((Integer)this.value == Double.valueOf(myDouble.value.toString()).intValue()) return false;
        } else if (v instanceof Int) {
            Int myInt = (Int) v;
            if((int)this.value == (int)myInt.value) return false;
        }

        if (v instanceof ImaginaryInt) {
            ImaginaryInt imaginaryInt = (ImaginaryInt) v;
            if((int)this.im != (int)imaginaryInt.im || (int)this.value != (int)imaginaryInt.value) return true;
        } else if (v instanceof ImaginaryDouble) {
            ImaginaryDouble imaginaryDouble = (ImaginaryDouble) v;
            int Value = Double.valueOf(imaginaryDouble.value.toString()).intValue();
            int Im = Double.valueOf(imaginaryDouble.im.toString()).intValue();

            if((Integer) this.im != Im || (Integer) this.value != Value) return true;
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
        ImaginaryInt konwersja = (ImaginaryInt) other;

        return ( (int)this.value == (int)konwersja.value && (int)this.im == (int)konwersja.im );
    }

    @Override
    public int hashCode() {

        int result = 7;
        result = 31 * result;
        return result;
    }

}
