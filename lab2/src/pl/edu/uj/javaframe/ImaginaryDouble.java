package pl.edu.uj.javaframe;

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
            result.im = (double)this.im + (double)imaginaryDouble.im;
        } else if (v instanceof ImaginaryInt) {
            ImaginaryInt imaginaryInt = (ImaginaryInt) v;
            result.value = (double)this.value + (double)imaginaryInt.value;
            result.im = (double)this.im + (double)imaginaryInt.im;
        } else {
            result.value = (double)this.value + (double)v.value;
            result.im = this.im;
        }

        return result;
    }

    @Override
    public String toString() {
        String str = value.toString() + "i" + im.toString();
        return str;
    }

}
