package com.aquatic.waterflow.error;

/**
 * Custom {@link WaterFlowCalculatorException} for error handling
 */
public class WaterFlowCalculatorException extends Exception {

	private static final long serialVersionUID = -973907333659319284L;

	public WaterFlowCalculatorException(String message) {
		   super(message);
	}
}
