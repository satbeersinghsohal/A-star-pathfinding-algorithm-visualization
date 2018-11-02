package pkg;

import java.util.*;
import java.awt.*;
import pkg.*;

public class algo{
	node start,end,current;
	HashSet<node> openset  = new HashSet<node>();
	HashSet<node> closeset = new HashSet<node>();
	HashMap<node, node> camefrom = new HashMap<>();
	Graphics g;
	int b;
	public algo(node start,node end,Graphics g,int b){
		this.start = start;
		this.end   = end;
		this.g     = g;
		this.b     = b;
		calculatestartandend();
		start();
	}

	public double finddistance(node a,node b){
		// double h = Math.pow(Math.pow(a.x-b.x,2)+Math.pow(a.y-b.y,2) ,0.5);
		double h =Math.abs(a.x-b.x)+Math.abs(a.y-b.y);
		return h*20;
	}


	public void calculatestartandend(){
		start.h = finddistance(start,end);
		start.g = finddistance(start,start);
		start.f = start.h + start.g;
		end.h = 0;
		end.g = 9999;
		end.f = end.h+end.g;
	}
	public void findminneighbor(node a){
	
		for(int i=-1;i<=1;i++){
			for(int j=-1;j<=1;j++){
				int skip = 0;
				// if(i == 0 && j == 0){
				// 	continue;
				// }		
				
				node neighbor = new node(a);
				neighbor.x = a.x + i;
				neighbor.y = a.y + j;
				if(neighbor.isoutofbound()|| display.arr[neighbor.x][neighbor.y] == 1){
					continue;
				}
				Iterator<node>it = closeset.iterator();
				while(it.hasNext()){
					if(it.next().isequal(neighbor)){
						skip = 1;
						break;
					}
				}
				if(skip == 1){
					continue;
				}
				if(i == 0 || j == 0){
					neighbor.g = a.g + 20;
				}else{
					neighbor.g = a.g + 28;
				}
				// neighbor.g = a.g + finddistance(neighbor,a);
				neighbor.h = finddistance(neighbor,end);
				neighbor.f = neighbor.g+neighbor.h;
				it = openset.iterator();
				node min = null;
				while(it.hasNext()){
					node temp = it.next();
					if(temp.isequal(neighbor)){
						if(temp.g > neighbor.g){
							min = temp;
						}
							skip = 1;
					}

				}
				if(min != null){
					camefrom.put(neighbor,a);
					openset.remove(min);
					openset.add(neighbor);
				}
				if(skip == 1){
					continue;
				}
				camefrom.put(neighbor,a);
				openset.add(neighbor);
				drawbox(neighbor,0);

			}
		}
		// System.out.println("choosen:"+min.x+" "+min.y+" "+min.f+" "+min.h );
		// return min;
	}


	public int start(){
		int count = 0;
		openset.add(start);
		current = start;
		node nodesave = null;
		while(!openset.isEmpty()&&count < 4 ){
			if(current.isequal(end)){
				buildpath(current);
				break;
			}

			nodesave = current;
			Iterator<node>it = openset.iterator();
			current = null;
			while(it.hasNext()){
				node temp = it.next();
				if(current == null || current.f > temp.f) {
					current = temp;
				}else if(current.f == temp.f && current.g > temp.g){
					current = temp;
				}
				// System.out.printf("openlist:%d %d %f\n",temp.x,temp.y,temp.f);
			}
			if(current.isequal(nodesave)){
				count++;
			}
			// System.out.println("choosen current:"+current.x+" "+current.y+" "+current.f);

			// current = min;
			
			openset.remove(current);
			closeset.add(current);
			findminneighbor(current);
			drawbox(current,1);
			
			// System.out.println("line109 current:"+current.x+" "+current.y+" "+current.f);
		}

		return 0;
	}
	public void drawbox(node current,int c){
		Color color = Color.RED;
		if(c==1){
			color = Color.GREEN;
		}else if(c ==2){
			color = Color.BLUE;
		}
		// switch(c){
		// 		case 0: color = Color.WHITE;break;
		// 		case 1: color = Color.BLACK;break;
		// 		case 2: color = Color.RED;  break;
		// 		case 3: color = Color.ORANGE;break;
		// 		case 4: color = Color.BLUE;break;
			// }
		int i = current.x;
		int j = current.y;
		// System.out.println("drawbox"+i+" "+j+" "+current.f);
		g.setColor(color);
		g.fillRect(i*b,j*b,b,b);
		g.setColor(Color.BLACK);
		g.drawRect(i*b,j*b,b,b);
		if(display.boxno == 10){
			String s = String.format("%d %d" ,(int)current.g,(int)current.h);
			String s1 = String.format("%d" ,(int)current.f);

			g.drawString(s,i*b+2,j*b+b/3);
			g.drawString(s1,i*b+2,j*b+b*2/3);
		}
		display.arr[i][j] = c;

	}
	void buildpath(node current){
		while(camefrom.containsKey(current)){
			current = camefrom.get(current);
			drawbox(current,2);
		}
	}
}