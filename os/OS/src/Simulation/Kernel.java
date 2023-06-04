package Simulation;

public class Kernel {
	Mutex userInput;
	Mutex userOutput;
	Mutex file;
	Scheduler scheduler;
	Memory memory;

	public Kernel() {
		userInput = new Mutex(this);
		userOutput = new Mutex(this);
		file = new Mutex(this);
		scheduler = new Scheduler();
		memory = new Memory();
	}

	public void semWait(String mutex, PCB process) {
		boolean block = false;
		if (mutex.equals("userInput")) {
			block = userInput.semWait(process);

		} else if (mutex.equals("userOutput")) {
			block = userOutput.semWait(process);
		} else if (mutex.equals("file")) {
			block = file.semWait(process);
		}
		if (block) {
			blockProcess(process);
			scheduler.block.add(process);
		}
	}

	private void blockProcess(PCB process) {
		process.state = State.BLOCK;

		if (memory.words[memory.firstPCBStart].equals(process.start))
			memory.words[1] = "BLOCK";
		else if (memory.words[memory.secondPCBStart].equals(process.start))
			memory.words[6] = "BLOCK";

	}

	public void semSignal(String mutex, PCB process) {
		PCB unblock = null;
		if (mutex.equals("userInput")) {
			unblock = userInput.semSignal(process.id);

		} else if (mutex.equals("userOutput")) {
			unblock = userOutput.semSignal(process.id);
		} else if (mutex.equals("file")) {
			unblock = file.semSignal(process.id);
		}
		if (unblock != null) {
			scheduler.ready.add(unblock);
			for (int i =0; i<scheduler.block.size(); i++){
				if (scheduler.block.peek().id!= unblock.id)
					scheduler.block.add(scheduler.block.poll());
				else 
					scheduler.block.poll();
			}
			process.state = State.READY;
			unblock(process);

		}
	}

	private void unblock(PCB process) {
		if (memory.words[memory.firstPCBStart].equals(process.start))
			memory.words[1] = "READY";
		else if (memory.words[memory.secondPCBStart].equals(process.start))
			memory.words[6] = "READY";
		
	}
	public void print(String x){
		System.out.println(x);
		
	}
	public void 
}
