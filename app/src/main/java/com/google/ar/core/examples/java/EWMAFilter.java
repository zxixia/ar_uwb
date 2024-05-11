package com.google.ar.core.examples.java;
public class EWMAFilter {
    private double alpha; // 平滑因子
    private Double smoothedValue; // 当前的平滑值，使用Double以处理初始化情况

    public EWMAFilter(double alpha) {
        this.alpha = alpha;
        this.smoothedValue = null; // 初始时没有平滑值
    }

    public double update(double newValue) {
        if (smoothedValue == null) {
            smoothedValue = newValue; // 如果是第一次调用，直接使用第一个值作为平滑值
        } else {
            smoothedValue = alpha * newValue + (1 - alpha) * smoothedValue; // 应用EWMA公式
        }
        return smoothedValue;
    }

    public double getSmoothedValue() {
        if (smoothedValue == null) {
            throw new IllegalStateException("Filter has not been initialized with any data.");
        }
        return smoothedValue;
    }
}