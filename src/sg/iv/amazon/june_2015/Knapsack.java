package sg.iv.amazon.june_2015;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sg.util.ArrayUtil;

//https://www.youtube.com/watch?v=6h6Fi6AQiRM

public class Knapsack {

	public static class UseItemUnlimited {
		private Table<Integer> table = new Table<>();

		public int getBestValueDP(List<Integer> values, List<Integer> weights,
				int capacity) {
			if (values == null || weights == null)
				return -1;
			if (values.size() != weights.size())
				return -1;
			if (capacity < 0)
				return -1;

			return getBestValueDP(values, weights, capacity, 0);
		}

		private int getBestValueDP(List<Integer> values, List<Integer> weights,	int capacity, int itemIndex) {
			int excludeItem = 0;
			int includeItem = 0;
			int reuseItem = 0;
			if (values.size() <= itemIndex)
				return 0; // items exausted, no more checking
			else if (weights.get(itemIndex) > capacity)
				return getBestValueDP(values, weights, capacity, itemIndex + 1); // item too heavy, skip this item
			else if (table.get(capacity, itemIndex) != null)
				return table.get(capacity, itemIndex);
			else {
				excludeItem = getBestValueDP(values, weights, capacity, itemIndex + 1);

				includeItem = values.get(itemIndex) + getBestValueDP(values, weights, capacity - weights.get(itemIndex), itemIndex + 1);
				
				reuseItem =  values.get(itemIndex) + getBestValueDP(values, weights, capacity - weights.get(itemIndex), itemIndex);
				
				int max = Math.max(Math.max(includeItem, excludeItem), reuseItem);
				table.put(capacity, itemIndex, max);
				
				return max;
			}

		}

		// find best value we can put in knapsack given Value, Weight vectors
		// and a capacity
		public int getBestValueBrute(List<Integer> values, List<Integer> weights, int capacity) {
			if (values == null || weights == null)
				return -1;
			if (values.size() != weights.size())
				return -1;
			if (capacity < 0)
				return -1;
			List<Integer> itemList = new ArrayList<>();
			int result = getBestValueBrute(values, weights, capacity, 0, itemList);
			System.out.print(" " + itemList);
			return result;
		}

		private int getBestValueBrute(List<Integer> values, List<Integer> weights, int capacity, int itemIndex, List<Integer> itemList) {
			if (values.size() <= itemIndex)
				return 0; // items exausted, no more checking
			else if (weights.get(itemIndex) > capacity)
				return getBestValueBrute(values, weights, capacity, itemIndex + 1, itemList); // item too heavy, skip this item
			else {
				List<Integer> excludeItemList = new ArrayList<>();
				int excludeItem = getBestValueBrute(values, weights, capacity, itemIndex + 1, excludeItemList);
				
				List<Integer> includeItemList = new ArrayList<>();
				includeItemList.add(values.get(itemIndex));
				int includeItem = values.get(itemIndex) + getBestValueBrute(values, weights, capacity - weights.get(itemIndex), itemIndex + 1, includeItemList);
				
				List<Integer> reuseItemList = new ArrayList<>();
				reuseItemList.add(values.get(itemIndex));
				int reuseItem = values.get(itemIndex) + getBestValueBrute(values, weights, capacity - weights.get(itemIndex), itemIndex, reuseItemList);
				
				int max = Math.max(Math.max(includeItem, excludeItem), reuseItem);
				
				if (max == includeItem) {
					itemList.addAll(includeItemList);
				} else if (max == excludeItem) {
					itemList.addAll(excludeItemList);
				} else {
					itemList.addAll(reuseItemList);
				}
				
				return max;
			}

		}
	}

	public static class UseItemOnce {
		private Table<Integer> table = new Table<>();

		public int getBestValueDP(List<Integer> values, List<Integer> weights,
				int capacity) {
			if (values == null || weights == null)
				return -1;
			if (values.size() != weights.size())
				return -1;
			if (capacity < 0)
				return -1;

			return getBestValueDP(values, weights, capacity, 0);
		}

		private int getBestValueDP(List<Integer> values, List<Integer> weights,	int capacity, int itemIndex) {
			int excludeItem = 0;
			int includeItem = 0;
			if (values.size() <= itemIndex)
				return 0; // items exausted, no more checking
			else if (weights.get(itemIndex) > capacity)
				return getBestValueDP(values, weights, capacity, itemIndex + 1); // item too heavy, skip this item
			else if (table.get(capacity, itemIndex) != null)
				return table.get(capacity, itemIndex);
			else {
				excludeItem = getBestValueDP(values, weights, capacity, itemIndex + 1);

				includeItem = values.get(itemIndex) + getBestValueDP(values, weights, capacity - weights.get(itemIndex), itemIndex + 1);
				
				int max = Math.max(includeItem, excludeItem);
				table.put(capacity, itemIndex, max);
				
				return max;
			}

		}

		// find best value we can put in knapsack given Value, Weight vectors
		// and a capacity
		// use item only once
		public int getBestValueBrute(List<Integer> values, List<Integer> weights, int capacity) {
			if (values == null || weights == null)
				return -1;
			if (values.size() != weights.size())
				return -1;
			if (capacity < 0)
				return -1;
			List<Integer> itemList = new ArrayList<>();
			int ret = getBestValueBrute(values, weights, capacity, 0, itemList);
//			System.out.print(" "+itemList);
			return ret;
		}

		private int getBestValueBrute(List<Integer> values, List<Integer> weights, int capacity, int itemIndex, List<Integer> itemList) {
			
			if (values.size() <= itemIndex)
				return 0; // items exausted, no more checking
			else if (weights.get(itemIndex) > capacity)
				return getBestValueBrute(values, weights, capacity, itemIndex + 1, itemList); // item too heavy, skip this item
			else {
				List<Integer> excludeItemList = new ArrayList<>();
				int excludeItem = getBestValueBrute(values, weights, capacity, itemIndex + 1, excludeItemList);
				
				List<Integer> includeItemList = new ArrayList<>();
				includeItemList.add(values.get(itemIndex));
				int includeItem = values.get(itemIndex) + getBestValueBrute(values, weights, capacity - weights.get(itemIndex), itemIndex + 1, includeItemList);
				int max = Math.max(includeItem, excludeItem);
				if (max == includeItem) {
					itemList.addAll(includeItemList);
				} else {
					itemList.addAll(excludeItemList);
				}
				return max;
			}

		}
	}

	public static void main(String[] args) {

		int[] arr = ArrayUtil.buildIntArrayFromString("1 2 4");
		List<Integer> values = Arrays.asList(ArrayUtil.getAsObject(arr));

		arr = ArrayUtil.buildIntArrayFromString("1 1 1");
		List<Integer> weights = Arrays.asList(ArrayUtil.getAsObject(arr));

		int capacity = 5;
		
		Knapsack.UseItemOnce once = new Knapsack.UseItemOnce();
		Knapsack.UseItemUnlimited reuse = new Knapsack.UseItemUnlimited();
		
		long start = 0;
		int result = 0;
		long end = 0;
		
		boolean runtime = true;
		
//		for (capacity = 0; capacity <= 45; capacity++) {
			System.out.println("");
			System.out.print("Capacity: " + capacity);
			
			start = System.currentTimeMillis();
			result = once.getBestValueBrute(values, weights, capacity);
			end = System.currentTimeMillis();
			if(runtime) System.out.print(" Runtime: " + (end - start));
			System.out.print(" ONCE: " + result);
			
			start = System.currentTimeMillis();
			result = once.getBestValueDP(values, weights, capacity);
			end = System.currentTimeMillis();
			if(runtime) System.out.print(" Runtime: " + (end - start));
			System.out.print(" ONCE_DP: " + result);
			
			start = System.currentTimeMillis();
			result = reuse.getBestValueBrute(values, weights, capacity);
			end = System.currentTimeMillis();
			if(runtime) System.out.print(" Runtime: " + (end - start));
			System.out.print(" REUSE: " + result);
			
			start = System.currentTimeMillis();
			result = reuse.getBestValueDP(values, weights, capacity);
			end = System.currentTimeMillis();
			if(runtime) System.out.print(" Runtime: " + (end - start));
			System.out.print(" REUSE_DP: " + result);
//		}

	}
}

class Table<T> {
	Map<Integer, Map<Integer, T>> tableMap = new HashMap<>();

	public void put(int x, int y, T data) {
		Map<Integer, T> row = tableMap.get(x);
		if (row == null) {
			row = new HashMap<>();
			tableMap.put(x, row);
		}
		row.put(y, data);
	}

	public T get(int x, int y) {
		Map<Integer, T> row = tableMap.get(x);
		if (row != null) {
			return row.get(y);
		}
		return null;
	}

}