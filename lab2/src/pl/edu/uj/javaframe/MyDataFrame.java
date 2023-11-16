package pl.edu.uj.javaframe;

public class MyDataFrame extends DataFrame{
    public MyDataFrame(Class<? extends Value>[] types, String[] vals) {
        super(types, vals);
    }

    public void print()
    {
       head();
//        for(int j =0; j < columns.size(); j++) {
//            System.out.print(columns.get(j).name+" ");
//        }
//        System.out.println();
//        for(int j =0; j < columns.get(0).values.size(); j++) {
//            for (int i = 0; i < columns.size(); i++) {
//                columns.get(i).values.get(j).print();
//                System.out.print(' ');
//            }
//            System.out.println();
//        }
    }

}
