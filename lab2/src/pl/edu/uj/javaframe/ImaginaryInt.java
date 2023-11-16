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
            result.value = (int)this.value + (int)imaginaryDouble.value;
            result.im = (int)this.im + (int)imaginaryDouble.im;
        } else {
            result.value = (int)this.value + (int)v.value;
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
