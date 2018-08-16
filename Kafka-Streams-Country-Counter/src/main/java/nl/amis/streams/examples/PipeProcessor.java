package nl.amis.streams.examples;

import org.apache.kafka.streams.processor.Processor;
import org.apache.kafka.streams.processor.ProcessorContext;

public class PipeProcessor implements Processor<String, String>{
	
	private ProcessorContext context;

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(ProcessorContext context) {
		// TODO Auto-generated method stub
		this.context = context;
		
	}

	@Override
	public void process(String key, String value) {
		// TODO Auto-generated method stub
		context.forward(key, value);
		System.out.println("Forwarded....." + key + " " + value);
		//throw new RuntimeException("Exception test");
		
	}

	@Override
	public void punctuate(long arg0) {
		// TODO Auto-generated method stub
		
	}

}
