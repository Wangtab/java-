import org.w3c.dom.ranges.Range;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
/*        int x=0,y=1,z=2;
        String s="x,y,z";
        System.out.println(s+x+y+z);
        System.out.println(x+""+s);
        s+="(summed)=";
        System.out.println(s+(x+y+z));
        System.out.println(""+x);
        if(x>=0&&y<=0)
        System.out.println("Hello World!");*/
/*  int big=Integer.MAX_VALUE;
  System.out.println("big="+big);
  int bigger =big*4;
  System.out.println("bigger="+bigger);*/
      Random rand=new Random();
      float f[]=new float[10];
      for(int i=0;i<10;i++)
        f[i]=rand.nextFloat();
      for(float x:f)
        System.out.println(x);
//      for(char c: "hello".toCharArray())
 System.out.println(test(15,20));

    }

  static int test(int i, int i1) {
      if(i>i1)
        return 1;
      else if(i<i1)
        return  -1;
      else return 0;

  }
}
