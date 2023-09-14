package com.acme.java.old.republic;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

public class FormService {

	private final CoffeeDao coffeeDao;
	private final TeaDao teaDao;
	private final TeeShirtDao teeShirtDao;

	public FormService(CoffeeDao coffeeDao, TeaDao teaDao, TeeShirtDao teeShirtDao) {
		this.coffeeDao = coffeeDao;
		this.teaDao = teaDao;
		this.teeShirtDao = teeShirtDao;
	}

	public List<Item> getItemsForMarketOperation() throws URISyntaxException, InterruptedException {
		List<Item> items = new ArrayList<>();
		items.addAll(coffeeDao.getCoffee());
		items.addAll(teaDao.getTea());
		items.addAll(teeShirtDao.getTeeShirt());

		List<Item> itemsFiltered = items.stream()
		                                .filter(Item::isTarget)
		                                .map(item -> {
			                                item.setDescription(withShorterDescription(item));
			                                return item;
		                                })
		                                .collect(Collectors.toList());

		if (itemsFiltered.isEmpty()) {
			throw new VeryBadBusinessException("⚰️ What ? No special operation ? ☠️");
		}
		return itemsFiltered;
	}

	public List<Item> getItemsForChildren() throws URISyntaxException, InterruptedException {
		List<Item> items = new ArrayList<>();
		items.addAll(coffeeDao.getCoffee());
		items.addAll(teaDao.getTea());
		items.addAll(teeShirtDao.getTeeShirt());

		List<Item> itemsFiltered = items.stream()
		                                .filter(Item::isSuitableForChildren)
		                                .map(item -> {
			                                item.setDescription(withShorterDescription(item));
			                                return item;
		                                })
		                                .collect(Collectors.toList());

		if (itemsFiltered.isEmpty()) {
			throw new VeryBadBusinessException("⚰️ What ? Nothing suitable for children ? ☠️");
		}
		return itemsFiltered;
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