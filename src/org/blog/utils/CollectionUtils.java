package org.blog.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public final class CollectionUtils {
	
	public static <E> List<E> newArrayList() {
		return new ArrayList<E>();
	}
	
	public static <E> List<E> newLinkedList() {
		return new LinkedList<E>();
	}
	
	public static <E> Set<E> newTreeSet() {
		return new TreeSet<E>();
	}
	
	public static <E> Set<E> newHashSet() {
		return new HashSet<E>();
	}

}
