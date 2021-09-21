
public class Aufgabe {
	
	public static long t;
	
	public static int summe(int[] zahlen) {
		int summe = 0;
		t  = System.currentTimeMillis();
		for (int i : zahlen) {
			summe += i;
		}
		System.out.println(System.currentTimeMillis() - t);
		return summe;
	}
	
	
	public static void main(String[] args) {
		int[] zahlen = new int[20997152];
		for(int i=0; i < zahlen.length; i++) {
			zahlen[i] = i+1;
		}
		System.out.println(summe(zahlen));
		
	}
}
