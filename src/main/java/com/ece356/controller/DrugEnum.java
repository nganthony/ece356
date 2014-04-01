package com.ece356.controller;

public class DrugEnum {
	public enum Drug {
		Barbiturates(1), Benzodiazepines(2), Codeine(3), Morphine(4), Methadone(
				5), Amphetamines(6), Methylphenidate(7), Fentanyl(8), Marijuana(
				9), DXM(10),None(11);
		private int id;

		private Drug(int id) {
			this.id = id;
		}

		public int getId() {
			return id;
		}
	}

	public static String  getName(int id){
		for(Drug drug:Drug.values()){
			if(drug.id ==id) return drug.toString();
		}
		return "";
	}
	
}
