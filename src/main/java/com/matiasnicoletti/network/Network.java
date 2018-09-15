package com.matiasnicoletti.network;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;

public class Network {

	private static final String INVALID_INITIALIZER_INPUT = "The number %s is not a valid initializer for this network.";
	private static final String INVALID_NODE_INPUT = "The number %s is not a valid node index.";

	private HashMap<Integer, HashSet<Integer>> adjacencyList;
	private Integer numberOfEdges;

	/**
	 * Initializes the Network using the input number of elements. Throws an
	 * IllegalArgumentException if the input is invalid.
	 * 
	 * @param numberOfElements
	 */
	public Network(Integer numberOfElements) {
		// validate input
		validatePositive(numberOfElements);

		// Initialize network
		adjacencyList = new HashMap<>();
		numberOfEdges = 0;
		for (Integer i = 1; i <= numberOfElements; i++) {
			adjacencyList.put(i, new HashSet<>());
		}
	}

	/**
	 * Creates an edge/connection between origin and destination. Throws an
	 * IllegalArgumentException if the input is invalid
	 * 
	 * @param origin
	 * @param destination
	 */
	public void connect(Integer origin, Integer destination) {
		// validate input
		validateNode(origin);
		validateNode(destination);

		// make connections
		adjacencyList.get(origin).add(destination);
		adjacencyList.get(destination).add(origin);
		numberOfEdges++;
	}

	/**
	 * Tries to search a path between origin and destination. Throws an
	 * IllegalArgumentException if the input is invalid
	 * 
	 * @param origin
	 * @param destination
	 * @return
	 */
	public Boolean query(Integer origin, Integer destination) {
		// validate input
		validateNode(origin);
		validateNode(destination);

		// perform a BFS search
		LinkedList<Integer> queue = new LinkedList<>();
		HashSet<Integer> visited = new HashSet<>();
		queue.add(origin);
		while (!queue.isEmpty()) {
			Integer current = queue.poll();
			// if found destination, return true
			if (destination.equals(current)) {
				return true;
			}
			// explore those adjacent nodes that have not been already visited
			HashSet<Integer> adjacents = adjacencyList.get(current);
			adjacents.stream().filter(adjacent -> !visited.contains(adjacent)).forEach(adjacent -> {
				visited.add(adjacent);
				queue.add(adjacent);
			});
		}
		return false;
	}

	private void validatePositive(Integer value) {
		if (Objects.isNull(value) || value <= 0) {
			throw new IllegalArgumentException(String.format(INVALID_INITIALIZER_INPUT, value));
		}
	}

	private void validateNode(Integer index) {
		if (Objects.isNull(index) || !adjacencyList.containsKey(index)) {
			throw new IllegalArgumentException(String.format(INVALID_NODE_INPUT, index));
		}
	}

	public Integer getNumberOfEdges() {
		return numberOfEdges;
	}

	public Integer getNumberOfNodes() {
		return adjacencyList.size();
	}

}
