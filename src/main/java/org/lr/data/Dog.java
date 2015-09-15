package org.lr.data;

public final class Dog implements Animal {

    private final String name;
    private final int age;

    public Dog(final String name, final int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public int age() {
        return age;
    }

    @Override
    public void eat() {
        System.out.println("dog eats meat");
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
