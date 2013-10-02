package sg.books.tij.generics.example1;

public class Coffee {
 private static long counter = 0;
 private final long id = counter++;
 public String toString() {
	 String ret = getClass().getSimpleName() + " " + id;
	 return ret;
 }
}


class Latte extends Coffee {
	
}

class Mocha extends Coffee {
	
}

class Americano extends Coffee {
	
}
