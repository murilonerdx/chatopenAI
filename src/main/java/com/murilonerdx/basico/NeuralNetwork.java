package com.murilonerdx.basico;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NeuralNetwork {
    private List<Layer> layers;
    private double learningRate;

    public NeuralNetwork(int inputSize, int outputSize, double learningRate) {
        this.layers = new ArrayList<>();
        this.learningRate = learningRate;

        // Adicione a camada de entrada
        this.layers.add(new Layer(inputSize));
        // Adicione a camada de saída
        this.layers.add(new Layer(outputSize));
    }

    public void addHiddenLayer(int size) {
        this.layers.add(1, new Layer(size));
    }

    public List<Double> predict(List<Double> input) {
        List<Double> output = input;
        for (int i = 0; i < this.layers.size(); i++) {
            output = this.layers.get(i).forwardPropagate(output);
        }
        return output;
    }

    public void train(List<Double> input, List<Double> target) {
        List<Double> prediction = this.predict(input);
        List<Double> errors = new ArrayList<>();
        for (int i = 0; i < prediction.size(); i++) {
            errors.add(target.get(i) - prediction.get(i));
        }

        for (int i = this.layers.size() - 1; i >= 0; i--) {
            errors = this.layers.get(i).backPropagate(errors, this.learningRate);
        }
    }
}

class Layer {
    private List<Neuron> neurons;

    public Layer(int size) {
        this.neurons = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            this.neurons.add(new Neuron());
        }
    }

    public List<Double> forwardPropagate(List<Double> inputs) {
        List<Double> outputs = new ArrayList<>();
        for (int i = 0; i < this.neurons.size(); i++) {
            outputs.add(this.neurons.get(i).activate(inputs));
        }
        return outputs;
    }

    public List<Double> backPropagate(List<Double> errors, double learningRate) {
        List<Double> newErrors = new ArrayList<>();
        for (int i = 0; i < this.neurons.size(); i++) {
            double error = errors.get(i);
            this.neurons.get(i).applyBackpropagation(error, learningRate);
            newErrors.addAll(this.neurons.get(i).getErrors());
        }
        return newErrors;
    }
}

class Neuron {
    private List<Double> weights;
    private double bias;
    private double output;
    private List<Double> errors;

    public Neuron() {
        this.weights = new ArrayList<>();
        this.bias = new Random().nextDouble();
        this.errors = new ArrayList<>();
    }

    public double activate(List<Double> inputs) {
        double sum = 0;
        for (int i = 0; i < inputs.size(); i++) {
            sum += inputs.get(i) * this.weights.get(i);
        }
        this.output = sigmoid(sum + this.bias);
        return this.output;
    }

    public void applyBackpropagation(double error, double learningRate) {
        this.errors.clear();
        double delta = error * sigmoidDerivative(this.output);
        this.bias -= learningRate * delta;
        for (int i = 0; i < this.weights.size(); i++) {
            double weight = this.weights.get(i);
            this.weights.set(i, weight - learningRate * delta * weight);
            this.errors.add(delta * weight);
        }
    }

    public List<Double> getErrors() {
        return this.errors;
    }

    private double sigmoid(double x) {
        return 1 / (1 + Math.exp(-x));
    }

    private double sigmoidDerivative(double x) {
        return x * (1 - x);
    }
}

