package nl.amis.streams.examples;

import org.apache.kafka.streams.processor.Processor;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.apache.kafka.streams.state.KeyValueStore;

public class AppendProcessor implements Processor<String, String> {

	private ProcessorContext context;
	private KeyValueStore<String, String> state;

	@Override
	public void close() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(ProcessorContext context) {

		System.out.println("Init call");
		this.context = context;
		state = (KeyValueStore<String, String>) context.getStateStore("append-store");
	}

	@Override
	public void process(String key, String value) {
		System.out.println("Triggered append process..." + key + " " + value);
		String storedVale = value;
		if (null != key) {
			storedVale = state.get(key);
			if (null == storedVale) {
				storedVale = value;
			} else {
				storedVale += "-" + value;
			}
			state.put(key, storedVale);
		}
		context.forward(key, storedVale);
	}

	@Override
	public void punctuate(long arg0) {
		// TODO Auto-generated method stub

	}

}
