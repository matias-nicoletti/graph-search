package com.matiasnicoletti.network;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class NetworkTest {

	private static final Integer INVALID_NUMBER_OF_NODES = -1;
	private static final Integer DEFAULT_NUMBER_OF_NODES = 8;

	@Before
	public void setUp() throws Exception {
	}

	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidInitializerInput() {
		Network network = new Network(INVALID_NUMBER_OF_NODES);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConnectInvalidNodeInput() {
		Network network = new Network(DEFAULT_NUMBER_OF_NODES);
		network.connect(10, 2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testQueryInvalidNodeInput() {
		Network network = new Network(DEFAULT_NUMBER_OF_NODES);
		network.connect(10, 2);
	}

	@Test
	public void testConnectBidireccional() {
		Network network = new Network(DEFAULT_NUMBER_OF_NODES);

		// test if a connect really links origin and destination
		network.connect(1, 2);
		assertTrue(network.query(1, 2));
		assertTrue(network.query(2, 1));
		assertFalse(network.query(3, 4));

		network.connect(5, 8);
		assertTrue(network.query(5, 8));
		assertTrue(network.query(8, 5));
		assertFalse(network.query(7, 4));

	}

	@Test
	public void testQueryExampleNetwork() {
		Network network = new Network(DEFAULT_NUMBER_OF_NODES);
		// example network
		network.connect(1, 2);
		network.connect(6, 2);
		network.connect(2, 4);
		network.connect(5, 8);

		assertTrue(network.query(1, 2));
		assertTrue(network.query(6, 2));
		assertTrue(network.query(2, 4));
		assertTrue(network.query(5, 8));
		assertTrue(network.query(1, 4));
		assertTrue(network.query(6, 4));
		assertTrue(network.query(4, 6));
		assertFalse(network.query(5, 3));
		assertFalse(network.query(6, 7));
		assertFalse(network.query(1, 8));
		assertFalse(network.query(5, 4));
	}

	@Test
	public void testQueryFullyConnectedNetwork() {
		Network network = new Network(DEFAULT_NUMBER_OF_NODES);
		// fully connected network
		network.connect(1, 2);
		network.connect(2, 3);
		network.connect(3, 4);
		network.connect(4, 5);
		network.connect(5, 6);
		network.connect(6, 7);
		network.connect(7, 8);

		assertTrue(network.query(1, 2));
		assertTrue(network.query(1, 3));
		assertTrue(network.query(1, 4));
		assertTrue(network.query(1, 5));
		assertTrue(network.query(1, 6));
		assertTrue(network.query(1, 7));
		assertTrue(network.query(1, 8));
		assertTrue(network.query(2, 3));
		assertTrue(network.query(2, 4));
		assertTrue(network.query(2, 5));
		assertTrue(network.query(2, 6));
		assertTrue(network.query(2, 7));
		assertTrue(network.query(2, 8));
		assertTrue(network.query(3, 4));
		assertTrue(network.query(3, 5));
		assertTrue(network.query(3, 6));
		assertTrue(network.query(3, 7));
		assertTrue(network.query(3, 8));
		assertTrue(network.query(4, 5));
		assertTrue(network.query(4, 6));
		assertTrue(network.query(4, 7));
		assertTrue(network.query(4, 8));
		assertTrue(network.query(5, 6));
		assertTrue(network.query(5, 7));
		assertTrue(network.query(5, 8));
		assertTrue(network.query(6, 7));
		assertTrue(network.query(6, 8));
		assertTrue(network.query(7, 8));
	}

}
