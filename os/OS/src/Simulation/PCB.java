package Simulation;

public class PCB {
	int id;
	Enum state;
	int pc;
	int start;
	int end;
	public PCB (int id, Enum state, int pc, int min, int max){
		this.id = id;
		this.state= state;
		this.start= min;
		this.end=max;
	}
}
