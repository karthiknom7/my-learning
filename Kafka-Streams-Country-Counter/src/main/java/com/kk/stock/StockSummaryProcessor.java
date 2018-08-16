package com.kk.stock;

import java.util.Objects;

import org.apache.kafka.streams.processor.Processor;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.apache.kafka.streams.processor.PunctuationType;
import org.apache.kafka.streams.state.KeyValueIterator;
import org.apache.kafka.streams.state.KeyValueStore;

import com.kk.stock.model.StockTransaction;
import com.kk.stock.model.StockTransactionSummary;

public class StockSummaryProcessor implements Processor<String, StockTransaction>{

	private ProcessorContext context;
	private KeyValueStore<String, StockTransactionSummary> summeryStore;
	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(ProcessorContext context) {
		this.context = context;
		summeryStore = (KeyValueStore<String, StockTransactionSummary>) context.getStateStore("summeryStore");
		Objects.requireNonNull(summeryStore, "Summery store can not be null...");
		this.context.schedule(10000, PunctuationType.WALL_CLOCK_TIME, (timestamo)->{
			KeyValueIterator<String, StockTransactionSummary> storeIterator = summeryStore.all();
			System.out.println("Runing puctoator----"  + timestamo);
			long currentTs = System.currentTimeMillis();
			while(storeIterator.hasNext()){
				StockTransactionSummary summery = storeIterator.next().value;
				//if(summery.updatedWithinLastMillis(currentTs, 11000)){
					this.context.forward(summery.tickerSymbol, summery);
				//}
			}
			this.context.commit();
		});
		
		
	}

	@Override
	public void process(String key, StockTransaction stockTransaction) {
		System.out.println("Processosing record : " + stockTransaction);
		String currentSysmbol = stockTransaction.getSymbol();
		StockTransactionSummary stockTransactionSummary = summeryStore.get(currentSysmbol);
		if(null == stockTransactionSummary){
			stockTransactionSummary = StockTransactionSummary.fromTransaction(stockTransaction);
		}else{
			stockTransactionSummary.update(stockTransaction);
		}
		
		summeryStore.put(currentSysmbol, stockTransactionSummary);
		context.commit();
		
	}

	@Override
	public void punctuate(long arg0) {
		// TODO Auto-generated method stub
		
	}

}
