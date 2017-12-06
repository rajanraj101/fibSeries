package com.fib.util;

import java.util.Comparator;

import com.fib.beans.Range;

public class RangeComparator implements Comparator<Range> {

	@Override
	public int compare(Range o1, Range o2) {

		if (o1.getStart() < o2.getStart())
				return 0;
		else
				return 1;
	}

	
}
