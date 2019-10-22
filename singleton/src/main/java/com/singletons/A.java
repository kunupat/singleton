package com.singletons;

public class A {
	private static A obj = new A();

	private A() {}

	public static A getInstance() {
		return obj;
	}

	public void doSomething() {
		System.out.println("Doing something");
	}
}