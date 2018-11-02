package pkg;


public class node{
	int x;
	int y;
	double g;
	double h;
	double f;

	node(int x,int y){
		this.x =x;
		this.y =y;
	}

	node(node a){
		this.x = a.x;
		this.y = a.y;
		this.h = a.h;
		this.g = a.g;
		this.f = a.f;
	}

	boolean isequal(node a){
		if(a.x == x && a.y == y){
			return true;
		}else{
			return false;
		}
	}
	boolean isoutofbound(){
		if(this.x <0 || this.y <0 || this.x >=display.boxno||this.y >= display.boxno){
			return true;
		}else{
			return false;
		}
	}
	boolean isat(int x,int y){
		if(this.x == x && this.y == y){
			return true;
		}else{
			return false;
		}	
	}


}