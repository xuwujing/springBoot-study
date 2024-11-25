package com.pancm.enums;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 是否判断
 * 
 */
public enum PcmYesNoEnum {

	YES(1, "是"),

	NO(0, "否"),
	;

	private Integer index;
	private String name;

	PcmYesNoEnum(Integer index, String name) {
		this.index = index;
		this.name = name;
	}

	public Integer getIndex() {
		return index;
	}

	public String getName() {
		return name;
	}

	public static List<Integer> getAllIndexes() {
		PcmYesNoEnum[] values = values();
		List<Integer> indexes = new ArrayList<>();
		for (PcmYesNoEnum value : values) {
			indexes.add(value.index);
		}
		return indexes;
	}

	public static List<LinkedHashMap<String, Object>> getEnumList() {
		List<LinkedHashMap<String, Object>> list = new ArrayList<>();
		LinkedHashMap<String, Object> map;
		for (PcmYesNoEnum item : values()) {
			map = new LinkedHashMap<>();
			map.put("code", item.index);
			map.put("name", item.name);
			list.add(map);
		}
		return list;
	}

	public static String getNameByIndex(Integer index) {
		PcmYesNoEnum[] values = values();
		for (PcmYesNoEnum value : values) {
			if (value.index.equals(index)) {
				return value.name;
			}
		}
		return null;
	}

	public static Integer getIndexByName(String name) {
		PcmYesNoEnum[] values = values();
		for (PcmYesNoEnum value : values) {
			if (value.name.equals(name)) {
				return value.index;
			}
		}
		return null;
	}

	public static List<String> getAllNames(){
		List<String> names=new ArrayList<>();
		PcmYesNoEnum[] values = values();
		for (PcmYesNoEnum value : values) {
			names.add(value.name);
		}
		return names;
	}


}
