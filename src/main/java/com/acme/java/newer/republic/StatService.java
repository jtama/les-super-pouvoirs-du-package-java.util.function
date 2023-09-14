package com.acme.java.newer.republic;

import java.util.List;
import java.util.function.Predicate;

public class StatService {

	private final Predicate<Beverage> isBio = b -> b.comesFromFsc() && b.isFairTrade();
	private final Predicate<Beverage> containsCaffeine = b -> !b.containsCaffeine();
	private final Predicate<Beverage> isBioAndCaffeineFree = isBio.and(containsCaffeine.negate());

	public void teeShirtInterestByType(List<TeeShirt> list) {
		// make some wonderful stats
	}

	public void bioCaffeineCount(List<Beverage> beverages) {
		caffeineCountWithFilter(beverages, isBio);
	}

	public void bioAndCaffeineFreeCount(List<Beverage> beverages) {
		caffeineCountWithFilter(beverages, isBioAndCaffeineFree);
	}

	private void caffeineCountWithFilter(List<Beverage> beverages, Predicate<Beverage> filter) {
		beverages.stream()
		         .filter(filter)
		         .forEach(_ -> {
			         // do something
		         });
	}

}
