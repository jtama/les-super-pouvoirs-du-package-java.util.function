package com.acme.java.newer.republic;

import java.net.URISyntaxException;
import java.util.Base64;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class FormService {

	private final CoffeeDao coffeeDao;
	private final TeaDao teaDao;
	private final TeeShirtDao teeShirtDao;
	private final Predicate<TeeShirt> ALWAYS_MARKET_OPERATION = _ -> true;

	public FormService(CoffeeDao coffeeDao, TeaDao teaDao, TeeShirtDao teeShirtDao) {
		this.coffeeDao = coffeeDao;
		this.teaDao = teaDao;
		this.teeShirtDao = teeShirtDao;
	}

	public List<Item> getItemsForMarketOperation() throws URISyntaxException, InterruptedException {
		Stream<Item> coffeeStream = toItemStream(coffeeDao.getCoffee(), coffee -> coffee.containsCaffeine() && !coffee.isFairTrade());
		Stream<Item> teaStream = toItemStream(teaDao.getTea(), tea -> tea.comesFromFsc() && tea.type() == Tea.Type.RED);
		Stream<Item> teeShirtStream = toItemStream(teeShirtDao.getTeeShirt(), Item::isSuitableForChildren);
		return filterAndControl(
				"⚰️ What ? No special operation ? ☠️", Stream.of(coffeeStream, teaStream, teeShirtStream));
	}

	public List<Item> getItemsForChildren() throws URISyntaxException, InterruptedException {
		Stream<Item> coffeeStream = toItemStream(coffeeDao.getCoffee(), Item::isSuitableForChildren);
		Stream<Item> teaStream = toItemStream(teaDao.getTea(), Item::isSuitableForChildren);
		Stream<Item> teeShirtStream = toItemStream(teeShirtDao.getTeeShirt(), Item::isSuitableForChildren);
		return filterAndControl(
				"⚰️ What ? Nothing suitable for children ? ☠️", Stream.of(coffeeStream, teaStream, teeShirtStream));
	}

	private <T extends Item>  Stream<Item> toItemStream(List<T> values, Predicate<T> filter){
		return values.stream().filter(filter).map(this::withShorterDescription);
	}

	private List<Item> filterAndControl(String exceptionMessage, Stream<Stream<Item>> itemStreams) {
		List<Item> filteredItems = itemStreams
				.flatMap(Function.identity())
				.toList();
		if (filteredItems.isEmpty()) {
			throw new VeryBadBusinessException(exceptionMessage);
		}
		return filteredItems;
	}

	private <T extends Item> Item withShorterDescription(T item) {
		// If you really want to know, this is neither shorter nor more readable...
		item.setDescription(Base64.getEncoder().encodeToString(item.description().getBytes()));
		return item;
	}

	private static class VeryBadBusinessException extends RuntimeException {

		public VeryBadBusinessException(String message) {
			super(message);
		}

	}

}