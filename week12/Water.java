package week12;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;


public class Water {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for (int i = 1; i <= t; i++) {
			String[] inp1 = br.readLine().split(" ");
			int n = Integer.parseInt(inp1[0]);
			Room[] rooms = new Room[n + 1];
			int m = Integer.parseInt(inp1[1]);
			int k = Integer.parseInt(inp1[2]);
			int l = Integer.parseInt(inp1[3]);
			for (int j = 1; j <= n; j++){
				rooms[j] = new Room(j);
			}

			for (int j = 0; j < m; j++) {
				String[] inp2 = br.readLine().split(" ");
				int ai = Integer.parseInt(inp2[0]);
				int bi = Integer.parseInt(inp2[1]);
				if (ai == bi){//if both same than dont check for anything
					continue;
				}
				Room rm1 = rooms[ai];
				Room rm2 = rooms[bi];
				int li = Integer.parseInt(inp2[2]);
				if (!rm1.connection.containsKey(rm2) || rm1.connection.get(rm2) < li) {
					rm1.connection.put(rm2, li);
					rm2.connection.put(rm1, li);
				}
			}
			for (int j = 0; j < k; j++) {
				String[] inp2 = br.readLine().split(" ");
				int ci = Integer.parseInt(inp2[0]);
				int di = Integer.parseInt(inp2[1]);
				rooms[ci].level = di;
			}	
			CalcWaterLevel waterLevel = new CalcWaterLevel();
			waterLevel.calcResult(rooms,l,n,i);
			br.readLine();
		}
	}
}
class Room {
	int id;
	boolean visited;
	int level = -1;
	int check = 0;
	HashMap<Room, Integer> connection = new HashMap<Room, Integer>();
	public Room(int id) {
		this.id = id;
	}
}

class CalcWaterLevel {
		public void calcResult(Room[] rooms,int l,int n,int i){
			int visitCounter = 0;
			LinkedList<Room> ll = new LinkedList<Room>();
			HashMap<Room, Integer> roomVals = new HashMap<Room, Integer>();
			int tempLevel = l;		
			if (rooms[1].level != -1  && rooms[1].level < tempLevel){
				tempLevel = rooms[1].level;
			}
			int prevRoom = Integer.MIN_VALUE;
			 while (true) {
				int curRoom = 1;
				ll.add(rooms[1]);
				visitCounter++;
				rooms[1].check = visitCounter;

				int longValue = -1;
				for (Map.Entry<Room, Integer> val : roomVals.entrySet()) {

					if (val.getValue() > longValue){
						longValue = val.getValue();
					}
				}
				if (longValue != -1) {
					if (longValue < tempLevel) {
						prevRoom = 0;
						break;
					}
					l = longValue;
					roomVals.clear();
				}
				while (!ll.isEmpty()) {
					Room room = ll.remove();
					for (Map.Entry<Room, Integer> connections :room.connection.entrySet()) {
						Room neibourRoom = connections.getKey();
						int neibourRoomVal = connections.getValue();			
						if (neibourRoom.check  < visitCounter) {
							if (neibourRoomVal >= l) {
								ll.add(neibourRoom);
								if (neibourRoom.level != -1 && neibourRoom.level < tempLevel){
									tempLevel = neibourRoom.level;
								}
								curRoom++;
								neibourRoom.check = visitCounter;
								roomVals.remove(neibourRoom); 
							} else {
								if (!roomVals.containsKey(neibourRoom) || roomVals.get(neibourRoom) < neibourRoomVal) {
									roomVals.put(neibourRoom, 
											neibourRoomVal);
								}
							}
						}
					}
				}
				if (curRoom <= prevRoom){
					break;
				}
				prevRoom = curRoom;
				if (curRoom >= n){
					break;
				}
			}
			if (prevRoom >= n)
				System.out.println("Case #" + i + ": " + l);
			else
				System.out.println("Case #" + i + ": impossible");
		}
}