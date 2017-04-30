package week8;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubwayStations{
	
	public static boolean DoLinesIntersect(Line2D L1, Line2D L2, Point2D ptIntersection)
    {
       // Denominator for ua and ub are the same, so store this calculation
       double d =
          (L2.getY2() - L2.getY1()) * (L1.getX2() - L1.getX1())
          -
          (L2.getX2() - L2.getX1()) * (L1.getY2() - L1.getY1());

       //n_a and n_b are calculated as seperate values for readability
       double n_a =
          (L2.getX2() - L2.getX1()) * (L1.getY1() - L2.getY1())
          -
          (L2.getY2() - L2.getY1()) * (L1.getX1() - L2.getX1());

       double n_b =
          (L1.getX2() - L1.getX1()) * (L1.getY1() - L2.getY1())
          -
          (L1.getY2() - L1.getY1()) * (L1.getX1() - L2.getX1());
       if (d == 0)
          return false;
       double ua = n_a / d;
       double ub = n_b / d;
       if (ua >= 0.0d && ua<=1.0d && ub >= 0.0d && ub<=1.0d)
       {
          double x = L1.getX1() + (ua * (L1.getX2() - L1.getX1()));
          double y = L1.getY1() + (ua * (L1.getY2() - L1.getY1()));
          ptIntersection.setLocation(x, y);
          return true;
       }
       return false;
    }
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc++) {
        	String[] inp1=br.readLine().split(" ");
        	int n=Integer.parseInt(inp1[0]);//number of subways
        	double startX = Double.parseDouble(inp1[1]);
        	double startY = Double.parseDouble(inp1[2]);
        	double endX = Double.parseDouble(inp1[3]);
        	double endY = Double.parseDouble(inp1[4]);
        	int nodenum=0;
        	List<LineInfo>[] connectionsInfo = new ArrayList[n];
        	//System.out.println(" "+startX+" "+startY+" "+endX+ " "+endY);
        	for(int i=0;i<n;i++){
        		connectionsInfo[i] = new ArrayList<LineInfo>();
        		String[] inp2 = br.readLine().split(" ");
        		int m = Integer.parseInt(inp2[0]);
        		int coordnum=0;
        		for(int j=0;j<m-1;j++){
        			//System.out.println("\n coornum="+coordnum+" "+j+" "+i);
        			double x1 = Double.parseDouble(inp2[coordnum+1]);
        			double y1 = Double.parseDouble(inp2[coordnum+2]);
        			double x2 = Double.parseDouble(inp2[coordnum+3]);
        			double y2 = Double.parseDouble(inp2[coordnum+4]);
        			LineInfo connection = new LineInfo();
        			connection.line= new Line2D.Double(x1, y1, x2, y2);// used for finding intersections
        			coordnum+=2;
        			connection.subwayNo=i;
        			connection.lineLength= Math.sqrt(((x1-x2)*(x1-x2)) + ((y1-y2)*(y1-y2)));//distance between two points forming the connection
        			connectionsInfo[i].add(connection);
        			//System.out.println("\n coornum="+coordnum);
        			connection.nodeNum = nodenum;// node only one because there is always connection between nodenum->nudenum+1
        			nodenum++;
        			//System.out.println("distance between points"+ x1+" " +y1 + " " + x2 +" "+y2+" = "+connection.lineLength);
        		}
        		nodenum++;
        	}
        	//System.out.println("nodenum="+nodenum);
//        	for(int i=0;i<n;i++){
//        		for (LineInfo temp : connectionsInfo[i]) {
//        			System.out.println(temp.subwayNo+" "+temp.lineLength+" "+temp.nodeNum+" "+temp.line.getP1()+ " "+temp.line.getP2());
//        		}
//        	}
//        	
//        	System.out.println();
//        	System.out.println();
//        	System.out.println();
        	Map<String,Integer> coordToNode = new HashMap<String,Integer>();
        	Map<Integer,ArrayList<LinesIntersection>> nodeIntersectionInfo = new HashMap<Integer,ArrayList<LinesIntersection>>();
        	int numIntersection=0;
        	//List<IntersectionInfo> intersectInfos = new ArrayList<IntersectionInfo>();
        	for(int i=0;i<n-1;i++){
        		for (LineInfo outerSubway  : connectionsInfo[i]) {
        			for(int j=i+1;j<n;j++){
        				for (LineInfo innerSubway  : connectionsInfo[j]) {
        					Point2D ptIntersection =  new Point2D.Double();
        					//System.out.println("lines: "+outerSubway.line.getP1()+" "+outerSubway.line.getP2()+"    "+innerSubway.line.getP1()+" "+innerSubway.line.getP2());
        					boolean isIntersects = DoLinesIntersect(innerSubway.line,outerSubway.line, ptIntersection);
        					if(isIntersects){
        						//ArrayList<LinesIntersection> intersectionNode = null ;//= new ArrayList<LinesIntersection>();
        						String coordValX = Double.toString(ptIntersection.getX());
            					String coordValY = Double.toString(ptIntersection.getY());
            					Integer nodeValue = coordToNode.get(coordValX+coordValY);
        						if(nodeValue!=null){//intersection node already exists
        							LinesIntersection intersectNode3 = new LinesIntersection();
        							intersectNode3.nodeNum = innerSubway.nodeNum;
        							intersectNode3.subwayNo = innerSubway.subwayNo;
        							intersectNode3.lineLength = Math.sqrt(((innerSubway.line.getX1()-ptIntersection.getX())*(innerSubway.line.getX1()-ptIntersection.getX())) 
            								+ ((innerSubway.line.getY1()-ptIntersection.getY())*(innerSubway.line.getY1()-ptIntersection.getY())));
        							
        							LinesIntersection intersectNode4 = new LinesIntersection();
        							intersectNode4.nodeNum = innerSubway.nodeNum;
        							intersectNode4.subwayNo = innerSubway.subwayNo;
        							intersectNode4.lineLength = Math.sqrt(((innerSubway.line.getX2()-ptIntersection.getX())*(outerSubway.line.getX2()-ptIntersection.getX())) 
            								+ ((innerSubway.line.getY2()-ptIntersection.getY())*(innerSubway.line.getY2()-ptIntersection.getY())));
//        							intersectionNode.add(intersectNode3);
//        							intersectionNode.add(intersectNode4);
        							
        							List<LinesIntersection> intersectInfos = nodeIntersectionInfo.get(nodeValue);
        							intersectInfos.add(intersectNode3);
        							intersectInfos.add(intersectNode4);
        							//than add 2 nodes from innerSubway line as nodes of first line will be already there
        						}
        						else{
        							ArrayList<LinesIntersection> intersectionNode = new ArrayList<LinesIntersection>();
        							coordToNode.put(coordValX+coordValY, numIntersection);// changing name from coordinate system to just number for using in dijkstra's algo
        							LinesIntersection intersectNode1 = new LinesIntersection();
        							intersectNode1.nodeNum = outerSubway.nodeNum;
        							intersectNode1.subwayNo = outerSubway.subwayNo;
        							intersectNode1.lineLength = Math.sqrt(((outerSubway.line.getX1()-ptIntersection.getX())*(outerSubway.line.getX1()-ptIntersection.getX())) 
            								+ ((outerSubway.line.getY1()-ptIntersection.getY())*(outerSubway.line.getY1()-ptIntersection.getY())));
        							
        							LinesIntersection intersectNode2 = new LinesIntersection();
        							intersectNode2.nodeNum = outerSubway.nodeNum;
        							intersectNode2.subwayNo = outerSubway.subwayNo;
        							intersectNode2.lineLength = Math.sqrt(((outerSubway.line.getX2()-ptIntersection.getX())*(outerSubway.line.getX2()-ptIntersection.getX())) 
            								+ ((outerSubway.line.getY2()-ptIntersection.getY())*(outerSubway.line.getY2()-ptIntersection.getY())));
        							
        							LinesIntersection intersectNode3 = new LinesIntersection();
        							intersectNode3.nodeNum = innerSubway.nodeNum;
        							intersectNode3.subwayNo = innerSubway.subwayNo;
        							intersectNode3.lineLength = Math.sqrt(((innerSubway.line.getX1()-ptIntersection.getX())*(innerSubway.line.getX1()-ptIntersection.getX())) 
            								+ ((innerSubway.line.getY1()-ptIntersection.getY())*(innerSubway.line.getY1()-ptIntersection.getY())));
        							
        							LinesIntersection intersectNode4 = new LinesIntersection();
        							intersectNode4.nodeNum = innerSubway.nodeNum;
        							intersectNode4.subwayNo = innerSubway.subwayNo;
        							intersectNode4.lineLength = Math.sqrt(((innerSubway.line.getX2()-ptIntersection.getX())*(outerSubway.line.getX2()-ptIntersection.getX())) 
            								+ ((innerSubway.line.getY2()-ptIntersection.getY())*(innerSubway.line.getY2()-ptIntersection.getY())));
        							
        							
        							intersectionNode.add(intersectNode1);
        							intersectionNode.add(intersectNode2);
        							intersectionNode.add(intersectNode3);
        							intersectionNode.add(intersectNode4);
        							
        							nodeIntersectionInfo.put(numIntersection,intersectionNode);
        							numIntersection++;
        							// than create new node for intersection and add four other nodes from 2 intersection lines
        						}
//        						IntersectionInfo intInfo = new IntersectionInfo();
//        						intInfo.intersectionNode1 = outerSubway.nodeNum;
//        						intInfo.intersectionNode2 = outerSubway.nodeNum+1;
//        						intInfo.intersectionNode3 = innerSubway.nodeNum;
//        						intInfo.intersectionNode4 = innerSubway.nodeNum+1;
//        						intInfo.subway1 = outerSubway.subwayNo;
//        						intInfo.subway2 = innerSubway.subwayNo;
//        						intInfo.lineLength1 = Math.sqrt(((outerSubway.line.getX1()-ptIntersection.getX())*(outerSubway.line.getX1()-ptIntersection.getX())) 
//        								+ ((outerSubway.line.getY1()-ptIntersection.getY())*(outerSubway.line.getY1()-ptIntersection.getY()))); // distance between one point of outerSubway line to intersection point
//        						intInfo.lineLength2 = Math.sqrt(((outerSubway.line.getX2()-ptIntersection.getX())*(outerSubway.line.getX2()-ptIntersection.getX())) 
//        								+ ((outerSubway.line.getY2()-ptIntersection.getY())*(outerSubway.line.getY2()-ptIntersection.getY())));
//        						intInfo.lineLength3 = Math.sqrt(((innerSubway.line.getX1()-ptIntersection.getX())*(innerSubway.line.getX1()-ptIntersection.getX())) 
//        								+ ((outerSubway.line.getY2()-ptIntersection.getY())*(outerSubway.line.getY2()-ptIntersection.getY())));
//        						intInfo.lineLength4 = Math.sqrt(((innerSubway.line.getX2()-ptIntersection.getX())*(innerSubway.line.getX2()-ptIntersection.getX())) 
//        								+ ((innerSubway.line.getY2()-ptIntersection.getY())*(innerSubway.line.getY2()-ptIntersection.getY())));
//        						intersectInfos.add(intInfo); //check for subways connecting ---- PENDING (LOOK FOR SPECIAL CASE WHEN POINT IS COMMON AMONG TWO LINE)
        						//TODO:check for case when two lines have same point and that will act as intersection point
        						//numIntersection++;
//        						System.out.println(ptIntersection);
        					}
        				}
        			}
        		}
        	}
        	
        	
        	List<Node>[][] nodesMatrix = new ArrayList[n+numIntersection][n+numIntersection];
        	for(int i=0;i<n;i++){
        		for(LineInfo nodeDetails  : connectionsInfo[i]){
        			
        		}
        	}
        	
        	System.out.println("number of intersections="+numIntersection);
        }
	}
}

interface Node{
	
}
class LineInfo implements Node{
	Line2D line;
	int nodeNum;
	double lineLength;//acts as distance between two nodes
	int subwayNo;//used to identify subways
	boolean intersectionLine = false;
	int intersectionNode1;
	int intersectionNode2;
	
}
class IntersectionInfo implements Node{
	int subwayNo;
	int intersectionNode1;// one point of first line
	int intersectionNode2;// second point of first line
	int intersectionNode3;// one point of second line
	int intersectionNode4;//  second point of second line
	double lineLength1;//length of line from the first point to intersection point.
	double lineLength2; // length of line from the second point to intersection point
	double lineLength3; // same for line 2
	double lineLength4;
	int subway1; //subway number of line1
	int subway2;// subway number of line2
	
}
class LinesIntersection implements Node{
	int nodeNum;
	double lineLength;
	int subwayNo;
	// TODO: identifying subways that this intersection is associated with
	
}