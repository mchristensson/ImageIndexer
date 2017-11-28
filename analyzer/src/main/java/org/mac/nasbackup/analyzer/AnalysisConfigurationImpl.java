package org.mac.nasbackup.analyzer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.mac.nasbackup.persistance.model.ImageEntry;

public class AnalysisConfigurationImpl implements AnalysisConfiguration {

	private List<String> result;
	private AtomicInteger counter;
	private AtomicInteger success;

	public AnalysisConfigurationImpl() {
		this.result = new ArrayList<>();
		this.counter = new AtomicInteger(0);
	}

	@Override
	public List<String> getAnalysisResult() {
		return result;
	}

	@Override
	public void addException(int result, ImageEntry imageEntry) {
		this.result.add("Status " + result + " on: " + imageEntry.toString());
	}

	@Override
	public void addSuccess() {
		this.success.incrementAndGet();
	}

	@Override
	public void addCount() {
		this.counter.incrementAndGet();
	}

	@Override
	public void count() {
		this.counter.incrementAndGet();
	}

	@Override
	public int getSuccess() {
		return this.success.get();
	}

}