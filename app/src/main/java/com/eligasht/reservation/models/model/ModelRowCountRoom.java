package com.eligasht.reservation.models.model;

import com.eligasht.reservation.models.model.pack.ChildModel;
import com.eligasht.reservation.tools.ValidationTools;

import java.util.ArrayList;

public class ModelRowCountRoom {
	//vasata
	private int CountB;
	//bozorg
	private int CountK;
	//kochik
	private int CountN;
	private boolean Anim;
	private ArrayList<ChildModel> childModels;

	public void addChildModel(ChildModel childModel) {
		if(ValidationTools.isEmptyOrNull(childModels)){
			childModels = new ArrayList<>();
		}
		childModels.add(childModel);
	}

	public ArrayList<ChildModel> getChildModels() {
		if(ValidationTools.isEmptyOrNull(childModels)){
			childModels = new ArrayList<>();
		}
		return childModels;
	}

	public boolean isAnim() {
		return Anim;
	}

	public void setAnim(boolean anim) {
		Anim = anim;
	}

	public int getCountB() {
		return CountB;
	}
	public void setCountB(int countB) {
		CountB = countB;
	}
	public int getCountK() {
		return CountK;
	}
	public void setCountK(int countK) {
		CountK = countK;
	}
	public int getCountN() {
		return CountN;
	}
	public void setCountN(int countN) {
		CountN = countN;
	}
}
