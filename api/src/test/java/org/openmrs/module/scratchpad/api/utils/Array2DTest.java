package org.openmrs.module.scratchpad.api.utils;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

public class Array2DTest {

	@Test
	public void testArraySize(){
		Array2D<String> temp = new Array2D<String>(2,5);
		assertTrue(temp.sizeRows()==2);
		assertTrue(temp.sizeColumns()==5);
		
	}
	
	@Test
	public void testArraySet(){
		Array2D<String> temp = new Array2D<String>();
		temp.set(4, 3, "test");
		assertTrue(temp.sizeRows()==5);
		assertTrue(temp.sizeColumns()==4);
		assertTrue(temp.get(4, 3).equalsIgnoreCase("test"));
	}
	
	@Test
	public void testflipXandYaxis() {
		
		Array2D<String> stringList = new Array2D<String>();
		
		
		for (int i=0;i<4;i++){
			for (int j=0;j<6;j++){
				stringList.set(i,j,"R"+Integer.toString(i)+"C"+Integer.toString(j));
			}
		}
		Array2D<String> returnString = stringList.flipXandYaxis();
		assertTrue(returnString.get(2,3).equals(stringList.get(3,2)));
		for (int i=0;i<4;i++){
			for (int j=0;j<6;j++){
				assertTrue(returnString.get(j,i).equals(stringList.get(i,j)));
			}
		}
		
		
	}
	@Test
	public void testConvertToList(){
		Array2D<String> tempArray = new Array2D<String>();
		List<List<String>> tempList;
		
		tempArray.set(2, 4, "fred");
		tempList = tempArray.convertToList();
		String result = tempList.get(2).get(4);
		
		assertTrue(tempList.size()>0);
		assertTrue(result.equalsIgnoreCase("fred"));
		
		
		
	}

}
