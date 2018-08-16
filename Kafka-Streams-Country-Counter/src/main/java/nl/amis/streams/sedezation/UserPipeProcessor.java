package nl.amis.streams.sedezation;

import org.apache.kafka.streams.processor.Processor;
import org.apache.kafka.streams.processor.ProcessorContext;

public class UserPipeProcessor implements Processor<String, User>{

	ProcessorContext context;
	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(ProcessorContext arg0) {

		context = arg0;
		System.out.println("UserPipeProcessor initialized...");
	}

	@Override
	public void process(String key, User user) {
		System.out.println("received... " + user.toString());
		context.forward(key, user);
	}

	@Override
	public void punctuate(long arg0) {
		// TODO Auto-generated method stub
		
	}

}
