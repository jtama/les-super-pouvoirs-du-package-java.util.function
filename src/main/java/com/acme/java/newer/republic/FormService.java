package com.acme.java.newer.republic;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
		List<Item> items = new ArrayList<>();
		items.addAll(getFilteredItemList(coffeeDao.getCoffee(), c -> c.containsCaffeine() && !c.isFairTrade()));
		items.addAll(getFilteredItemList(teaDao.getTea(), t -> t.comesFromFsc() && t.type() == Tea.Type.RED));
		items.addAll(getFilteredItemList(teeShirtDao.getTeeShirt(), ALWAYS_MARKET_OPERATION));
		if (items.isEmpty()) {
			throw new VeryBadBusinessException("⚰️ What ? No special operation ? ☠️");
		}
		return items;
	}

	public List<Item> getItemsForChildren() throws URISyntaxException, InterruptedException {
		List<Item> items = new ArrayList<>();
		items.addAll(coffeeDao.getCoffee());
		items.addAll(teaDao.getTea());
		items.addAll(teeShirtDao.getTeeShirt());

		List<Item> itemsFiltered = getFilteredItemList(items, Item::isSuitableForChildren);

		if (itemsFiltered.isEmpty()) {
			throw new VeryBadBusinessException("⚰️ What ? Nothing suitable for children ? ☠️");
		}
		return itemsFiltered;
	}

	private <T extends Item> List<Item> getFilteredItemList(List<T> items, Predicate<T> filter) {
		return items.stream()
		            .filter(filter)
		            .map(item -> {
			            item.setDescription(withShorterDescription(item));
			            return item;
		            })
		            .collect(Collectors.toList());
	}

	private String withShorterDescription(Item item) {
		// If you really want to know, this is neither shorter nor more readable...
		return Base64.getEncoder().encodeToString(item.description().getBytes());
	}

	private static class VeryBadBusinessException extends RuntimeException {

		public VeryBadBusinessException(String message) {
			super(message);
		}

	}

}