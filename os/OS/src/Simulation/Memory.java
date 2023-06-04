package Simulation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class Memory {
	static String[] words= new String[40];
	Memory memory;
   Integer firstPCBStart= 0;
	 Integer secondPCBStart = 5;
	 Integer firstProcess =10;
	 Integer secondProcess =25;
	 //reserve last 3 cells of each process at the end of each processes cells 
	 
	public  Memory(){
		}
	public int unloadPCB() throws IOException{
		int index =-1;
		
		if(!this.words[firstPCBStart+1].equals("RUNNING") )
			 index = 0;
		else 
			 index = 5;
		
		String fileName = "src/"+"PCB"+ words[index] +".txt";
		File pcbFile = new File(fileName);
		if (!pcbFile.exists()){
			pcbFile.createNewFile();
		}
		FileWriter writer = new FileWriter(pcbFile);
		for (int i = index; i< index +5; i++){
			writer.write(words[i]);
			writer.write("\n");
		}
		
		unloadProcess(index+3, index+4);
		writer.close();
		return index;
		}
	private void unloadProcess(int i, int j) throws IOException {
		String fileName = "src/"+"PCB"+ words[i] +".txt";
		File pcbFile = new File(fileName);
		if (!pcbFile.exists()){
			pcbFile.createNewFile();
		}
		FileWriter writer = new FileWriter(pcbFile);
		for (int k=i; i< j ;k++){
			writer.write(words[k]);
			writer.write("\n");
		}
		
		
	}
	public void loadPCB(PCB pcb) throws IOException{
		if(this.words[firstPCBStart].equals( ""+pcb.id)|| this.words[secondPCBStart].equals(pcb.id+""))
			return;
	int index;
	if (this.words[firstPCBStart]== null)
		index = 0;
	else if(this.words[secondPCBStart]== null)
		index = 5;
	else
		index =unloadPCB();
	String fileName = "src/"+"PCB"+ words[index] +".txt";
	
	
	BufferedReader reader = new BufferedReader(new FileReader(fileName));
	String line;
	
	while((line= reader.readLine())!=null&& index< index+5){
	
		words[index]= line;
		index++;
	}
	reader.close();
	
	loadProcess(pcb.id,pcb.start, pcb.end);
	}
	
	

	@Override
	public String toString() {
		return "Memory [words=" + Arrays.toString(words) + "]";
	}
	
	
	private void loadProcess(int id, int start, int end) throws IOException {
		String fileName = "src/"+"PCB"+id +".txt";
		
	
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		String line;
		
		while((line= reader.readLine())!=null){
		
			words[start]= line;
			
		}
		reader.close();
		
	}
	
}
