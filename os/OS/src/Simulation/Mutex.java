package Simulation;

import java.util.LinkedList;
import java.util.Queue;

public class Mutex {
	Queue <PCB>blockedQueue = new LinkedList<>();
	Integer currProcessId;
	Enum value;
	Kernel kernel;
	public Mutex(Kernel kernel){
		this.kernel= kernel;
	}
	public PCB semSignal(int processId){
		PCB process = null;
		if (processId == this.currProcessId){
			
		
		if (!(this.blockedQueue.isEmpty())){
			this.currProcessId = processId;
			process = blockedQueue.poll();//lw rg3t null mhdsh bist3ml resources
		}
		else
			this.currProcessId =  null;
		}
		return process;
	}
	
	public boolean semWait(PCB process){
		if(this.currProcessId==null){
			this.currProcessId= process.id;
		//No blocked processes
		return false;
		}
		else{
			this.blockedQueue.add(process);
			return true;
		}}
	
	
	}
	

