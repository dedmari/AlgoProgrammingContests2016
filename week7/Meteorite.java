package week7;


import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;

public class Meteorite {

    public static void main(String[] args) throws Exception
    {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int cas = Integer.parseInt(br.readLine());

        for (int t = 1; t <= cas; t++) 
        {

            String[] inp = br.readLine().split(" ");
            int xImp = Integer.parseInt(inp[0]);
            int yImpact = Integer.parseInt(inp[1]);
            int n = Integer.parseInt(inp[2]);
            Map<String,Point> map = new HashMap<>();
            String inPnt=null;
            for (int i = 0; i < n; i++)
            {
                String[] side = br.readLine().split(" ");
                int x1 = Integer.parseInt(side[0]);
                int y1 = Integer.parseInt(side[1]);
                int x2 = Integer.parseInt(side[2]);
                int y2 = Integer.parseInt(side[3]);

                String s1 = x1 + "," + y1;
                String s2 = x2 + "," + y2;

                Point p1 = map.get(s1);
                Point p2 = map.get(s2);

                if(p1==null) 
                {
                    p1 = new Point(x1,y1);
                    map.put(s1,p1);
                }

                if(p2==null)
                {
                    p2= new Point(x2,y2);
                    map.put(s2,p2);
                }

                p1.adj.add(p2);
                p2.adj.add(p1);

                if(inPnt==null)
                    inPnt = s1;
            }

            Polygon poly = new Polygon();

            Stack<Point> stack = new Stack<>();
            
            stack.add(map.get(inPnt));

            while(!stack.isEmpty())
            {
                Point p = stack.pop();
                
                p.isVis=true;
                
                poly.addPoint(p.x,p.y);

                for(Point pt:p.adj)
                {
                    if(!pt.isVis && !stack.contains(pt))
                        stack.add(pt);
                }
            }


            if (poly.contains(xImp,yImpact))
            	System.out.println("Case #"+t+": "+"jackpot");
            else
            	System.out.println("Case #"+t+": "+"too bad");
            
                br.readLine();
        }
    }

}
class Point 
{
    int x;
    int y;
    boolean isVis=false;
    List<Point> adj;

    Point(int x, int y)
    {
        this.x=x;
        this.y=y;
        adj = new ArrayList<>();
    }
}

