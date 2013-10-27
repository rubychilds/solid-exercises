package com.theladders.solid.ocp.resume;

import java.util.ArrayList;

public class PhraseListContainer {

	private ArrayList<ConfidentialPhraseCategory> phraseList;
	
	public PhraseListContainer()
	{
		this.phraseList = new ArrayList<ConfidentialPhraseCategory>();
	}
	
	
	public void add(ConfidentialPhraseCategory category) {
		phraseList.add(category);
	}


	public  ArrayList<ConfidentialPhraseCategory> values() {
		return phraseList;
	}
}
