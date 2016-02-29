package com.aquatic.waterflow.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RiverCrossSectionResults {

	private double totalArea;
	private double totalVelocty;
	private double totalFlow;
	
	@Override
	public String toString() {
		return "RiverCrossSectionResults [totalArea=" + totalArea
				+ ", totalVelocty=" + totalVelocty 
				+ ", totalFlow=" + totalFlow + "]";
	}
}
